package com.example.cs6650.server.coordinator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
public class CoordinatorService {

    @Autowired
    ServerRepository serverRepository;

    public Server addServer(Server server) {
        if(serverRepository.getServerByHostAndPort(server.getHost(), server.getPort()).isEmpty()) {
            serverRepository.save(server);
        }
        return serverRepository.getServerByHostAndPort(server.getHost(), server.getPort()).get();
    }

    public Server getRandomServer() {
        List<Server> serverList= serverRepository.findAll();
        int random = new Random().nextInt(0, serverList.size());
        return serverList.get(random);
    }
}
