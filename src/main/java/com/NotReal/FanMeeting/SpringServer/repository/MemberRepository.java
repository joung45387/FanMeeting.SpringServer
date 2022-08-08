package com.NotReal.FanMeeting.SpringServer.repository;

import com.NotReal.FanMeeting.SpringServer.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;
    List<Member> memory = new ArrayList<>();

    public String save(Member member){

        memory.add(member);
        em.persist(member);
        return member.getId();
    }

    public Member find(String id){
        return em.find(Member.class, id);
    }

    public Member findUserName(String name){
        String jpql= "select m From Member m where m.username ='"+name+"'";
        Member result = em.createQuery(jpql, Member.class).getSingleResult();
        System.out.println(em.contains(result));
        return result;
    }
    public void doFlush(Member member){
        em.merge(member);
    }
}
