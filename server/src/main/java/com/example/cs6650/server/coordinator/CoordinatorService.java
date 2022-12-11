package com.example.cs6650.server.coordinator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class CoordinatorService {

    @Autowired
    ServerRepository serverRepository;

    private final RestTemplate restTemplate;

    public CoordinatorService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Server addServer(Server server) {
        serverRepository.save(server);
        return server;
    }
}
