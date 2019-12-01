/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.bean;

import com.softbean.sindmepaOs.controle.CadAnexoControle;
import com.softbean.sindmepaOs.entidade.CadAnexos;
import com.softbean.sindmepaOs.entidade.CadAnexosPK;
import com.softbean.sindmepaOs.util.Util;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Victor
 */
@Named(value = "cadAnexoBean")
@SessionScoped
public class CadAnexoBean implements Serializable {

    /**
     * Creates a new instance of CadAnexoBean
     */
    public CadAnexoBean() {
    }

    @Inject
    CadAnexoControle anexosControle;
    @Inject
    Util util;
    @Inject
    LoginBean loginBean;

    UploadedFile arquivo;
    CadAnexos objAnexo;
    CadAnexosPK objAnexoPk;
    String nome, paginaAnterior;
    Integer cod;
    Integer codOsAnexo;

    String dtRegistro;
    String usuRegistro;

    List<CadAnexos> gridPesquisaAnexo;
    List<Map<String, Object>> infomacoes;

    public void info(Integer nros) {
        setInfomacoes(null);
        setInfomacoes(anexosControle.InfoAnexo(nros));

        for (Map<String, Object> elemento : getInfomacoes()) {
            setDtRegistro((String) elemento.get("dt_regi_anexo"));
            setUsuRegistro((String) elemento.get("func_abert"));
        }
    }

    public void pesquisarAnexo() {
        try {
            setGridPesquisaAnexo(anexosControle.gridPrincipal(getNome(), getCod(), getCodOsAnexo()));
            info(getCodOsAnexo());
        } catch (Exception e) {
            System.out.println("ERRO no método pesquisar()");
            e.printStackTrace();
        }
    }

    public void limparPesquisaAnexo() {
        setCod(null);
        setNome(null);
        setGridPesquisaAnexo(null);
        setDtRegistro(null);
        setUsuRegistro(null);
        setInfomacoes(null);
    }

    public String paginaAnexoTarefa(String tarOs) {
        return paginaAnexo(Integer.parseInt(tarOs));
    }

    public String paginaAnexo(Integer codOs) {
        setCodOsAnexo(null);//limpar variavel
        setPaginaAnterior(null);// limpar variavel
        limparPesquisaAnexo();
        setCodOsAnexo(codOs);
        info(codOs);
        setPaginaAnterior(FacesContext.getCurrentInstance().getViewRoot().getViewId());
        setGridPesquisaAnexo(anexosControle.gridPrincipal("", null, codOs));
        RequestContext.getCurrentInstance().update(":frmCadAnexo");
        return "cadanexo.xhtml";
    }

    public String voltarAnexo() {
        return getPaginaAnterior();
    }

    public void uploadAnexo(FileUploadEvent evento) throws IOException {
        try {
            setArquivo(evento.getFile());
            byte[] arquivByte = util.retornoByteArquivo(evento.getFile().getInputstream());
            String extensao = getArquivo().getFileName().substring(getArquivo().getFileName().lastIndexOf("."), getArquivo().getFileName().length());
            setObjAnexoPk(null);

            if (getObjAnexo().getCadAnexosPK() == null) {
                setObjAnexoPk(new CadAnexosPK());
                getObjAnexoPk().setCodOsAnexo(getCodOsAnexo());
                getObjAnexoPk().setSeqAnexo(anexosControle.retornaSeqAnexo(getCodOsAnexo()));

                setObjAnexo(new CadAnexos());
                getObjAnexo().setBlobArqAnexo(arquivByte);
                getObjAnexo().setNmArqAnexo(getArquivo().getFileName());
                getObjAnexo().setExtArqAnexo(extensao);
                getObjAnexo().setPagArqAnexo(getPaginaAnterior());
                getObjAnexo().setDtRegiAnexo(new Date());
                getObjAnexo().setFuncRegiAnexo(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());
                getObjAnexo().setDtUltAtuAnexo(new Date());
                getObjAnexo().setFuncUltAtuAnexo(loginBean.getUsuario().getCadFuncionarioPK().getCdFunc());

                salvar();
            }
        } catch (Exception e) {
            System.out.println("ERRO no metodo salvarAnexo");
            e.printStackTrace();
        }
    }

