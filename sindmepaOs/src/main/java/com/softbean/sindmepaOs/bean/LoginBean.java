/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.bean;

import com.softbean.sindmepaOs.controle.CadFuncionarioControle;
import com.softbean.sindmepaOs.entidade.CadFuncionario;
import com.softbean.sindmepaOs.manager.IndexManager;
import com.softbean.sindmepaOs.util.Util;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author admin
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    /**
     * Creates a new instance of LoginManagerBean
     */
    public LoginBean() {
    }

    @Inject
    CadFuncionarioControle funcionarioControle;
    @Inject
    Util util;
    @Inject
    IndexManager indexManager;

    String cpfAcess, senha;
    CadFuncionario usuario;

    public String logar() {
        FacesContext mensagem = FacesContext.getCurrentInstance();
        RequestContext context = RequestContext.getCurrentInstance();
        setUsuario(null);//limpar variavel
        setUsuario(funcionarioControle.validaAcesso(getCpfAcess(), util.converteParaMd5(getSenha())));

        if (getUsuario() == null) {
            mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepProtocol Informa:", "Usuário ou senha inválidos, tente Novamente."));
            return null;

        } else {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            if (session != null) {
                session.setAttribute("usuario", getUsuario());
            }
            indexManager.carregaGrids();
            context.update(":frmIndex");
            return "index";
        }
    }

    public String logOff() {
        FacesContext mensagem = FacesContext.getCurrentInstance();
        RequestContext context = RequestContext.getCurrentInstance();
        HttpSession session = (HttpSession) mensagem.getExternalContext().getSession(false);
        mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Informa:", "Você saiu do sistema."));
        setCpfAcess(null);
        setSenha(null);
        session.invalidate();
        context.update(":frmLogin");
        return "login";
    }

    public String getCpfAcess() {
        return cpfAcess;
    }

    public void setCpfAcess(String cpfAcess) {
        this.cpfAcess = cpfAcess;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public CadFuncionario getUsuario() {
        return usuario;
    }

    public void setUsuario(CadFuncionario usuario) {
        this.usuario = usuario;
    }
}
