package com.NotReal.FanMeeting.SpringServer.controller;

import com.NotReal.FanMeeting.SpringServer.domain.LoginForm;
import com.NotReal.FanMeeting.SpringServer.domain.Member;
import com.NotReal.FanMeeting.SpringServer.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

import static com.NotReal.FanMeeting.SpringServer.domain.Position.FAN;

@Controller
@RequiredArgsConstructor
public class AdminLogin {
    private final MemberRepository memberRepository;
    static final String SESSION_ID = "SESSION_ID";

    @GetMapping("/")
    public String getLogin(@SessionAttribute(name = SESSION_ID, required = false) Member login,  Model model){
        if(login !=null){
            if(login.getPosition()==FAN){
                return "redirect:/fanpage";
            }
            return "redirect:/chat";
        }
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }
    @PostMapping("/")
    public String postLogin(@ModelAttribute LoginForm loginForm, HttpServletRequest request){
        if(memberRepository.find(loginForm.getLoginId()) != null ){
            Member ge = memberRepository.find(loginForm.getLoginId());
            if(ge.getPassword().equals(loginForm.getLoginPassword())){
                HttpSession session = request.getSession();
                session.setAttribute(SESSION_ID, ge);
                if(ge.getPosition()!= FAN){
                    return "redirect:/chat";
                }
                return "redirect:/fanpage";
            }
            else {
                return "redirect:/";
            }
        }
        return "redirect:/";
    }
}
