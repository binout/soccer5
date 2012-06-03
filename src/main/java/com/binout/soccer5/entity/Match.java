/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.binout.soccer5.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.joda.time.DateTime;

/**
 *
 * @author benoit
 */
@Entity
@Table(name="RMATCH")
@NamedQueries({
    @NamedQuery(name = Match.FIND_ALL,
    query = "SELECT m FROM Match m order by m.date desc"),
    @NamedQuery(name = Match.FIND_BY_DATE,
    query = "SELECT m FROM Match m where m.date=:date"),
    @NamedQuery(name = Match.FIND_NEXT_MATCH,
    query = "SELECT m "
        + "FROM Match m "
        + "where m.date="
        + "(select min(m2.date)"
        + " from Match m2"
        + " where m2.date > :today)")
})
public class Match {
    
    public final static String FIND_ALL = "match.findAll";
    public final static String FIND_BY_DATE = "match.findByDate";
    public final static String FIND_NEXT_MATCH = "match.findNextMatch";

    @Id
    @GeneratedValue
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    
    @ManyToMany(cascade=CascadeType.DETACH, fetch= FetchType.EAGER)
    private List<Player> players;
    
    @ManyToMany(cascade=CascadeType.ALL, fetch= FetchType.EAGER)
    private List<Guest> guests;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public Date getEndDate() {
        DateTime dt = new DateTime(date);
        dt.plusHours(2);
        return dt.toDate();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player p) {
        if (players==null) {
            players = new ArrayList<Player>();
        }
        players.add(p);
    }
    
    public void removePlayer(Player p) {
        if (players!=null) {
            players.remove(p);
        } 
    }
    
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getNbPlayers() {
        return players == null ? 0 : players.size();
    }
    
    public List<Guest> getGuests() {
        return guests;
    }

     public void addGuest(Guest p) {
        if (guests==null) {
            guests = new ArrayList<Guest>();
        }
        guests.add(p);
    }
    
    public void removeGuest(Guest p) {
        if (guests!=null) {
            guests.remove(p);
        } 
    }
    
    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }
    
    public int getNbGuests() {
        return guests == null ? 0 : guests.size();
    }
    
    public boolean isFull() {
        return 10 == (getNbGuests() + getNbPlayers());
    }
    
    public boolean isOpen() {
        return !isFull();
    }
    
}
