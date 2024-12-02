package com.example.arrowheadmodbus.service;

import com.example.arrowheadmodbus.dto.ServiceRegistryRequestDTO;
import com.example.arrowheadmodbus.dto.SystemRequestDTO;
import com.example.arrowheadmodbus.utils.ArrowheadHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map; // Import Map
import java.util.HashMap; // Import HashMap if needed
import java.util.Collections; // Import Collections if needed

@Service
public class ArrowheadRegistrationService {

    @Value("${arrowhead.service.registry.url}")
    private String serviceRegistryUrl;

    private final ArrowheadHttpClient arrowheadHttpClient;

    public ArrowheadRegistrationService(ArrowheadHttpClient arrowheadHttpClient) {
        this.arrowheadHttpClient = arrowheadHttpClient;
    }

    @PostConstruct
    public void registerServices() {
        registerService("/modbus/read-inputs", "ModbusReadService");
        registerService("/modbus/write-coil", "ModbusWriteService");
    }

    private void registerService(String serviceUri, String serviceDefinition) {
        ServiceRegistryRequestDTO request = new ServiceRegistryRequestDTO();
        request.setServiceDefinition(serviceDefinition);
        request.setProviderSystem(new SystemRequestDTO("ModbusApplication", "localhost", 8080, null));
        request.setServiceUri(serviceUri);
        request.setMetadata(Collections.singletonMap("security", "token"));

        String url = serviceRegistryUrl + "/register";

        arrowheadHttpClient.post(url, request);
        System.out.println(serviceDefinition + " registered with Arrowhead.");
    }
}
