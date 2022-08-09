package com.NotReal.FanMeeting.SpringServer.repository;

import com.NotReal.FanMeeting.SpringServer.domain.Member;
import com.NotReal.FanMeeting.SpringServer.domain.Message;
import com.NotReal.FanMeeting.SpringServer.domain.MessageEntity;
import lombok.RequiredArgsConstructor;
import net.jodah.expiringmap.ExpiringMap;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
@RequiredArgsConstructor
public class ChatRepository {
    private final EntityManager em;
    private ExpiringMap<String, Integer> chatFreeze = ExpiringMap.builder()
            .variableExpiration()
            .build();

    public void save(Message message, Member member){
        MessageEntity me = new MessageEntity();
        me.createMessage(message.getMessageId(), member, message.getSender(), message.getText());
        em.persist(me);
    }
    public List<MessageEntity> find(String sender){
        String jpql= "select m From MessageEntity m where m.sender ='"+sender+"'";
        List<MessageEntity> resultList = em.createQuery(jpql, MessageEntity.class).getResultList();
        return resultList;
    }

    public ExpiringMap<String, Integer> exMap(){
        return chatFreeze;
    }
}
