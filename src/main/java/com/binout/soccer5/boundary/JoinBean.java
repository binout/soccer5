/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.binout.soccer5.boundary;

import com.binout.soccer5.controller.MatchEJB;
import com.binout.soccer5.controller.PlayerEJB;
import com.binout.soccer5.entity.Match;
import com.binout.soccer5.entity.Player;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author benoit
 */
@Model
public class JoinBean {

    @Inject
    private MatchEJB matchEjb;
    @Inject
    private PlayerEJB playerEjb;
    private Player selectedPlayer;

    public Player getSelectedPlayer() {
        return selectedPlayer;
    }

    public void setSelectedPlayer(Player selectedPlayer) {
        this.selectedPlayer = selectedPlayer;
    }

    public List<Player> complete(String query) {
        return playerEjb.listPlayers();
    }
    
    public void join(Match m) {
        if (selectedPlayer != null) {
            matchEjb.registerPlayerToMatch(m, selectedPlayer);
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unkonwn Player", "Unkonwn Player");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        selectedPlayer = null;
    }
    
}
