package com.example.cs6650.server.distributedalgos.ricartoagarwala;

import com.example.cs6650.server.common.Log;
import com.example.cs6650.server.coordinator.RestService;
import com.example.cs6650.server.coordinator.Server;
import com.example.cs6650.server.model.MyServer;
import com.example.cs6650.server.repository.MyServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.*;

@Controller
public class RicartAgarwala {

    @Autowired
    StateRepository stateRepository;

    @Autowired
    MyServerRepository myServerRepository;

    @Autowired
    RestService restService;

    Queue<Server> serverQueue = new LinkedList<>();

    public void enterSection() {
        Log.logln("Enter Section");

        MyServer myserver = myServerRepository.getMyServerById(1);

        List<LinkedHashMap<String, Object>> serverList = (List<LinkedHashMap<String, Object>>) restService.post(restService.generateURL("localhost", 8080, "allservers"), null).getBody();
        List<Server> servers = new ArrayList<>();
        for (LinkedHashMap<String, Object> serverL: serverList) {
            Server server = new Server((String) serverL.get("host"), (Integer)serverL.get("port"));
            if(server.getHost().equals(myserver.getHost())&&server.getPort()==myserver.getPort()) {
                continue;
            }
            servers.add(server);
        }

        long timestamp = Long.parseLong(System.currentTimeMillis()+""+myserver.getPort());
        State state = stateRepository.getStateById(1);
        state.setState(State.WANTED);
        state.setTimestamp(timestamp);
        state.setServerCount(servers.size());
        stateRepository.save(state);

        Server servero = new Server();
        servero.setHost(myserver.getHost());
        servero.setPort(myserver.getPort());
        servero.setRaTimestamp(timestamp);

        for(Server server: servers) {
            ResponseEntity<Object> response = restService.post(restService.generateURL(server.getHost(), server.getPort(), "rarequest"), servero);
        }
        state.setState(State.HELD);
        stateRepository.save(state);
    }


    public void exitSection() {
        Log.logln("Exit Section");
        State state = stateRepository.getStateById(1);
        state.setState(State.RELEASED);
        stateRepository.save(state);
        if(!serverQueue.isEmpty()) {
            Server server = serverQueue.poll();
            restService.post(restService.generateURL(server.getHost(), server.getPort(), "raresponse"), server);
        }
    }

    @PostMapping("/rarequest")
    public ResponseEntity<Object> rarequest(@RequestBody Server server) {
        Log.logln("RA Request");
        State state = stateRepository.getStateById(1);
        if(state.getState().equals(State.HELD) || state.getState().equals(State.WANTED) && state.getTimestamp()<server.getRaTimestamp()) {
            serverQueue.add(server);
        } else {
            restService.post(restService.generateURL(server.getHost(), server.getPort(), "raresponse"), server);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("/raresponse")
    public ResponseEntity<Object> raresponse(@RequestBody Server server) {
        Log.logln("RA Response");
        State state = stateRepository.getStateById(1);
        state.setResponseCount(state.getResponseCount()+1);
        Log.logln(state.getServerCount() + " "+state.getResponseCount());
        if(state.getResponseCount() == state.getServerCount()) {
            Log.logln("Setting state to held");
            state.setState(State.HELD);
        }
        stateRepository.save(state);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
