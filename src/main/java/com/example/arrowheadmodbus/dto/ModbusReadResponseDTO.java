package com.example.arrowheadmodbus.dto;

public class ModbusReadResponseDTO {
    private int address;
    private boolean value;

    // Constructor
    public ModbusReadResponseDTO(int address, boolean value) {
        this.address = address;
        this.value = value;
    }

    // Getters and Setters
    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }
}
