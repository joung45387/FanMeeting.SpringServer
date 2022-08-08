package com.NotReal.FanMeeting.SpringServer.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class MessageEntity {

    @Id
    private String messageId;
    @ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    private String sender;
    private String text;
    private LocalDateTime chatTime;
    public void createMessage(String messageId, Member member, String sender, String text){
        this.messageId = messageId;
        this.member = member;
        this.sender = sender;
        this.text = text;
        this.chatTime = LocalDateTime.now();
    }
}
