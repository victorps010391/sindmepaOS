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
import java.util.List;
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

    UploadedFile arquivo;
    CadAnexos objAnexo;
    CadAnexosPK objAnexoPk;
    String nome;
    Integer cod;

    List<CadAnexos> gridPesquisa;
    
    
        public void pesquisar() {
        try {
            setGridPesquisa(anexosControle.gridPrincipal(getNome(), getCod()));
        } catch (Exception e) {
            System.out.println("ERRO no método pesquisar()");
            e.printStackTrace();
        }
    }

    public void limparPesquisa() {
        setCod(null);
        setNome(null);
        setGridPesquisa(null);
    }

    public void uploadAnexo(FileUploadEvent evento) throws IOException {
        try {
            setArquivo(evento.getFile());
            byte[] arquivByte = util.retornoByteArquivo(evento.getFile().getInputstream());
            String extensao = getArquivo().getFileName().substring(getArquivo().getFileName().lastIndexOf("."), getArquivo().getFileName().length());
            setObjAnexoPk(null);

            if (getObjAnexo().getCadAnexosPK() == null) {
                setObjAnexoPk(new CadAnexosPK());
                getObjAnexoPk().setIdAnexo(189);

                setObjAnexo(new CadAnexos());
                getObjAnexo().setBlobArqAnexo(arquivByte);
                getObjAnexo().setNmArqAnexo(getArquivo().getFileName());
                getObjAnexo().setExtArqAnexo(extensao);

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
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaOS Informa:", "Anexo Excluido com Sucesso."));
                setGridPesquisa(null);
                setGridPesquisa(anexosControle.gridPrincipal("", null));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepaOS Informa:", "Erro ao Excluir Anexo."));
            }
        } catch (Exception e) {
            System.out.println("ERRO no método excluirAnexo");
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepaOS Informa:", "Erro ao Excluir Anexo."));
        }
    }

    public void salvar() {
        RequestContext context = RequestContext.getCurrentInstance();
        if (anexosControle.salvarControle(getObjAnexo(), getObjAnexoPk())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaOS Informa:", "Cadastro do Anexo " + getObjAnexo().getNmArqAnexo() + " Realizado Com Sucesso."));
            context.execute("PF('dlCadAnex').hide()");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ADM Monobloco Informa:", "Não Foi Possivel Realizar o Cadastro do Anexo."));
        }
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

    public List<CadAnexos> getGridPesquisa() {
        return gridPesquisa;
    }

    public void setGridPesquisa(List<CadAnexos> gridPesquisa) {
        this.gridPesquisa = gridPesquisa;
    }

}
