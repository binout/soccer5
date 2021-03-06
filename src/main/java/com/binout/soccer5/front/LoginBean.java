package com.binout.soccer5.front;

import com.binout.soccer5.front.filter.AuthenticationFilter;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;

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
    
    public void checkCredential() {
        boolean logged = "admin".equals(login) && "admin".equals(password);
        if (logged) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(AuthenticationFilter.AUTH_KEY, login);
        }
        login = null;
        password = null;
    }
    
    public boolean isLoggedIn() {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AuthenticationFilter.AUTH_KEY) != null;
    }
    
    public boolean isNotLoggedIn() {
        return !isLoggedIn();
    }
    
    public void logout() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(AuthenticationFilter.AUTH_KEY);
    }       
    
}
