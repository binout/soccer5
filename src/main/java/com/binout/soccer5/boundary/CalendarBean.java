/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.binout.soccer5.boundary;

import com.binout.soccer5.controller.MatchEJB;
import com.binout.soccer5.entity.Match;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author benoit
 */
@Model
public class CalendarBean {

    private Date newDate;
    private Match currentMatch;
    
    @Inject
    private MatchEJB ejb;

    @PostConstruct
    public void initCurrentMatch() {
        currentMatch = ejb.getNextMatch();
    }
    
    public Date getMinDate() {
        return new Date();
    }

    public Date getNewDate() {
        return newDate;
    }

    public void setNewDate(Date newDate) {
        this.newDate = newDate;
    }

    public Match getCurrentMatch() {
        return currentMatch;
    }

    public void setCurrentMatch(Match currentMatch) {
        this.currentMatch = currentMatch;
    }

    public void register() throws Exception {
        if (newDate != null) {
            Match m = new Match();
            m.setDate(newDate);
            ejb.registerMatch(m);
            initCurrentMatch();
        }
        newDate = null;
    }

}
