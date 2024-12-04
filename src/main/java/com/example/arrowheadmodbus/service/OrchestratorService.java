package com.example.arrowheadmodbus.service;

import eu.arrowhead.common.dto.shared.OrchestrationFormRequestDTO;
import eu.arrowhead.common.dto.shared.OrchestrationResponseDTO;
import eu.arrowhead.common.dto.shared.ServiceRegistryRequestDTO;
import eu.arrowhead.common.dto.shared.SystemRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrchestratorService {

    @Autowired
    private ArrowheadRegistrationService arrowheadRegistrationService;

    // Register the service to the Arrowhead Service Registry
    public void registerService() {
        ServiceRegistryRequestDTO serviceRegistryRequest = new ServiceRegistryRequestDTO();
        serviceRegistryRequest.setServiceDefinition("modbus-read-inputs");

        SystemRequestDTO provider = new SystemRequestDTO();
        provider.setSystemName("modbus-service");
        provider.setAddress("localhost");
        provider.setPort(8080);
        provider.setAuthenticationInfo(""); // Add security token if required

        serviceRegistryRequest.setProviderSystem(provider);
        serviceRegistryRequest.setServiceUri("/modbus/read-inputs");
        serviceRegistryRequest.setMetadata(new HashMap<>()); // Add metadata if needed
        serviceRegistryRequest.setSecure("NOT_SECURE"); // Change if using secure connections

        arrowheadRegistrationService.registerServiceToServiceRegistry(serviceRegistryRequest);
    }

    /*// Discover services through the orchestrator
    public void orchestrateService() {
        OrchestrationFormRequestDTO orchestrationFormRequest = new OrchestrationFormRequestDTO();
        orchestrationFormRequest.setRequestedServiceDefinition("modbus-read-inputs");

        ArrowheadSystem requester = new ArrowheadSystem("orchestrator-client", "localhost", 8080, null);
        orchestrationFormRequest.setRequesterSystem(requester);

        OrchestrationResponseDTO response = arrowheadService.proceedOrchestration(orchestrationFormRequest);

        // Use the OrchestrationResponseDTO to access provider details and make a request
        System.out.println("Orchestration response: " + response);
    }*/
}
