package com.NotReal.FanMeeting.SpringServer.controller;

import com.NotReal.FanMeeting.SpringServer.domain.Position;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {

    private String id;
    private String password;
    private String username;
    private Position position;
}
