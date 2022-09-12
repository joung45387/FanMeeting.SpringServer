package com.NotReal.FanMeeting.SpringServer.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class PermisionUpgrade {
    @Id
    @GeneratedValue
    private int id;
    @OneToOne(targetEntity = Member.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;
    private String message;
    @Enumerated(EnumType.STRING)
    private Position request;
    private LocalDateTime requstTime;
    public PermisionUpgrade(Member member, String message, Position request, LocalDateTime requstTime) {
        this.member = member;
        this.message = message;
        this.request = request;
        this.requstTime = requstTime;
    }

    public PermisionUpgrade() {

    }
}
