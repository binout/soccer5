/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.binout.soccer5.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author benoit
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Player.FIND_ALL,
    query = "SELECT p FROM Player p order by p.id"),
    @NamedQuery(name = Player.FIND_BY_NAME,
    query = "SELECT p FROM Player p where p.name=:name")
})
public class Player {

    public final static String FIND_ALL = "player.findAll";
    public final static String FIND_BY_NAME = "player.findByName";
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String mail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
