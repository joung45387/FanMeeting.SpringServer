package com.NotReal.FanMeeting.SpringServer.controller;


import com.NotReal.FanMeeting.SpringServer.Service.Connecting;
import com.NotReal.FanMeeting.SpringServer.domain.LoginForm;
import com.NotReal.FanMeeting.SpringServer.domain.Member;
import com.NotReal.FanMeeting.SpringServer.domain.MessageEntity;
import com.NotReal.FanMeeting.SpringServer.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

import static com.NotReal.FanMeeting.SpringServer.controller.AdminLogin.SESSION_ID;

@Controller
@Log4j2
@RequiredArgsConstructor
public class ChatController {
    private final Connecting connecting;
    private final ChatRepository chatRepository;

    @GetMapping("/chat")
    public String chatGET(@SessionAttribute(name = SESSION_ID, required = false)Member member, Model model){
        System.out.println(member.getUsername());
        if(member.getUsername() == null){
            return "redirect:/";
        }
        model.addAttribute("players",connecting.getConnecting());
        model.addAttribute("userName", member.getUsername());
        return "chat";
    }
    @GetMapping("/pastchat")
    public String pastChat(@SessionAttribute(name = SESSION_ID, required = false)Member member, Model model, String sender){
        if(member.getUsername() == null){
            return "redirect:/";
        }
        List<MessageEntity> messageEntityList = chatRepository.find(sender);
        model.addAttribute("chatList", messageEntityList);
        return "pastchat";
    }
}
