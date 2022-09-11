package com.NotReal.FanMeeting.SpringServer.controller;

import com.NotReal.FanMeeting.SpringServer.domain.*;
import com.NotReal.FanMeeting.SpringServer.repository.MemberRepository;
import com.NotReal.FanMeeting.SpringServer.repository.PermisionUpgradeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.util.List;

import static com.NotReal.FanMeeting.SpringServer.controller.AdminLogin.SESSION_ID;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MemberController {
    private final PermisionUpgradeRepository puRepository;
    private final MemberRepository memberRepository;

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        if(request == null){
            return "redirect:/";
        }
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/myinfo")
    public String upgrade(@SessionAttribute(name = SESSION_ID, required = false) Member member, Model model){
        if(member == null){
            return "redirect:/";
        }
        model.addAttribute("info", member);
        return "myinfo";
    }

    @GetMapping("/sendreq")
    public String sendReq(@SessionAttribute(name = SESSION_ID, required = false)Member member, Model model){
        if(member == null){
            return "redirect:/";
        }
        model.addAttribute("info", member);
        return "sendreq";
    }

    @PostMapping("/sendreq")
    public String postSendReq(@SessionAttribute(name = SESSION_ID, required = false)Member member, @ModelAttribute(name = "s") String s, @ModelAttribute(name="po") String po){
        if(member == null){
            return "redirect:/";
        }
        puRepository.save(new PermisionUpgrade(member, s, Position.VTUBER.valueOf(po), LocalDateTime.now()));
        member.setRequestPermission(RequestPosition.신청중);
        memberRepository.doFlush(member);
        return "endpage";
    }
}
