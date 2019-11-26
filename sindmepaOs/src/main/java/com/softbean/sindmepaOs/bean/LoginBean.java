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
import java.util.Date;
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

    String cpfAcess, senha, senhaAcess, repetirSenhaAcess;
    CadFuncionario usuario;
    CadFuncionario usuAltSenhaObj;

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
            context.update(":frmIndex :frmDashboard");
            return "index";
        }
    }

//    lse if (getUsuario().getSenhaFunc().equals(util.converteParaMd5("102030"))) {
//            setUsuAltSenhaObj(null);
//            setUsuAltSenhaObj(funcionarioControle.retornaUsuario(getUsuario().getCadFuncionarioPK().getCpfFunc(), util.converteParaMd5(getSenha())));
//            context.update(":frmPrimeiroAcesso");
//            return "primeiroacesso";
//
//        } 
    public String alterarSenha() {
        FacesContext mensagem = FacesContext.getCurrentInstance();
        RequestContext context = RequestContext.getCurrentInstance();
        String ret = "primeiroacesso";
        return ret;
//        if (!getSenhaAcess().equals(getRepetirSenhaAcess())) {
//            if (getUsuAltSenhaObj() != null) {
//                getUsuAltSenhaObj().setSenhaFunc(getSenhaAcess());
//                getUsuAltSenhaObj().setDtUltAtuFunc(new Date());
//                getUsuAltSenhaObj().setFuncUltAtuFunc(999);
//
//                if (funcionarioControle.alterarSenha(getUsuAltSenhaObj())) {
//                    mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepProtocol Informa:", "Senha alterada com sucesso. acesse o sistema com sua nova senha."));
//                    limparPrimeiroAcesso();
//                    context.update(":frmLogin");
//                    ret = "login";
//                } else {
//                    mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepProtocol Informa:", "Ocorreu um erro ao tentarmos alterar sua senha."));                    
//                }
//            }
//            return ret;
//
//        } else {
//            System.out.println("Entrou no if senhas iguais");
//            mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepProtocol Informa:", "Erro ao Validar Senha (Senhas Diferentes)."));
//            return ret;
//        }
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

    public void limparPrimeiroAcesso() {
        setUsuAltSenhaObj(null);
        setSenhaAcess(null);
        setRepetirSenhaAcess(null);
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

    public String getSenhaAcess() {
        return senhaAcess;
    }

    public void setSenhaAcess(String senhaAcess) {
        this.senhaAcess = senhaAcess;
    }

    public String getRepetirSenhaAcess() {
        return repetirSenhaAcess;
    }

    public void setRepetirSenhaAcess(String repetirSenhaAcess) {
        this.repetirSenhaAcess = repetirSenhaAcess;
    }

    public CadFuncionario getUsuAltSenhaObj() {
        return usuAltSenhaObj;
    }

    public void setUsuAltSenhaObj(CadFuncionario usuAltSenhaObj) {
        this.usuAltSenhaObj = usuAltSenhaObj;
    }

}
