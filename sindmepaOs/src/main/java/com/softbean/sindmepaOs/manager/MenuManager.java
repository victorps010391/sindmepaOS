/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.manager;

import com.softbean.sindmepaOs.bean.CadFuncionarioBean;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author admin
 */
@Named(value = "menuManager")
@SessionScoped
public class MenuManager implements Serializable {

    /**
     * Creates a new instance of MenuControle
     */
    public MenuManager() {
    }

    @Inject
    CadFuncionarioBean funcionarioBean;

    public String menuCadFucionario() {
        funcionarioBean.limparCadastro();
        return "cadfuncionario.xhtml";
    }
}
