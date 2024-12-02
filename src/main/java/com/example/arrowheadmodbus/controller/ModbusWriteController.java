package com.example.arrowheadmodbus.controller;

import com.example.arrowheadmodbus.dto.ModbusRequestDTO;
import com.example.arrowheadmodbus.service.ModbusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/modbus")
public class ModbusWriteController {

    @Autowired
    private ModbusService modbusService;

    @PostMapping("/write-coil")
    public String writeCoil(@RequestBody ModbusRequestDTO request) {
        modbusService.writeCoil(request.getCoilAddress(), request.isCoilValue());
        return "Coil written successfully.";
    }
}
