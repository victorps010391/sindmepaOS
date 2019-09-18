/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.bean;

import com.softbean.sindmepaOs.controle.CadFuncionarioControle;
import com.softbean.sindmepaOs.entidade.CadFuncionario;
import com.softbean.sindmepaOs.entidade.CadSetor;
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
    CadFuncionario objFunc;
    String nome, cpf;
    Date dataNascimento;
    Integer codSetor;
   
    
    public void salvarCadFuncionario() {
//        RequestContext context = RequestContext.getCurrentInstance();
//        FacesContext mensagem = FacesContext.getCurrentInstance();
//        try {
//            setObjFunc(new CadFuncionario());
//            getObjFunc().setNmFunc(getNome().toUpperCase());
//            setObjFunc(new CadFuncionario(getCpf()));
//
//            if (setorControle.salvarSetorControle(getObjSetor())) {
//                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepOS Informa:", "Cadastro do Setor Realizado com Sucesso."));
//                context.execute("PF('dlCadSetor').hide()");
//                limparCadastro();
//                setGridPesquisa(setorControle.gridPrincipal(getObjSetor().getNmSetor(), getObjSetor().getCdSetor(), getObjSetor().getSitSetor()));
//            } else {
//                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepOS Informa:", "Erro ao Cadastrar Setor."));
//                limparCadastro();
//            }
//        } catch (Exception e) {
//            System.out.println("Erro metódo salvarSetorBean");
//            e.printStackTrace();
//        }
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
        return objFunc;
    }

    public void setObjFunc(CadFuncionario objFunc) {
        this.objFunc = objFunc;
    }  
}
