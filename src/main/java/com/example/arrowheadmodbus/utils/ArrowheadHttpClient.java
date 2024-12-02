package com.example.arrowheadmodbus.utils;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ArrowheadHttpClient {

    private final RestTemplate restTemplate;

    public ArrowheadHttpClient() {
        this.restTemplate = new RestTemplate();
        // Configure SSL and other settings if necessary
    }

    public void post(String url, Object request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(request, headers);

        restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
    }
}
