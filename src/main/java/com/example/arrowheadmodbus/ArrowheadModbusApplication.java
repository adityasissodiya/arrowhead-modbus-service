package com.example.arrowheadmodbus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ArrowheadModbusApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArrowheadModbusApplication.class, args);
    }
}
