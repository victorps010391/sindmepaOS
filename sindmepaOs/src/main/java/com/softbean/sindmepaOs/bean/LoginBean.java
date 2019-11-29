/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.bean;

import com.softbean.sindmepaOs.controle.CadFuncionarioControle;
import com.softbean.sindmepaOs.entidade.CadFuncionario;
import com.softbean.sindmepaOs.manager.IndexManager;
import com.softbean.sindmepaOs.util.MailUtil;
import com.softbean.sindmepaOs.util.Util;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
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
    @Inject
    MailUtil mailUtil;

    String cpfAcess, senha, cpfSolicitacao, novaSenha;
    CadFuncionario usuario;
    CadFuncionario usuarioSolicitacaoSenha;

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
    
    public void limpaCpf(){
        setCpfSolicitacao(null);
    }

    public String solicitarAlteraSenha() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext mensagem = FacesContext.getCurrentInstance();
        String ret = "login";
        try {
            setUsuarioSolicitacaoSenha(null);//limpar variavel
            setNovaSenha(null);//limpar variavel           
            if (funcionarioControle.retornaUsuarioSolicitacaoSenha(getCpfSolicitacao()) != null) {
                setUsuarioSolicitacaoSenha(funcionarioControle.retornaUsuarioSolicitacaoSenha(getCpfSolicitacao()));
                setNovaSenha(util.randomSenha());

                getUsuarioSolicitacaoSenha().setSenhaFunc(util.converteParaMd5(getNovaSenha()));
                getUsuarioSolicitacaoSenha().setFuncUltAtuFunc(999);
                getUsuarioSolicitacaoSenha().setDtUltAtuFunc(new Date());

                if (funcionarioControle.alterarSenha(getUsuarioSolicitacaoSenha())) {
                    if (enviarEmailNovaSenha(getUsuarioSolicitacaoSenha().getEmailFunc(), getNovaSenha(), getUsuarioSolicitacaoSenha())) {
                        mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Informa:", "Solicitação realizada com sucesso, enviamos um e-mail com sua nova senha."));                        
                        context.execute("PF('dlSolicitarSenha').hide()");

                    } else {
                        mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepaProtocol Informa:", "Erro, Solicitação não concluida."));                        
                        context.execute("PF('dlSolicitarSenha').hide()");

                    }
                } else {
                    mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepaProtocol Informa:", "Erro, Solicitação não concluida."));                    
                    context.execute("PF('dlSolicitarSenha').hide()");

                }
            } else {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepaProtocol Informa:", "Usuário não encontrado."));               
                context.execute("PF('dlSolicitarSenha').hide()");

            }

        } catch (Exception e) {
            System.out.println("Erro no método solicitarAlteraSenha() " + e.getMessage());
            e.printStackTrace();
            ret = null;
        }

        return ret;
    }

    public Boolean enviarEmailNovaSenha(String emailDestinatario, String novaSenha, CadFuncionario obj) {

        try {
            String assunto = "Solicitação de nova senha";

            SimpleDateFormat formate = new SimpleDateFormat("dd/MM/yyyy");

            StringBuilder corpoEmailAbertura = new StringBuilder();
            corpoEmailAbertura.append("<p style='font-family: Arial, Helvetica, sans-serif; font-size: 13px; font-weight: normal;'>SINDMEPA informa,<br />");
            corpoEmailAbertura.append("Sua senha foi alterada com sucesso, segue nova senha de acesso ao sistema: <br /><br />");
            corpoEmailAbertura.append("<strong>Usuário: </strong>");
            corpoEmailAbertura.append(obj.getNmFunc());
            corpoEmailAbertura.append("<br />");
            corpoEmailAbertura.append("<strong>nova senha: </strong>");
            corpoEmailAbertura.append(novaSenha);
            corpoEmailAbertura.append("<br />");
            corpoEmailAbertura.append("Para sua maior segurança sugerimos trocar a senha após acesso.");
            corpoEmailAbertura.append("<br /><br />");
            corpoEmailAbertura.append("<i>Email Enviado automaticamente pelo sistema ");
            corpoEmailAbertura.append("<br />");
            corpoEmailAbertura.append("Data: ");
            corpoEmailAbertura.append(formate.format(new Date()));
            corpoEmailAbertura.append("<br />");
            corpoEmailAbertura.append("Softbean ©");
            corpoEmailAbertura.append("</i></p>");

            mailUtil.enviar(assunto, emailDestinatario, corpoEmailAbertura.toString());

            return true;

        } catch (Exception e) {
            System.out.println("Erro no método  enviarEmailNovaSenha " + e.getMessage());
            e.printStackTrace();
            return false;
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

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
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

    public CadFuncionario getUsuarioSolicitacaoSenha() {
        return usuarioSolicitacaoSenha;
    }

    public void setUsuarioSolicitacaoSenha(CadFuncionario usuarioSolicitacaoSenha) {
        this.usuarioSolicitacaoSenha = usuarioSolicitacaoSenha;
    }

    public String getCpfSolicitacao() {
        return cpfSolicitacao;
    }

    public void setCpfSolicitacao(String cpfSolicitacao) {
        this.cpfSolicitacao = cpfSolicitacao;
    }

}
