package com.binout.soccer5.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.binout.soccer5.entity.Match;
import com.binout.soccer5.entity.Player;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

@Stateless
public class MatchEJB {

    @PersistenceContext
    EntityManager em;
    
    public Match findMatch(Long id) {
        return em.find(Match.class, id);
    }

    public Match findMatch(Date date) {
        TypedQuery<Match> query = em.createNamedQuery(Match.FIND_BY_DATE, Match.class);
        query.setParameter("date", date);
        List<Match> existing = query.getResultList();
        if (existing.isEmpty()) {
            return null;
        } else {
            return existing.get(0);
        }
    }
    
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
    
    public Match getNextMatch() {
        TypedQuery<Match> query = em.createNamedQuery(Match.FIND_NEXT_MATCH, Match.class);
        query.setParameter("today", new Date());
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }
}
