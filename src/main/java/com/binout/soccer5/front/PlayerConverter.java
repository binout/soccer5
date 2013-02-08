package com.binout.soccer5.front;

import com.binout.soccer5.back.PlayerEJB;
import com.binout.soccer5.entity.Player;
import javax.enterprise.inject.Model;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

@Model
public class PlayerConverter implements Converter{

    @Inject
    private PlayerEJB playerEjb;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return playerEjb.findPlayer(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Player p = (Player) value;
        return p.getName();
    }
    
}
