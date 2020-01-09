/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.manager;

import com.softbean.sindmepaOs.bean.CadAnaliseBean;
import com.softbean.sindmepaOs.bean.CadFuncionarioBean;
import com.softbean.sindmepaOs.bean.LoginBean;
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
    @Inject
    CadAnaliseBean analiseBean;
    @Inject
    LoginBean loginBean;

    public String menuCadFucionario() {
        funcionarioBean.limparCadastro();
        return "cadfuncionario.xhtml";
    }

    public String menuAnalise() {
        analiseBean.pesquisarMenu();
        return "cadanalise.xhtml";
    }

    public String menuAlterarSenha() {
        funcionarioBean.limparPrimeiroAcesso();
        return "alterarsenha.xhtml";
    }

    public Boolean menuAcompanhamento() {
        return loginBean.getUsuario().getSetorFunc().getCdSetor() == 7;
    }
}
