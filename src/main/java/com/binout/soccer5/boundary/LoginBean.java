/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.binout.soccer5.boundary;

import com.binout.soccer5.boundary.filter.AuthenticationFilter;
import javax.enterprise.inject.Model;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.FacesContext;

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
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(AuthenticationFilter.AUTH_KEY, login);
            redirect = "admin.xhtml";
        }
        login = null;
        password = null;
        return redirect;
    }
    
    public boolean isLoggedIn() {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AuthenticationFilter.AUTH_KEY) != null;
    }
    
    public boolean isNotLoggedIn() {
        return !isLoggedIn();
    }
    
}
