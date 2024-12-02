package com.example.arrowheadmodbus.service;

import com.ghgande.j2mod.modbus.facade.ModbusTCPMaster;
import com.ghgande.j2mod.modbus.procimg.InputRegister;
import com.ghgande.j2mod.modbus.ModbusException;
import com.ghgande.j2mod.modbus.util.BitVector;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class ModbusService {

    private ModbusTCPMaster modbusMaster;

    @Value("${modbus.slave.ip}")
    private String slaveIp;

    @Value("${modbus.slave.port}")
    private int slavePort;

    @Value("${modbus.unit.id}")
    private int unitId;

    @PostConstruct
    public void initialize() {
        try {
            modbusMaster = new ModbusTCPMaster(slaveIp, slavePort);
            modbusMaster.connect();
            System.out.println("Modbus connection established.");
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to Modbus slave.", e);
        }
    }

    @PreDestroy
    public void cleanup() {
        try {
            if (modbusMaster != null) {
                modbusMaster.disconnect();
                System.out.println("Modbus connection closed.");
            }
        } catch (Exception e) {
            System.err.println("Error closing Modbus connection: " + e.getMessage());
        }
    }

    public boolean[] readDiscreteInputs(int startAddress, int count) {
        try {
            BitVector bitVector = modbusMaster.readInputDiscretes(startAddress, count);
            return bitVector.toBooleanArray(); // Use toBooleanArray() instead of getBits()
        } catch (ModbusException e) {
            throw new RuntimeException("Error reading Modbus discrete inputs.", e);
        }
    }

    public void writeCoil(int address, boolean value) {
        try {
            modbusMaster.writeCoil(address, value);
        } catch (ModbusException e) {
            throw new RuntimeException("Error writing to Modbus coil.", e);
        }
    }
}
