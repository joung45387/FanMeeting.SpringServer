package com.NotReal.FanMeeting.SpringServer.Handler;

import com.NotReal.FanMeeting.SpringServer.domain.Message;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Log4j2
public class WebSocketHandler extends TextWebSocketHandler {
    /*private static Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        sessions.values().forEach(s->{
            try {
                s.sendMessage(new TextMessage(message.toString()));
            }catch (Exception e){

            }
        });

    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        String sessionId = session.getId();

        sessions.put(sessionId, session);

        Message message = Message.builder().sender(sessionId).receiver("all").build();
        message.newConnect();

        sessions.values().forEach(s->{
            try {
                if(!s.getId().equals(sessionId)){
                    s.sendMessage(new TextMessage("누군가 접속함"));
                }
            }catch (Exception e){

            }
        });

        log.info(session + " 클라이언트 접속");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String sessionId = session.getId();
        sessions.remove(sessionId);
    }*/
}
