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
@Table(name="RGUEST")
public class Guest {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String name;
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
