/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.bean;

import com.softbean.sindmepaOs.controle.CadFuncionarioControle;
import com.softbean.sindmepaOs.controle.CadSetorControle;
import com.softbean.sindmepaOs.entidade.CadFuncionario;
import com.softbean.sindmepaOs.entidade.CadFuncionarioPK;
import com.softbean.sindmepaOs.util.Util;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

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

    CadFuncionario objFunc;
    CadFuncionarioPK objfuncPK;

    String nome, cpf;
    Date dataNascimento;
    Integer codSetor;

    public void salvarCadFuncionario() {
        RequestContext context = RequestContext.getCurrentInstance();
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

                if (util.CPFcorreto(getCpf())) {
                    if (funcionarioControle.salvarFuncioControle(getObjFunc(), getObjfuncPK())) {
                        mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepOS Informa:", "Cadastro do Funcionário Realizado com Sucesso."));
                    } else {
                        mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepOS Informa:", "Erro ao Cadastrar Funcionário."));
                    }
                } else {
                    mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepOS Informa:", "O CPF informado é invalido."));
                }
            }
        } catch (Exception e) {
            System.out.println("Erro metódo salvarCadFuncionario " + e.getMessage());
            e.printStackTrace();
        }
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

    public CadFuncionario getObjFunc() {
        if (objFunc == null) {
            objFunc = new CadFuncionario();
        }
        return objFunc;
    }

    public void setObjFunc(CadFuncionario objFunc) {
        this.objFunc = objFunc;
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
