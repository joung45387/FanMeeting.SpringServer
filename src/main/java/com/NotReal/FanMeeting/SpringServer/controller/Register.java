package com.NotReal.FanMeeting.SpringServer.controller;

import com.NotReal.FanMeeting.SpringServer.domain.Member;
import com.NotReal.FanMeeting.SpringServer.domain.Position;
import com.NotReal.FanMeeting.SpringServer.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class Register {

    private final MemberRepository memberRepository;

    @GetMapping("/regist")
    public String savemember(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "regist/regist";
    }
    @PostMapping("/regist")
    public String save(@ModelAttribute MemberForm memberForm){

        memberRepository.save(new Member(memberForm.getId(), memberForm.getPassword(), memberForm.getUsername(), memberForm.getPosition(), LocalDateTime.now()));
        return "redirect:/regist";
    }
}
