package com.example.arrowheadmodbus.dto;

import eu.arrowhead.common.dto.shared.OrchestrationFormRequestDTO;
import eu.arrowhead.common.dto.shared.OrchestrationResponseDTO;
import eu.arrowhead.common.dto.shared.ServiceQueryFormDTO;
import org.springframework.web.client.RestTemplate;

public class SystemRequestDTO extends eu.arrowhead.common.dto.shared.SystemRequestDTO {
    private String systemName;
    private String address;
    private int port;
    private String authenticationInfo;

    private final RestTemplate restTemplate = new RestTemplate();

    public SystemRequestDTO() {
        // Default constructor
    }

    public SystemRequestDTO(String systemName, String address, int port, String authenticationInfo) {
        this.systemName = systemName;
        this.address = address;
        this.port = port;
        this.authenticationInfo = authenticationInfo;
    }

    public void orchestrateService() {
        // Construct the orchestration form request
        OrchestrationFormRequestDTO orchestrationFormRequest = new OrchestrationFormRequestDTO();

        // Set the requested service definition
        // Assuming the correct method is setServiceDefinitionRequirement
        // Create a ServiceQueryFormDTO and set the required service definition
        ServiceQueryFormDTO serviceQueryForm = new ServiceQueryFormDTO();
        serviceQueryForm.setServiceDefinitionRequirement("modbus-read-inputs");

        orchestrationFormRequest.setRequestedService(serviceQueryForm);

        // Set requester system information
        SystemRequestDTO requester = new SystemRequestDTO();
        requester.setSystemName("orchestrator-client");
        requester.setAddress("localhost");
        requester.setPort(8080);
        requester.setAuthenticationInfo(null);  // Set a security token if required

        // Add the requester system to the orchestration form
        orchestrationFormRequest.setRequesterSystem(requester);

        // Create an instance of your orchestration service to make the request
        String orchestratorServiceUrl = "http://localhost:8081/orchestrator";
        OrchestrationResponseDTO response = restTemplate.postForObject(
                orchestratorServiceUrl + "/orchestration",
                orchestrationFormRequest,
                OrchestrationResponseDTO.class
        );

        // Use the response from the orchestrator to access provider details
        if (response != null) {
            System.out.println("Orchestration response: " + response);
        } else {
            System.out.println("No response from orchestrator.");
        }
    }


    // Getters and Setters
    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAuthenticationInfo() {
        return authenticationInfo;
    }

    public void setAuthenticationInfo(String authenticationInfo) {
        this.authenticationInfo = authenticationInfo;
    }
}
