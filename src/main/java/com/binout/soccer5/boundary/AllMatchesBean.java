package com.binout.soccer5.boundary;

import com.binout.soccer5.controller.MatchEJB;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;

import com.binout.soccer5.entity.Match;

@Model
public class AllMatchesBean {

    @EJB
    private MatchEJB ejb;
    

    public List<Match> getAllMatches() {
        return ejb.listMatches();
    }
    
    public void removeMatch(Match m) {
        ejb.removeMatch(m);
    }

}
