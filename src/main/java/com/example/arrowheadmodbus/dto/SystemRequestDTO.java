package com.example.arrowheadmodbus.dto;

public class SystemRequestDTO {
    private String systemName;
    private String address;
    private int port;
    private String authenticationInfo;

    public SystemRequestDTO(String systemName, String address, int port, String authenticationInfo) {
        this.systemName = systemName;
        this.address = address;
        this.port = port;
        this.authenticationInfo = authenticationInfo;
    }

    // Getters and Setters
    // ...
}
