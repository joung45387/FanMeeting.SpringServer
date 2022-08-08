package com.NotReal.FanMeeting.SpringServer.controller;


import com.NotReal.FanMeeting.SpringServer.Service.Connecting;
import com.NotReal.FanMeeting.SpringServer.domain.Member;
import com.NotReal.FanMeeting.SpringServer.domain.Message;
import com.NotReal.FanMeeting.SpringServer.repository.ChatRepository;
import com.NotReal.FanMeeting.SpringServer.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class MessageController {
    private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final Connecting connecting;
    private final MemberRepository memberRepository;
    private final ChatRepository chatRepository;

    @MessageMapping("/hello")
    public void message(Message message){
        message.setMessageId(message.getSender()+"-"+System.currentTimeMillis());
        Member member = memberRepository.findUserName(message.getSender());
        chatRepository.save(message, member);
        simpMessageSendingOperations.convertAndSend("/sub/channel/"+message.getChannelId(), message);
    }

    @MessageMapping("/del")
    public void deleteMessage(Message message){
        simpMessageSendingOperations.convertAndSend("/sub/channel/del", message);
    }

    @MessageMapping("/playerlist")
    public void playerList(Member member){
        connecting.adding(member.getUsername());
        simpMessageSendingOperations.convertAndSend("/sub/channel/playerlist", member);
    }
    @MessageMapping("/kickplayer")
    public void kickPlayer(Member member){
        connecting.removing(member.getUsername());
        simpMessageSendingOperations.convertAndSend("/sub/channel/kickplayer", member);
    }
    @MessageMapping("/endless")
    public void endLess(Member member){
        Member m = memberRepository.findUserName(member.getUsername());
        m.setBlockTime(LocalDateTime.of(9999, 12, 30, 0, 0, 0));
        memberRepository.doFlush(m);
        connecting.removing(member.getUsername());
        simpMessageSendingOperations.convertAndSend("/sub/channel/kickplayer", member);
    }
}
