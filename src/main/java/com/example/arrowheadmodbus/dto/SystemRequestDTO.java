package com.example.arrowheadmodbus.dto;

public class SystemRequestDTO {
    private String systemName;
    private String address;
    private int port;
    private String authenticationInfo;

    public SystemRequestDTO() {
        // Default constructor
    }

    public SystemRequestDTO(String systemName, String address, int port, String authenticationInfo) {
        this.systemName = systemName;
        this.address = address;
        this.port = port;
        this.authenticationInfo = authenticationInfo;
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

    public int getPort() {
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
