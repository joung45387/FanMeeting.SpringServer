package com.NotReal.FanMeeting.SpringServer.repository;

import com.NotReal.FanMeeting.SpringServer.domain.PermisionUpgrade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
@RequiredArgsConstructor
public class PermisionUpgradeRepository {
    private final EntityManager em;

    public Integer save(PermisionUpgrade pu){
        em.persist(pu);
        return pu.getId();
    }
    public PermisionUpgrade find(String id){
        return em.find(PermisionUpgrade.class, id);
    }

}
