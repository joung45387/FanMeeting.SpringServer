package com.NotReal.FanMeeting.SpringServer.repository;

import com.NotReal.FanMeeting.SpringServer.domain.Member;
import com.NotReal.FanMeeting.SpringServer.domain.PermisionUpgrade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class PermisionUpgradeRepository {
    private final EntityManager em;

    public Integer save(PermisionUpgrade pu){
        em.persist(pu);
        return pu.getId();
    }

    public List<PermisionUpgrade> findAll(){
        String jpql= "select m From PermisionUpgrade m";
        return em.createQuery(jpql, PermisionUpgrade.class).getResultList();
    }

    public void delete(String s){
        String jpql= "delete From PermisionUpgrade m where m.member.id='"+s+"'";
        em.createQuery(jpql).executeUpdate();
    }

    public PermisionUpgrade find(String id){
        return em.find(PermisionUpgrade.class, id);
    }

}
