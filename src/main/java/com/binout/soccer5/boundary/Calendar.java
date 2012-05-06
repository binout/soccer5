/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.binout.soccer5.boundary;

import com.binout.soccer5.entity.Match;
import com.binout.soccer5.entity.Player;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.enterprise.inject.Model;

/**
 *
 * @author benoit
 */
@Model
public class Calendar {

    
    
    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<Player>();
        players.add(newPlayer());
        players.add(newPlayer());
        players.add(newPlayer());
        return players;
    }

    public List<Match> getMatches() {
        List<Match> matches = new ArrayList<Match>();
        matches.add(newMatch(6));
        matches.add(newMatch(5));
        matches.add(newMatch(9));
        matches.add(newMatch(10));
        matches.add(newMatch(10));
        matches.add(newMatch(10));

        return matches;
    }

    private Match newMatch(int nbPlayer) {
        Match m = new Match();
        m.setDate(new Date());
        for (int i=0; i<nbPlayer; i++) {
            m.addPlayer(newPlayer());
        }
        return m;
    }

    private Player newPlayer() {
        Player p = new Player();
        String name = "toto" + new Random().nextInt();
        p.setName(name);
        p.setMail(name + "@gmail.com");
        return p ;
    }
}
