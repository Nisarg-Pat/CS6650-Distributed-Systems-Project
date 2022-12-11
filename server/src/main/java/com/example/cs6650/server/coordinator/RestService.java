package com.example.cs6650.server.coordinator;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class RestService {
    private final RestTemplate restTemplate;

    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public ServerData sendData(String host, int port, ServerData serverData) {
        String url = "http://"+host+":"+port+"/serverdata";

        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // build the request
        HttpEntity<ServerData> entity = new HttpEntity<>(serverData, headers);

        // send POST request
        ResponseEntity<ServerData> response = restTemplate.postForEntity(url, entity, ServerData.class);
        return response.getBody();
    }
}
