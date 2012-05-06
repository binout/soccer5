/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.binout.soccer5.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author benoit
 */
public class Match {
    
    private Date date;
    
    private List<Player> players;

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
