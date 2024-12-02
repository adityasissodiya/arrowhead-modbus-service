package com.example.arrowheadmodbus.dto;

public class ModbusRequestDTO {
    private int coilAddress;
    private boolean coilValue;

    // Getters and Setters
    public int getCoilAddress() {
        return coilAddress;
    }

    public void setCoilAddress(int coilAddress) {
        this.coilAddress = coilAddress;
    }

    public boolean isCoilValue() {
        return coilValue;
    }

    public void setCoilValue(boolean coilValue) {
        this.coilValue = coilValue;
    }
}
