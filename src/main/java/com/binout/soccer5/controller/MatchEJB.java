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
    
    public void registerGuestToMatch(Match m, String guest) {
        Match match = em.merge(m);
        if (!match.getGuests().contains(guest)) {
            match.addGuest(guest);
        }
    }
    
    public void unregisterGuestToMatch(Match m, String guest) {
        Match match = em.merge(m);
        match.removeGuest(guest);
    }
    
    public void unregisterPlayerToMatch(Match m, Player p) {
        Match match = em.merge(m);
        Player player = em.merge(p);
        match.removePlayer(player);
    }
    
    public List<Match> getNextMatches() {
        TypedQuery<Match> query = em.createNamedQuery(Match.FIND_NEXT_MATCHES, Match.class);
        query.setParameter("today", new Date());
        return query.getResultList();
    }
}
