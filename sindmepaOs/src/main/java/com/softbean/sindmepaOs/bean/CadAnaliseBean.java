/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.bean;

import com.softbean.sindmepaOs.controle.CadAnaliseControle;
import com.softbean.sindmepaOs.util.ObjVerOsUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

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

    Integer nrOs;
    String priorPesq;
    List<Map<String, Object>> grid01;
    List<Map<String, Object>> grid02;
    List<Map<String, Object>> grid03;
    List<Map<String, Object>> priorListaPesq;
    List<Map<String, Object>> verOs;

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

    public void analise(Integer os) {
        try {
            setVerOs(analiseControle.verOs(os));
            
        } catch (Exception e) {
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
