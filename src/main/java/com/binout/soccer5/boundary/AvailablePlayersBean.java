/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.binout.soccer5.boundary;

import com.binout.soccer5.controller.MatchEJB;
import com.binout.soccer5.controller.PlayerEJB;
import com.binout.soccer5.entity.Match;
import com.binout.soccer5.entity.Player;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

/**
 *
 * @author benoit
 */
@Model
public class AvailablePlayersBean {
    
    @Inject
    private PlayerEJB playerEjb;
    @Inject
    private MatchEJB matchEjb;
    
    private List<SelectItem> selectItems;
    private SelectItem selectedItem;
   
    @PostConstruct
    public void fillSelectItems() {
        selectItems = new ArrayList<SelectItem>();
        for (Player p : playerEjb.listPlayers()) {
            selectItems.add(new SelectItem(p, p.getName()));
        }
    }
    
    public void join(Match m) {
        if (selectedItem != null) {
            matchEjb.registerPlayerToMatch(m, (Player)selectedItem.getValue());
        }
    }

    public List<SelectItem> getSelectItems() {
        return selectItems;
    }

    public SelectItem getSelectedItem() {
        return selectedItem;
    }
    
    public void setSelectedItem(SelectItem selectedItem) {
        this.selectedItem = selectedItem;
    }

   
}
