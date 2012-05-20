package com.binout.soccer5.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.binout.soccer5.entity.Match;
import com.binout.soccer5.entity.Player;
import javax.ejb.Stateless;

@Stateless
public class MatchEJB {

    @PersistenceContext
    EntityManager em;

    public void removeMatch(Long id) {
        Match p = em.find(Match.class, id);
        em.remove(p);
    }

    public void registerMatch(Match p) {               
        TypedQuery<Match> query = em.createNamedQuery(Match.FIND_BY_DATE, Match.class);
        query.setParameter("date", p.getDate());
        List<Match> existing = query.getResultList();
        if (existing.isEmpty()) {
            em.persist(p);
        }
    }

    public List<Match> listMatches() {
        TypedQuery<Match> query = em.createNamedQuery(Match.FIND_ALL, Match.class);
        return query.getResultList();
    }
    
    public void registerPlayerToMatch(Match m, Player p) {
        Match match = em.merge(m);
        Player player = em.merge(p);
        match.addPlayer(player);
    }
    
    public void unregisterPlayerToMatch(Match m, Player p) {
        Match match = em.merge(m);
        Player player = em.merge(p);
        match.removePlayer(player);
    }
}
