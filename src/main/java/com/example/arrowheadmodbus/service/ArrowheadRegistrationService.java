package com.example.arrowheadmodbus.service;

import com.example.arrowheadmodbus.dto.ServiceRegistryRequestDTO;
import com.example.arrowheadmodbus.dto.SystemRequestDTO;
import com.example.arrowheadmodbus.utils.ArrowheadHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Map; // Import Map
import java.util.HashMap; // Import HashMap if needed
import java.util.Collections; // Import Collections if needed

@Service
public class ArrowheadRegistrationService {

    @Value("${arrowhead.service.registry.url}")
    private String serviceRegistryUrl;

    private final RestTemplate restTemplate;

    public ArrowheadRegistrationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void registerService() {
        // Construct the registration request payload
        ServiceRegistryRequestDTO request = new ServiceRegistryRequestDTO();
        // Populate request with necessary details

        // Send registration request
        restTemplate.postForEntity(serviceRegistryUrl + "/register", request, Void.class);
    }
}