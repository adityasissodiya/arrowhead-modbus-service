package com.example.arrowheadmodbus;

import com.example.arrowheadmodbus.service.ModbusService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ModbusServiceTests {

    @Test
    public void testReadDiscreteInputs() {
        ModbusService modbusService = Mockito.mock(ModbusService.class);
        Mockito.when(modbusService.readDiscreteInputs(0, 2)).thenReturn(new boolean[]{true, false});

        boolean[] inputs = modbusService.readDiscreteInputs(0, 2);
        assert inputs[0];
        assert !inputs[1];
    }

    @Test
    public void testWriteCoil() {
        ModbusService modbusService = Mockito.mock(ModbusService.class);
        Mockito.doNothing().when(modbusService).writeCoil(0, true);

        modbusService.writeCoil(0, true);
        Mockito.verify(modbusService).writeCoil(0, true);
    }
}
