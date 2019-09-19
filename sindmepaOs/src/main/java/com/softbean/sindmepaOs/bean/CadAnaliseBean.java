/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.bean;

import com.softbean.sindmepaOs.controle.CadAnaliseControle;
import com.softbean.sindmepaOs.controle.CadNotaControle;
import com.softbean.sindmepaOs.controle.CadOsControle;
import com.softbean.sindmepaOs.entidade.CadOs;
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
 * @author admin
 */
@Named(value = "cadAnaliseBean")
@SessionScoped
public class CadAnaliseBean implements Serializable {

    /**
     * Creates a new instance of CadAnalise
     */
    public CadAnaliseBean() {
    }

    @Inject
    CadAnaliseControle analiseControle;
    @Inject
    CadOsControle osControle;
    @Inject
    CadNotaControle notaControle;

    CadOs objOs;
    Integer nrOs;
    String priorPesq;
    List<Map<String, Object>> grid01;
    List<Map<String, Object>> grid02;
    List<Map<String, Object>> grid03;
    List<Map<String, Object>> priorListaPesq;
    List<Map<String, Object>> verOs;
    List<Map<String, Object>> listarFinalizacao;
    List<Map<String, Object>> notaAnalise;

    Integer Vos;
    String Vprioridade;
    String Vcategoria;
    String Vsetor_responsavel;
    String Vdata_hora_abert;
    String Vdata_hora_fecha;
    String Vsit;
    String Vcd_sit;
    String Vhistorico;
    String Vobservacao;
    String Vsetor_abertura;
    Integer Vcod_setor_abertura;
    Integer Vcod_categoria;
    String Vfunc_abert;
    String sitFinal;
    String descFinal;

    public void analise(Integer os) {
        try {
            setVerOs(null);
            setVerOs(analiseControle.verOs(os));
            for (Map<String, Object> elemento : getVerOs()) {
                setVcategoria((String) elemento.get("categoria"));
                setVos((Integer) elemento.get("os"));
                setVprioridade((String) elemento.get("prioridade"));
                setVsetor_responsavel((String) elemento.get("setor_responsavel"));
                setVdata_hora_abert((String) elemento.get("data_hora_abert"));
                setVdata_hora_fecha((String) elemento.get("data_hora_fecha"));
                setVsit((String) elemento.get("sit"));
                setVcd_sit((String) elemento.get("cd_sit"));
                setVhistorico((String) elemento.get("historico"));
                setVobservacao((String) elemento.get("observacao"));
                setVsetor_abertura((String) elemento.get("setor_abertura"));
                setVcod_setor_abertura((Integer) elemento.get("cod_setor_abert"));
                setVcod_categoria((Integer) elemento.get("cod_categoria"));
                setVfunc_abert((String) elemento.get("func_abert"));
            }
        } catch (Exception e) {
            System.out.println("Erro no método analise " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String iniciarAnalise() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext mensagem = FacesContext.getCurrentInstance();
        try {
            setObjOs(null);
            setObjOs(osControle.buscarOsControle(getVos()));
            getObjOs().setFuncUltAtuOs(999);
            getObjOs().setDtUltAtuOs(new Date());
            getObjOs().setFuncResponOs(999);
            getObjOs().setSitOs("03");
            if (osControle.alterarOsControle(getObjOs())) {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "SindmepaProtocol Informa:", "Análise do protocolo: " + getObjOs().getNrOs() + " Iniciada com Sucesso."));
                context.execute("PF('dlConfirm').hide()");
                setNotaAnalise(null);
                setNotaAnalise(notaControle.gridSecundario(getVos()));
                context.update(":frmAnaliseOs :gridNota");
                return "analise";
            } else {
                mensagem.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SindmepaProtocol Informa:", "Erro ao iniciar Análise do protocolo: " + getObjOs().getNrOs() + "."));
                return "";
            }
        } catch (Exception e) {
            System.out.println("Erro no método iniciarAnalise " + e.getMessage());
            e.printStackTrace();
            return "";
        }
    }

