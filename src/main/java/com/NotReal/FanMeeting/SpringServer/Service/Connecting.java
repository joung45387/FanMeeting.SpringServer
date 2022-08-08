package com.NotReal.FanMeeting.SpringServer.Service;

import com.NotReal.FanMeeting.SpringServer.domain.Member;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Connecting {
    private List<String> connect = new ArrayList<>();
    public void adding(String username){
        connect.add(username);
    }
    public void removing(String username){
        connect.remove(username);
    }
    public List<String> getConnecting(){
        return connect;
    }
    boolean isConnecting(String username){
        return connect.contains(username);
    }
}
