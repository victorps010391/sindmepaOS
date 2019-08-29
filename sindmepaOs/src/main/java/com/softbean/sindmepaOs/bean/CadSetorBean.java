/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.bean;

import com.softbean.sindmepaOs.controle.CadSetorControle;
import com.softbean.sindmepaOs.entidade.CadSetor;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Victor
 */
@Named(value = "cadSetorBean")
@SessionScoped
public class CadSetorBean implements Serializable {

    /**
     * Creates a new instance of CadSetorBean
     */
    public CadSetorBean() {
    }

    @Inject
    CadSetorControle setorControle;

    CadSetor objSetor;

    String nomeSetor;
    String sitSetor = "01";
    String nomeSetorPesq;
    String sitSetorPesq;
    Integer codSetorPesq;

    List<CadSetor> gridPesquisa;

    public void salvarSetorBean() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext mensagem = FacesContext.getCurrentInstance();
        try {
            setObjSetor(new CadSetor());
            getObjSetor().setNmSetor(getNomeSetor());
            getObjSetor().setSitSetor(getSitSetor());

            if (setorControle.salvarSetorControle(getObjSetor())) {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepOS Informa:", "Cadastro do Setor Realizado com Sucesso."));
                context.execute("PF('dlCadSetor').hide()");
                limparCadastro();                                
            } else {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepOS Informa:", "Erro ao Cadastrar Setor."));
                limparCadastro();
            }
        } catch (Exception e) {
            System.out.println("Erro metódo salvarSetorBean");
            e.printStackTrace();
        }
    }
    
    public void limparCadastro(){
        setNomeSetor(null);
        setSitSetor("01");
    }

    public CadSetor getObjSetor() {
        if (objSetor == null) {
            objSetor = new CadSetor();
        }
        return objSetor;
    }

    public void setObjSetor(CadSetor objSetor) {
        this.objSetor = objSetor;
    }

    public String getNomeSetor() {
        return nomeSetor;
    }

    public void setNomeSetor(String nomeSetor) {
        this.nomeSetor = nomeSetor;
    }

    public String getSitSetor() {
        return sitSetor;
    }

    public void setSitSetor(String sitSetor) {
        this.sitSetor = sitSetor;
    }

    public String getNomeSetorPesq() {
        return nomeSetorPesq;
    }

    public void setNomeSetorPesq(String nomeSetorPesq) {
        this.nomeSetorPesq = nomeSetorPesq;
    }

    public String getSitSetorPesq() {
        return sitSetorPesq;
    }

    public void setSitSetorPesq(String sitSetorPesq) {
        this.sitSetorPesq = sitSetorPesq;
    }

    public Integer getCodSetorPesq() {
        return codSetorPesq;
    }

    public void setCodSetorPesq(Integer codSetorPesq) {
        this.codSetorPesq = codSetorPesq;
    }
       
    public List<CadSetor> getGridPesquisa() {
        return gridPesquisa;
    }

    public void setGridPesquisa(List<CadSetor> gridPesquisa) {
        this.gridPesquisa = gridPesquisa;
    }

}
