package com.NotReal.FanMeeting.SpringServer.controller;


import com.NotReal.FanMeeting.SpringServer.Service.Connecting;
import com.NotReal.FanMeeting.SpringServer.domain.*;
import com.NotReal.FanMeeting.SpringServer.repository.ChatRepository;
import com.NotReal.FanMeeting.SpringServer.repository.MemberRepository;
import com.NotReal.FanMeeting.SpringServer.repository.PermisionUpgradeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.jodah.expiringmap.ExpirationPolicy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.NotReal.FanMeeting.SpringServer.controller.AdminLogin.SESSION_ID;

@Controller
@Log4j2
@RequiredArgsConstructor
public class ChatController {
    private final Connecting connecting;
    private final ChatRepository chatRepository;
    private final MessageController messageController;
    private final MemberRepository memberRepository;
    private final PermisionUpgradeRepository puRepository;

    @GetMapping("/chat")
    public String chatGET(@SessionAttribute(name = SESSION_ID, required = false)Member member, Model model){
        if(member == null || member.getPosition()==Position.FAN){
            return "redirect:/";
        }
        model.addAttribute("players",connecting.getConnecting());
        model.addAttribute("userName", member.getUsername());
        model.addAttribute("isFreeze", messageController.getFreeze());
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
    @GetMapping("/freezechat")
    public String freezeChat(@SessionAttribute(name = SESSION_ID, required = false)Member member, Model model, String sender, Integer hour, Integer min){
        if(member.getUsername() == null){
            return "redirect:/";
        }
        chatRepository.exMap().put(sender, hour*60+min, ExpirationPolicy.CREATED, hour*60+min,TimeUnit.MINUTES);
        List<MessageEntity> messageEntityList = chatRepository.find(sender);
        model.addAttribute("chatList", messageEntityList);
        return "pastchat";
    }

    @GetMapping("/timeban")
    public String timeBan(@SessionAttribute(name = SESSION_ID, required = false)Member member, Model model, String sender, Integer hour, Integer min){
        if(member.getUsername() == null){
            return "redirect:/";
        }
        LocalDateTime blocktime = LocalDateTime.now();
        blocktime = blocktime.plusHours(hour);
        blocktime = blocktime.plusMinutes(min);
        Member findMember = memberRepository.findUserName(sender);
        findMember.setBlockTime(blocktime);
        messageController.kickPlayer(findMember);
        memberRepository.doFlush(findMember);
        List<MessageEntity> messageEntityList = chatRepository.find(sender);
        model.addAttribute("chatList", messageEntityList);
        return "pastchat";
    }

    @GetMapping("/meltchat")
    public String meltChat(@SessionAttribute(name = SESSION_ID, required = false)Member member, Model model, String sender){
        if(member.getUsername() == null){
            return "redirect:/";
        }
        chatRepository.exMap().remove(sender);
        List<MessageEntity> messageEntityList = chatRepository.find(sender);
        model.addAttribute("chatList", messageEntityList);
        return "pastchat";
    }
    @GetMapping("/unblock")
    public String unBlock(@SessionAttribute(name = SESSION_ID, required = false)Member member, Model model, String sender){
        if(member.getUsername() == null){
            return "redirect:/";
        }
        Member findMember = memberRepository.findUserName(sender);
        findMember.setBlockTime(LocalDateTime.now());
        memberRepository.doFlush(findMember);

        List<MessageEntity> messageEntityList = chatRepository.find(sender);
        model.addAttribute("chatList", messageEntityList);
        return "pastchat";
    }
    @GetMapping("/allowpermission")
    public String allowPermission(@SessionAttribute(name = SESSION_ID, required = false)Member member, Model model){
        if(member == null || member.getPosition()==Position.FAN){
            return "redirect:/";
        }
        List<PermisionUpgrade> all = puRepository.findAll();
        model.addAttribute("info", all);
        return "allowpage";
    }

    @PostMapping("/permissionok")
    public String postSendReq(@SessionAttribute(name = SESSION_ID, required = false)Member member, @ModelAttribute(name = "ok") String s, @ModelAttribute(name = "req") String req){
        if(member == null || member.getPosition()==Position.FAN){
            return "redirect:/";
        }
        puRepository.delete(s);
        Member member1 = memberRepository.find(s);
        member1.setPosition(Position.valueOf(req));
        member1.setRequestPermission(RequestPosition.등업완료);
        memberRepository.doFlush(member1);
        return "redirect:/allowpermission";
    }

    @PostMapping("/permissionrefuse")
    public String postSendrefuse(@SessionAttribute(name = SESSION_ID, required = false)Member member, @ModelAttribute(name = "ok") String s){
        if(member == null || member.getPosition()==Position.FAN){
            return "redirect:/";
        }
        puRepository.delete(s);
        Member member1 = memberRepository.find(s);
        member1.setRequestPermission(RequestPosition.거부);
        memberRepository.doFlush(member1);
        return "redirect:/allowpermission";
    }
}
