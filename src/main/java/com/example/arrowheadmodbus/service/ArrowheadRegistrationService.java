package com.example.arrowheadmodbus.service;


import eu.arrowhead.common.dto.shared.ServiceRegistryRequestDTO;
import eu.arrowhead.common.dto.shared.SystemRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ArrowheadRegistrationService {

    @Value("${arrowhead.service.registry.url}")
    private String serviceRegistryUrl;

    private final RestTemplate restTemplate;

    public ArrowheadRegistrationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void registerServiceToServiceRegistry(ServiceRegistryRequestDTO serviceRegistryRequest) {
        // Set the service definition for the request
        serviceRegistryRequest.setServiceDefinition("modbus-read-inputs");

        // Create a provider object and set its properties
        SystemRequestDTO provider = new SystemRequestDTO();
        provider.setSystemName("modbus-service");
        provider.setAddress("localhost");
        provider.setPort(8080);
        provider.setAuthenticationInfo(""); // Add security token if required

        // Set the provider in the request
        serviceRegistryRequest.setProviderSystem(provider);

        // Send the registration request to the service registry
        restTemplate.postForEntity(serviceRegistryUrl + "/register", serviceRegistryRequest, Void.class);
    }

}