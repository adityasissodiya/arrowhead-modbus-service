package com.example.arrowheadmodbus.dto;

import java.util.Map;

public class ServiceRegistryRequestDTO {
    private String serviceDefinition;
    private SystemRequestDTO providerSystem;
    private String serviceUri;
    private Map<String, String> metadata;

    // Getter and Setter for serviceDefinition
    public String getServiceDefinition() {
        return serviceDefinition;
    }

    public void setServiceDefinition(String serviceDefinition) {
        this.serviceDefinition = serviceDefinition;
    }

    // Getter and Setter for providerSystem
    public SystemRequestDTO getProviderSystem() {
        return providerSystem;
    }

    public void setProviderSystem(SystemRequestDTO providerSystem) {
        this.providerSystem = providerSystem;
    }

    // Getter and Setter for serviceUri
    public String getServiceUri() {
        return serviceUri;
    }

    public void setServiceUri(String serviceUri) {
        this.serviceUri = serviceUri;
    }

    // Getter and Setter for metadata
    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }
}
