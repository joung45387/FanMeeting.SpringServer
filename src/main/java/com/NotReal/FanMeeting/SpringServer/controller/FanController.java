package com.NotReal.FanMeeting.SpringServer.controller;

import com.NotReal.FanMeeting.SpringServer.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.NotReal.FanMeeting.SpringServer.controller.AdminLogin.SESSION_ID;

@Controller
@Log4j2
@RequiredArgsConstructor
public class FanController {
    @GetMapping("/fanpage")
    public String fanPage(@SessionAttribute(name = SESSION_ID, required = false) Member member, Model model){
        if(member == null){
            return "redirect:/";
        }
        model.addAttribute("userName", member.getUsername());
        return "FanPage";
    }
}
