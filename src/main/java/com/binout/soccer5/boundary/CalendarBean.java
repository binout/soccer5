/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.binout.soccer5.boundary;

import com.binout.soccer5.controller.MatchEJB;
import com.binout.soccer5.controller.PlayerEJB;
import com.binout.soccer5.entity.Match;
import com.binout.soccer5.entity.Player;
import com.google.common.base.Strings;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 *
 * @author benoit
 */
@Model
public class CalendarBean {

    private Date newDate;
    
    @Inject
    private MatchEJB ejb;

    public Date getNewDate() {
        return newDate;
    }

    public void setNewDate(Date newDate) {
        this.newDate = newDate;
    }

    public List<Match> getMatches() {
        return ejb.listMatches();
    }
    
     public void register() throws Exception {
        if (newDate != null) {
            Match m = new Match();
            m.setDate(newDate);
            ejb.registerMatch(m);
        }
        newDate = null;
    }
}
