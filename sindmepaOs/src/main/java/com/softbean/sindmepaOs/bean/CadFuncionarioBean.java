/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.bean;

import com.softbean.sindmepaOs.controle.CadFuncionarioControle;
import com.softbean.sindmepaOs.controle.CadOsControle;
import com.softbean.sindmepaOs.controle.CadSetorControle;
import com.softbean.sindmepaOs.entidade.CadFuncionario;
import com.softbean.sindmepaOs.entidade.CadFuncionarioPK;
import com.softbean.sindmepaOs.util.Util;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Raphael
 */
@Named(value = "cadFuncionarioBean")
@SessionScoped
public class CadFuncionarioBean implements Serializable {

    /**
     * Creates a new instance of CadFuncionarioBean
     */
    public CadFuncionarioBean() {
    }

    @Inject
    CadFuncionarioControle funcionarioControle;
    @Inject
    CadSetorControle setorControle;
    @Inject
    Util util;
    @Inject
    CadOsControle osControle;
    @Inject
    LoginBean loginBean;

    CadFuncionario objFunc;
    CadFuncionario usuAltSenhaObj;
    CadFuncionarioPK objfuncPK;

    String nome, cpf, email, senhaAcess, repetirSenhaAcess;
    Date dataNascimento;
    Integer codSetor;

    List<Map<String, Object>> setorResponsFunc;

    public void salvarCadFuncionario() {        
        FacesContext mensagem = FacesContext.getCurrentInstance();
        try {
            if (getObjFunc().getCadFuncionarioPK() == null) {
                setObjfuncPK(new CadFuncionarioPK());
                getObjfuncPK().setCdFunc(funcionarioControle.retornaCdFunc());
                getObjfuncPK().setCpfFunc(getCpf());

                setObjFunc(new CadFuncionario());
                getObjFunc().setNmFunc(getNome().toUpperCase());
                getObjFunc().setDtNascFunc(getDataNascimento());
                getObjFunc().setSetorFunc(setorControle.buscarSetor(getCodSetor()));
                getObjFunc().setEmailFunc(getEmail());
                getObjFunc().setDtRegFunc(new Date());
                getObjFunc().setFuncRegFunc(999);
                getObjFunc().setDtUltAtuFunc(new Date());
                getObjFunc().setFuncUltAtuFunc(999);
                getObjFunc().setSenhaFunc(util.converteParaMd5("102030"));

                if (funcionarioControle.verificaCpfCadastrado(getCpf()) == 0) {
                    if (util.emailValido(getEmail())) {
                        if (util.CPFcorreto(getCpf())) {
                            if (funcionarioControle.salvarFuncioControle(getObjFunc(), getObjfuncPK())) {
                                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepOS Informa:", "Cadastro do Colaborador Realizado com Sucesso."));
                                limparCadastro();                                
                            } else {
                                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepOS Informa:", "Erro ao Cadastrar Colaborador."));
                            }
                        } else {
                            mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepOS Informa:", "O CPF informado é inválido."));
                        }
                    } else {
                        mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepOS Informa:", "O e-mail informado é inválido."));
                    }
                } else {
                    mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepOS Informa:", "Já existe cadastro para o CPF informado."));
                }
            }
        } catch (Exception e) {
            System.out.println("Erro metódo salvarCadFuncionario " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Map<String, Object>> listarSetorPesq() {
        try {
            setSetorResponsFunc(funcionarioControle.listarSetorPesq());
        } catch (Exception e) {
            System.out.println("Erro no metodo listarSetorPesq");
        }
        return getSetorResponsFunc();
    }

    public void limparCadastro() {
        setCpf(null);
        setNome(null);
        setDataNascimento(null);
        setCodSetor(null);
        setSetorResponsFunc(null);
        setEmail(null);

    }

    public String alterarSenha() {
        FacesContext mensagem = FacesContext.getCurrentInstance();
        String ret = null;
        setUsuAltSenhaObj(null);
        setUsuAltSenhaObj(funcionarioControle.retornaUsuario(loginBean.getUsuario().getCadFuncionarioPK().getCpfFunc(), 
                                                             loginBean.getUsuario().getSenhaFunc()));
        if (!getSenhaAcess().equals(getRepetirSenhaAcess())) {
            mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepProtocol Informa:", "Erro ao Validar Senha (Senhas Diferentes)."));

        } else if (getUsuAltSenhaObj() != null) {
            getUsuAltSenhaObj().setSenhaFunc(util.converteParaMd5(getSenhaAcess()));
            getUsuAltSenhaObj().setDtUltAtuFunc(new Date());
            getUsuAltSenhaObj().setFuncUltAtuFunc(999);
            if (funcionarioControle.alterarSenha(getUsuAltSenhaObj())) {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepProtocol Informa:", "Senha alterada com sucesso. acesse o sistema com sua nova senha."));
                limparPrimeiroAcesso();
                ret = loginBean.logOff();
            } else {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepProtocol Informa:", "Ocorreu um erro ao tentarmos alterar sua senha."));
            }
        }
        return ret;
    }

    public void limparPrimeiroAcesso() {
        setSenhaAcess(null);
        setRepetirSenhaAcess(null);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getCodSetor() {
        return codSetor;
    }

    public void setCodSetor(Integer codSetor) {
        this.codSetor = codSetor;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
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

    public CadFuncionario getObjFunc() {
        if (objFunc == null) {
            objFunc = new CadFuncionario();
        }
        return objFunc;
    }

    public CadFuncionario getUsuAltSenhaObj() {
        return usuAltSenhaObj;
    }

    public void setUsuAltSenhaObj(CadFuncionario usuAltSenhaObj) {
        this.usuAltSenhaObj = usuAltSenhaObj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setObjFunc(CadFuncionario objFunc) {
        this.objFunc = objFunc;
    }

    public List<Map<String, Object>> getSetorResponsFunc() {
        return setorResponsFunc;
    }

    public void setSetorResponsFunc(List<Map<String, Object>> setorResponsFunc) {
        this.setorResponsFunc = setorResponsFunc;
    }

    public CadFuncionarioPK getObjfuncPK() {
        if (objfuncPK == null) {
            objfuncPK = new CadFuncionarioPK();
        }
        return objfuncPK;
    }

    public void setObjfuncPK(CadFuncionarioPK objfuncPK) {
        this.objfuncPK = objfuncPK;
    }
}
