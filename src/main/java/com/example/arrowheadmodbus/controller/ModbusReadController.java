package com.example.arrowheadmodbus.controller;

import com.example.arrowheadmodbus.dto.ModbusReadResponseDTO;
import com.example.arrowheadmodbus.service.ModbusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/modbus")
public class ModbusReadController {

    @Autowired
    private ModbusService modbusService;

    @GetMapping("/read-inputs")
    public List<ModbusReadResponseDTO> readInputs(
            @RequestParam(defaultValue = "0") int startAddress,
            @RequestParam(defaultValue = "9") int count
    ) {
        boolean[] values = modbusService.readDiscreteInputs(startAddress, count);
        List<ModbusReadResponseDTO> responseList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            responseList.add(new ModbusReadResponseDTO(startAddress + i, values[i]));
        }

        return responseList;
    }
}
