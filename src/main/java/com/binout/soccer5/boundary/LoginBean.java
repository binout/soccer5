/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.binout.soccer5.boundary;

import javax.enterprise.inject.Model;

/**
 *
 * @author benoit
 */
@Model
public class LoginBean {
 
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String checkCredential() {
        boolean logged = "root".equals(login) && "lectra".equals(password);
        String redirect = "index.xhtml";
        if (logged) {
            redirect = "admin.xhtml";
        }
        login = null;
        password = null;
        return redirect;
    }
    
}
