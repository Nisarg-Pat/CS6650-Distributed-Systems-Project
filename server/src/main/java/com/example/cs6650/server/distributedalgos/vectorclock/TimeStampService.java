package com.example.cs6650.server.distributedalgos.vectorclock;

import com.example.cs6650.server.model.MyServer;
import com.example.cs6650.server.repository.MyServerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class TimeStampService {
    @Autowired
    TimeStampRepository timeStampRepository;

    @Autowired
    MyServerRepository myServerRepository;

    public List<Integer> generateTimeStamp() {
        TimeStamp timeStamp = getMyTimeStamp();
        timeStamp.setValue(timeStamp.getValue()+1);
        timeStampRepository.save(timeStamp);
        return getTimeStamp();
    }

    public TimeStamp getMyTimeStamp() {
        MyServer myServer = myServerRepository.getMyServerById(1);
        int index = myServer.getGlobalIndex();
        return timeStampRepository.getTimeStampById(index);
    }

    public List<Integer> getTimeStamp() {
        List<TimeStamp> timeStamps = timeStampRepository.findAll();
        List<Integer> ans = new ArrayList<>();
        for(int i=0;i<100;i++) {
            ans.add(0);
        }
        for(TimeStamp timeStamp: timeStamps) {
            ans.set(timeStamp.getId(), timeStamp.getValue());
        }
        return ans;
    }

    public void setTimeStamp(List<Integer> stamps) {
        for(int i=0;i<100;i++) {
            TimeStamp timeStamp = timeStampRepository.getTimeStampById(i);
            timeStamp.setValue(Math.max(timeStamp.getValue(), stamps.get(i)));
            timeStampRepository.save(timeStamp);
        }
    }
}
