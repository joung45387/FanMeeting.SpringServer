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
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequiredArgsConstructor
public class Register {

    private final MemberRepository memberRepository;

    @GetMapping("/regist")
    public String savemember(Model model, MemberForm memberForm){
        model.addAttribute("memberForm", memberForm);
        return "regist/regist";
    }
    @PostMapping("/regist")
    public String save(@ModelAttribute MemberForm memberForm, Model model){
        ConcurrentHashMap<String, String> errors = new ConcurrentHashMap<>();
        if(memberRepository.find(memberForm.getId()) != null){
            errors.put("idDuplication", "중복된 아이디입니다.");
        }
        if(memberRepository.findUserName(memberForm.getUsername()) != null){
            errors.put("userNameDuplication", "중복된 유저네임입니다.");
        }
        if(memberForm.getPosition()==null){
            errors.put("notExist", "포지션을 선택해야 합니다.");
        }
        if(!errors.isEmpty()){
            model.addAttribute("errors", errors);
            return "regist/regist";
        }
        memberRepository.save(new Member(memberForm.getId(), memberForm.getPassword(), memberForm.getUsername(), memberForm.getPosition(), LocalDateTime.now()));
        return "redirect:/regist";
    }
}
