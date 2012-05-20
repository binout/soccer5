/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.binout.soccer5.boundary;

import com.binout.soccer5.controller.MatchEJB;
import com.binout.soccer5.entity.Match;
import com.binout.soccer5.entity.Player;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 *
 * @author benoit
 */
@Model
public class MatchBean {
    
    @Inject
    private MatchEJB matchEjb; 
    
    public void remove(Match m, Player p) {
        matchEjb.unregisterPlayerToMatch(m, p);
    }
}
