package com.NotReal.FanMeeting.SpringServer.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Member {
    @Id
    private String id;
    private String password;
    private String username;
    @Enumerated(EnumType.STRING)
    private Position position;

    private LocalDateTime blockTime;

    @Enumerated(EnumType.STRING)
    private RequestPosition requestPermission;

    public Member(String id, String password, String username, Position position, LocalDateTime blockTime) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.position = position;
        this.blockTime = blockTime;
    }

    public Member() {

    }
}
