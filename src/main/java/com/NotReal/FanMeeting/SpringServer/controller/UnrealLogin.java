package com.NotReal.FanMeeting.SpringServer.controller;

import com.NotReal.FanMeeting.SpringServer.domain.Member;
import com.NotReal.FanMeeting.SpringServer.domain.Position;
import com.NotReal.FanMeeting.SpringServer.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import net.jodah.expiringmap.ExpiringMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UnrealLogin {

    private final MemberRepository memberRepository;
    @PostMapping("/UELogin")
    public String uelogin(@RequestBody Member info){

        if(memberRepository.find(info.getId()) != null ){
            Member ge = memberRepository.find(info.getId());
            if(ge.getPassword().equals(info.getPassword()) && ge.getBlockTime().isBefore(LocalDateTime.now())){
                return ge.getUsername();
            }
            else
                return "bad";
        }
        else
            return "bad";
    }
}