    public String analiseIniciada(Integer os) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext mensagem = FacesContext.getCurrentInstance();
        try {
            setVerOs(null);
            setVerOs(analiseControle.verOs(os));
            for (Map<String, Object> elemento : getVerOs()) {
                setVcategoria((String) elemento.get("categoria"));
                setVos((Integer) elemento.get("os"));
                setVprioridade((String) elemento.get("prioridade"));
                setVsetor_responsavel((String) elemento.get("setor_responsavel"));
                setVdata_hora_abert((String) elemento.get("data_hora_abert"));
                setVdata_hora_fecha((String) elemento.get("data_hora_fecha"));
                setVsit((String) elemento.get("sit"));
                setVcd_sit((String) elemento.get("cd_sit"));
                setVhistorico((String) elemento.get("historico"));
                setVobservacao((String) elemento.get("observacao"));
                setVsetor_abertura((String) elemento.get("setor_abertura"));
                setVcod_setor_abertura((Integer) elemento.get("cod_setor_abert"));
                setVcod_categoria((Integer) elemento.get("cod_categoria"));
                setVfunc_abert((String) elemento.get("func_abert"));
            }
            setNotaAnalise(null);
            setNotaAnalise(notaControle.gridSecundario(getVos()));
            context.update(":frmAnaliseOs :gridNota");
            return "analise";

        } catch (Exception e) {
            System.out.println("Erro no método analiseIniciada " + e.getMessage());
            e.printStackTrace();
            return "";
        }
    }

    public void pesquisar() {
        try {
            setGrid01(analiseControle.gridAnalise01(getNrOs(), getPriorPesq()));
            setGrid02(analiseControle.gridAnalise02(getNrOs(), getPriorPesq()));
            setGrid03(analiseControle.gridAnalise03(getNrOs(), getPriorPesq()));
        } catch (Exception e) {
            System.out.println("erro no método pesquisar(Analise) " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void pesquisarNota() {
        try {
            setNotaAnalise(null);
            setNotaAnalise(notaControle.gridSecundario(getVos()));
        } catch (Exception e) {
            System.out.println("Erro no método pesquisarNota " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Map<String, Object>> listarPriorOs() {
        try {
            setPriorListaPesq(analiseControle.listarPriorAnaliseOs());
        } catch (Exception e) {
            System.out.println("Erro no método listarPriorOs " + e.getMessage());
            e.printStackTrace();
        }
        return getPriorListaPesq();
    }

    public void limparPesquisa() {
        setPriorListaPesq(null);
        setNrOs(null);
        setGrid01(null);
        setGrid02(null);
        setGrid03(null);
    }

    public String dadosSindicais() {
        if (getVcod_categoria() == 7 && getVcod_setor_abertura() == 0) {
            return "true";
        } else {
            return "false";
        }
    }

    public String renderizarFinalização() {
        if ("05".equals(getVcd_sit()) || "07".equals(getVcd_sit())) {
            return "true";
        } else {
            return "false";
        }
    }

    public List<Map<String, Object>> listarFinalizacao() {
        try {
            setListarFinalizacao(analiseControle.listarSitFinalizacaoOs());
        } catch (Exception e) {
            System.out.println("Erro no metodo listarFinalizacao " + e.getMessage());
        }
        return getListarFinalizacao();
    }

    public CadOs getObjOs() {
        if (objOs == null) {
            objOs = new CadOs();
        }
        return objOs;
    }

    public void setObjOs(CadOs objOs) {
        this.objOs = objOs;
    }

    public String getDescFinal() {
        return descFinal;
    }

    public void setDescFinal(String descFinal) {
        this.descFinal = descFinal;
    }

    public String getSitFinal() {
        return sitFinal;
    }

    public void setSitFinal(String sitFinal) {
        this.sitFinal = sitFinal;
    }

    public Integer getNrOs() {
        return nrOs;
    }

    public void setNrOs(Integer nrOs) {
        this.nrOs = nrOs;
    }

    public String getPriorPesq() {
        return priorPesq;
    }

    public void setPriorPesq(String priorPesq) {
        this.priorPesq = priorPesq;
    }

    public List<Map<String, Object>> getNotaAnalise() {
        return notaAnalise;
    }

    public void setNotaAnalise(List<Map<String, Object>> notaAnalise) {
        this.notaAnalise = notaAnalise;
    }

    public List<Map<String, Object>> getListarFinalizacao() {
        return listarFinalizacao;
    }

    public void setListarFinalizacao(List<Map<String, Object>> listarFinalizacao) {
        this.listarFinalizacao = listarFinalizacao;
    }

    public List<Map<String, Object>> getGrid03() {
        return grid03;
    }

    public void setGrid03(List<Map<String, Object>> grid03) {
        this.grid03 = grid03;
    }

    public List<Map<String, Object>> getGrid02() {
        return grid02;
    }

    public void setGrid02(List<Map<String, Object>> grid02) {
        this.grid02 = grid02;
    }

    public List<Map<String, Object>> getGrid01() {
        return grid01;
    }

    public void setGrid01(List<Map<String, Object>> grid01) {
        this.grid01 = grid01;
    }

    public List<Map<String, Object>> getPriorListaPesq() {
        return priorListaPesq;
    }

    public void setPriorListaPesq(List<Map<String, Object>> priorListaPesq) {
        this.priorListaPesq = priorListaPesq;
    }

    public List<Map<String, Object>> getVerOs() {
        return verOs;
    }

    public void setVerOs(List<Map<String, Object>> verOs) {
        this.verOs = verOs;
    }

    public Integer getVos() {
        return Vos;
    }

    public void setVos(Integer Vos) {
        this.Vos = Vos;
    }

    public String getVprioridade() {
        return Vprioridade;
    }

    public void setVprioridade(String Vprioridade) {
        this.Vprioridade = Vprioridade;
    }

    public String getVcategoria() {
        return Vcategoria;
    }

    public void setVcategoria(String Vcategoria) {
        this.Vcategoria = Vcategoria;
    }

    public String getVsetor_responsavel() {
        return Vsetor_responsavel;
    }

    public void setVsetor_responsavel(String Vsetor_responsavel) {
        this.Vsetor_responsavel = Vsetor_responsavel;
    }

    public String getVdata_hora_abert() {
        return Vdata_hora_abert;
    }

    public void setVdata_hora_abert(String Vdata_hora_abert) {
        this.Vdata_hora_abert = Vdata_hora_abert;
    }

    public String getVdata_hora_fecha() {
        return Vdata_hora_fecha;
    }

    public void setVdata_hora_fecha(String Vdata_hora_fecha) {
        this.Vdata_hora_fecha = Vdata_hora_fecha;
    }

    public String getVsit() {
        return Vsit;
    }

    public void setVsit(String Vsit) {
        this.Vsit = Vsit;
    }

    public String getVcd_sit() {
        return Vcd_sit;
    }

    public void setVcd_sit(String Vcd_sit) {
        this.Vcd_sit = Vcd_sit;
    }

    public String getVhistorico() {
        return Vhistorico;
    }

    public void setVhistorico(String Vhistorico) {
        this.Vhistorico = Vhistorico;
    }

    public String getVobservacao() {
        return Vobservacao;
    }

    public void setVobservacao(String Vobservacao) {
        this.Vobservacao = Vobservacao;
    }

    public String getVsetor_abertura() {
        return Vsetor_abertura;
    }

    public void setVsetor_abertura(String Vsetor_abertura) {
        this.Vsetor_abertura = Vsetor_abertura;
    }

    public Integer getVcod_setor_abertura() {
        return Vcod_setor_abertura;
    }

    public void setVcod_setor_abertura(Integer Vcod_setor_abertura) {
        this.Vcod_setor_abertura = Vcod_setor_abertura;
    }

    public Integer getVcod_categoria() {
        return Vcod_categoria;
    }

    public void setVcod_categoria(Integer Vcod_categoria) {
        this.Vcod_categoria = Vcod_categoria;
    }

    public String getVfunc_abert() {
        return Vfunc_abert;
    }

    public void setVfunc_abert(String Vfunc_abert) {
        this.Vfunc_abert = Vfunc_abert;
    }

}
