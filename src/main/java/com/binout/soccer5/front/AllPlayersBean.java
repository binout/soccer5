package com.binout.soccer5.front;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;

import com.binout.soccer5.back.PlayerEJB;
import com.binout.soccer5.entity.Player;

import com.google.common.base.Strings;
import javax.annotation.PostConstruct;
import javax.validation.constraints.Size;

@Model
public class AllPlayersBean {

    @EJB
    private PlayerEJB ejb;
    
    @Size(min = 1, message = "Please enter a name")
    private String name;
    @Size(min = 1, message = "Please enter a mail")
    private String mail;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getAllPlayers() {
        return ejb.listPlayers();
    }

    public void register() throws Exception {
        if (!Strings.isNullOrEmpty(name) && !Strings.isNullOrEmpty(mail)) {
            Player p = new Player();
            p.setName(name);
            p.setMail(mail);
            ejb.registerPlayer(p);
        }
        name = null;
        mail = null;
    }

    private void createPlayer(String name, String mail) {
        Player p = new Player();
        p.setName(name);
        p.setMail(mail);
        try {
            ejb.registerPlayer(p);
        } catch (Throwable t) {
        }        
    }
    
}
