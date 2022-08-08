package com.NotReal.FanMeeting.SpringServer.domain;

import lombok.*;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Message {

    private String messageId;
    private String sender;
    private String channelId;
    private String text;

    public void setSender(String sender){
        this.sender = sender;
    }
    public void setMessageId(String id){
        this.messageId = id;
    }
}
