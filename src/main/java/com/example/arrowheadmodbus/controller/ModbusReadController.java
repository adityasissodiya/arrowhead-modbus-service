package com.example.arrowheadmodbus.controller;

import com.example.arrowheadmodbus.service.ModbusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/modbus")
public class ModbusReadController {

    @Autowired
    private ModbusService modbusService;

    @GetMapping("/read-inputs")
    public boolean[] readInputs(
            @RequestParam(defaultValue = "0") int startAddress,
            @RequestParam(defaultValue = "2") int count
    ) {
        return modbusService.readDiscreteInputs(startAddress, count);
    }
}