    public StreamedContent downloadAnexo(CadAnexos obj) {
        StreamedContent file = new DefaultStreamedContent();
        try {
            ByteArrayInputStream inpByt = new ByteArrayInputStream(obj.getBlobArqAnexo());
            InputStream stream = (InputStream) inpByt;
            file = new DefaultStreamedContent(stream, obj.getExtArqAnexo(), obj.getNmArqAnexo());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    public void excluirAnexo(CadAnexos obj) {
        try {
            if (anexosControle.excluirControle(obj)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Informa:", "Anexo Excluido com Sucesso."));
                setGridPesquisaAnexo(null);
                setGridPesquisaAnexo(anexosControle.gridPrincipal("", null, obj.getCadAnexosPK().getCodOsAnexo()));
                info(obj.getCadAnexosPK().getCodOsAnexo());
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepaProtocol Informa:", "Erro ao Excluir Anexo."));
            }
        } catch (Exception e) {
            System.out.println("ERRO no método excluirAnexo");
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepaProtocol Informa:", "Erro ao Excluir Anexo."));
        }
    }

    public void salvar() {
        RequestContext context = RequestContext.getCurrentInstance();
        if (anexosControle.salvarControle(getObjAnexo(), getObjAnexoPk())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Informa:", "Cadastro do Anexo " + getObjAnexo().getNmArqAnexo() + " Realizado Com Sucesso."));
            setGridPesquisaAnexo(anexosControle.gridPrincipal(getObjAnexo().getNmArqAnexo(), getObjAnexo().getCadAnexosPK().getSeqAnexo(), getObjAnexo().getCadAnexosPK().getCodOsAnexo()));
            info(getObjAnexo().getCadAnexosPK().getCodOsAnexo());
            context.execute("PF('dlCadAnex').hide()");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepaProtocol Informa:", "Não Foi Possivel Realizar o Cadastro do Anexo."));
        }
    }

    public String getDtRegistro() {
        return dtRegistro;
    }

    public void setDtRegistro(String dtRegistro) {
        this.dtRegistro = dtRegistro;
    }

    public String getUsuRegistro() {
        return usuRegistro;
    }

    public void setUsuRegistro(String usuRegistro) {
        this.usuRegistro = usuRegistro;
    }

    public List<Map<String, Object>> getInfomacoes() {
        return infomacoes;
    }

    public void setInfomacoes(List<Map<String, Object>> infomacoes) {
        this.infomacoes = infomacoes;
    }

    public Integer getCodOsAnexo() {
        return codOsAnexo;
    }

    public void setCodOsAnexo(Integer codOsAnexo) {
        this.codOsAnexo = codOsAnexo;
    }

    public UploadedFile getArquivo() {
        return arquivo;
    }

    public void setArquivo(UploadedFile arquivo) {
        this.arquivo = arquivo;
    }

    public CadAnexos getObjAnexo() {
        if (objAnexo == null) {
            objAnexo = new CadAnexos();
        }
        return objAnexo;
    }

    public void setObjAnexo(CadAnexos objAnexo) {
        this.objAnexo = objAnexo;
    }

    public CadAnexosPK getObjAnexoPk() {
        if (objAnexoPk == null) {
            objAnexoPk = new CadAnexosPK();
        }
        return objAnexoPk;
    }

    public void setObjAnexoPk(CadAnexosPK objAnexoPk) {
        this.objAnexoPk = objAnexoPk;
    }

    public String getPaginaAnterior() {
        return paginaAnterior;
    }

    public void setPaginaAnterior(String paginaAnterior) {
        this.paginaAnterior = paginaAnterior;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public List<CadAnexos> getGridPesquisaAnexo() {
        return gridPesquisaAnexo;
    }

    public void setGridPesquisaAnexo(List<CadAnexos> gridPesquisaAnexo) {
        this.gridPesquisaAnexo = gridPesquisaAnexo;
    }

}
