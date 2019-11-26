/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.controle;

import com.softbean.sindmepaOs.entidade.CadFuncionario;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Victor
 */
public class LoginControle implements PhaseListener {

    /**
     * Creates a new instance of LoginControle
     */
    public LoginControle() {
    }
    
     private FacesContext facesContext;

    @Override
    public void afterPhase(PhaseEvent event) {
        facesContext = event.getFacesContext();
        String viewId = facesContext.getViewRoot().getViewId();

        NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
        boolean paginaLogin = viewId.lastIndexOf("login") > -1;

        if (existeUsuarioLogado() && paginaLogin) {            
            if (getUsuario().getSenhaFunc().equals(util.converteParaMd5("102030"))){
                
            }
            
            nh.handleNavigation(facesContext, null, "index");
        } else if (!existeUsuarioLogado() && !paginaLogin) {                                    
            nh.handleNavigation(facesContext, null, "login");
        }
    }
    
    public

    public boolean existeUsuarioLogado() {
        return (((CadFuncionario) getAtributoSessao("usuario")) != null);
    }

    public Object getAtributoSessao(String attributeName) {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            return session.getAttribute(attributeName);
        }
        return null;
    }

    @Override
    public void beforePhase(PhaseEvent event) {

    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
    
}
