package com.binout.soccer5.front;

import com.binout.soccer5.back.MatchEJB;
import com.binout.soccer5.entity.Match;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

@Model
public class ScheduleBean {

    @Inject
    private MatchEJB ejb;
    private ScheduleModel schedule;

    public ScheduleModel getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleModel schedule) {
        this.schedule = schedule;
    }

    @PostConstruct
    public void initSchedule() {
        schedule = new DefaultScheduleModel();
        for (Match m : ejb.listMatches()) {
            final DefaultScheduleEvent event = new DefaultScheduleEvent("Match("+m.getNbPlayersAndGuests()+"/10)", m.getDate(), m.getEndDate());
            if (m.isFull()) {
                event.setTitle("Match");
                event.setStyleClass("full-match");
            } else {
                event.setStyleClass("open-match");
            }
            schedule.addEvent(event);
        }
    }
}
