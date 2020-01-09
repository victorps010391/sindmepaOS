/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.bean;

import com.softbean.sindmepaOs.controle.CadCategoriaControle;
import com.softbean.sindmepaOs.entidade.CadCategoria;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

/**
 *
 * @author cdi_vsilva
 */
@Named(value = "cadCategoriaBean")
@SessionScoped
public class CadCategoriaBean implements Serializable {

    /**
     * Creates a new instance of CadCategoriaBean
     */
    public CadCategoriaBean() {
    }

    @Inject
    CadCategoriaControle categoriaControle;
    @Inject
    LoginBean loginBean;

    CadCategoria obj;

    Integer codCateg;
    String nomeCateg;
    String tipoCateg;
    Character usuExt;

    Integer codCategPesq;
    String nomeCategPesq;
    String tipoCategPesq;

    List<Map<String, Object>> gridPesquisa;
    List<Map<String, Object>> tipoCategLista;

    public void pesquisa() {
        try {
            setGridPesquisa(categoriaControle.gridPrincipal(getNomeCategPesq(), getCodCategPesq(), getTipoCategPesq()));
        } catch (Exception e) {
            System.out.println("Erro no método pesquisa (Categoria)");
        }
    }

    public void buscarCategoriaBean(Integer cod) {
        try {
            setObj(categoriaControle.buscarCategoria(cod));
        } catch (Exception e) {
            System.out.println("Erro no método buscarCategoriaBean");
            e.printStackTrace();
        }
    }

    public void limparPesquisa() {
        setCodCategPesq(null);
        setTipoCategPesq(null);
        setNomeCategPesq(null);
        setGridPesquisa(null);
    }
    
        public void limparCadastro() {        
        setTipoCateg(null);
        setNomeCateg(null);
        setUsuExt(null);
    }

    public List<Map<String, Object>> listarTipoCateg() {
        try {
            setTipoCategLista(categoriaControle.listarTipoCateg());
        } catch (Exception e) {
            System.out.println("Erro no método listarTipoCateg");
        }
        return getTipoCategLista();
    }

    public void salvar() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext mensagem = FacesContext.getCurrentInstance();
        try {
            setObj(null);//limpar variavel
            setObj(new CadCategoria());
            getObj().setCodPriorCategoria(getTipoCateg());
            getObj().setDescCategoria(getNomeCateg().toUpperCase());
            getObj().setDtRegCategoria(new Date());
            getObj().setDtUltAtuCategoria(new Date());
            getObj().setUsuRegCateg(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());
            getObj().setUsuUltAtuCategoria(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());
            getObj().setUsuCategoria(getUsuExt());
            if (categoriaControle.salvarCategoria(getObj())) {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Informa:", "Categoria salva com sucesso."));
                setGridPesquisa(categoriaControle.gridPrincipal(getNomeCateg(), getCodCateg(), getTipoCateg()));
                context.execute("PF('dlCadCateg').hide()");
            } else {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Informa:", "Erro ao salvar categoria"));
            }
        } catch (Exception e) {
            System.out.println("Erro no método salvar (categoria) " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void buscarCategoria(Integer cod) {
        try {
            setObj(null);//limpar variavel
            setObj(categoriaControle.buscarCategoria(cod));
        } catch (Exception e) {
            System.out.println("Erro o método buscarCategoria " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void alterarCategoria() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext mensagem = FacesContext.getCurrentInstance();
        try {
            getObj().setDtUltAtuCategoria(new Date());
            getObj().setUsuUltAtuCategoria(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());
            if (categoriaControle.alterarCategoria(getObj())) {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Informa:", "Categoria alterada com sucesso."));
                setGridPesquisa(categoriaControle.gridPrincipal(getObj().getDescCategoria(), getObj().getIdCategoria(), getObj().getCodPriorCategoria()));
                context.execute("PF('dlAltCateg').hide()");
            } else {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Informa:", "Erro ao alterar categoria"));
            }
        } catch (Exception e) {
            System.out.println("Erro no método alterarCategoria " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Character getUsuExt() {
        return usuExt;
    }

    public void setUsuExt(Character usuExt) {
        this.usuExt = usuExt;
    }

    public CadCategoria getObj() {
        if (obj == null) {
            obj = new CadCategoria();
        }
        return obj;
    }

    public void setObj(CadCategoria obj) {
        this.obj = obj;
    }

    public Integer getCodCateg() {
        return codCateg;
    }

    public void setCodCateg(Integer codCateg) {
        this.codCateg = codCateg;
    }

    public String getNomeCateg() {
        return nomeCateg;
    }

    public void setNomeCateg(String nomeCateg) {
        this.nomeCateg = nomeCateg;
    }

    public String getTipoCateg() {
        return tipoCateg;
    }

    public void setTipoCateg(String tipoCateg) {
        this.tipoCateg = tipoCateg;
    }

    public List<Map<String, Object>> getGridPesquisa() {
        return gridPesquisa;
    }

    public Integer getCodCategPesq() {
        return codCategPesq;
    }

    public void setCodCategPesq(Integer codCategPesq) {
        this.codCategPesq = codCategPesq;
    }

    public String getNomeCategPesq() {
        return nomeCategPesq;
    }

    public void setNomeCategPesq(String nomeCategPesq) {
        this.nomeCategPesq = nomeCategPesq;
    }

    public String getTipoCategPesq() {
        return tipoCategPesq;
    }

    public void setTipoCategPesq(String tipoCategPesq) {
        this.tipoCategPesq = tipoCategPesq;
    }

    public void setGridPesquisa(List<Map<String, Object>> gridPesquisa) {
        this.gridPesquisa = gridPesquisa;
    }

    public List<Map<String, Object>> getTipoCategLista() {
        return tipoCategLista;
    }

    public void setTipoCategLista(List<Map<String, Object>> tipoCategLista) {
        this.tipoCategLista = tipoCategLista;
    }

}
