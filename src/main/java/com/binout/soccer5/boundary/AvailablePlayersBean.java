/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.binout.soccer5.boundary;

import com.binout.soccer5.controller.PlayerEJB;
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
    private PlayerEJB ejb;
    
    private List<SelectItem> selectItems;
    private Player selectedItem;
   
    @PostConstruct
    public void fillSelectItems() {
        selectItems = new ArrayList<SelectItem>();
        for (Player p : ejb.listPlayers()) {
            selectItems.add(new SelectItem(p, p.getName()));
        }
    }
    
    public void action() {
        System.out.println("Selected Foo item: " + selectedItem);
    }

    public List<SelectItem> getSelectItems() {
        return selectItems;
    }

    public Player getSelectedItem() {
        return selectedItem;
    }
    
    public void setSelectedItem(Player selectedItem) {
        this.selectedItem = selectedItem;
    }

   
}
