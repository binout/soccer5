/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.binout.soccer5.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author benoit
 */
@Entity
public class Match {
    
    @Id
    @GeneratedValue
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @ManyToMany(cascade=CascadeType.ALL)
    private List<Player> players;

    
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

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player p) {
        if (players==null) {
            players = new ArrayList<Player>();
        }
        players.add(p);
    }
    
    public void setPlayers(List<Player> players) {
        this.players = players;
    }
    
    public boolean isFull() {
        return players == null ? false : 10 == players.size();
    }
    
    public boolean isOpen() {
        return !isFull();
    }
    
}
