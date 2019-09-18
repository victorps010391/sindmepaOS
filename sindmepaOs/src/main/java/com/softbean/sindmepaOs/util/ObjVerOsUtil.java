/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.util;

import java.util.Objects;

/**
 *
 * @author admin
 */
public class ObjVerOsUtil {
    
    private Integer os;
    private String prioridade;
    private String categoria;
    private String setor_responsavel;
    private String data_hora_abert;
    private String data_hora_fecha;
    private String sit;
    private String cd_sit;
    private String historico;
    private String observacao;
    private String setor_abertura;
    private Integer cod_setor_abertura;
    private Integer cod_categoria;
    private String func_abert;

    public Integer getOs() {
        return os;
    }

    public void setOs(Integer os) {
        this.os = os;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSetor_responsavel() {
        return setor_responsavel;
    }

    public void setSetor_responsavel(String setor_responsavel) {
        this.setor_responsavel = setor_responsavel;
    }

    public String getData_hora_abert() {
        return data_hora_abert;
    }

    public void setData_hora_abert(String data_hora_abert) {
        this.data_hora_abert = data_hora_abert;
    }

    public String getData_hora_fecha() {
        return data_hora_fecha;
    }

    public void setData_hora_fecha(String data_hora_fecha) {
        this.data_hora_fecha = data_hora_fecha;
    }

    public String getSit() {
        return sit;
    }

    public void setSit(String sit) {
        this.sit = sit;
    }

    public String getCd_sit() {
        return cd_sit;
    }

    public void setCd_sit(String cd_sit) {
        this.cd_sit = cd_sit;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getSetor_abertura() {
        return setor_abertura;
    }

    public void setSetor_abertura(String setor_abertura) {
        this.setor_abertura = setor_abertura;
    }

    public Integer getCod_setor_abertura() {
        return cod_setor_abertura;
    }

    public void setCod_setor_abertura(Integer cod_setor_abertura) {
        this.cod_setor_abertura = cod_setor_abertura;
    }

    public Integer getCod_categoria() {
        return cod_categoria;
    }

    public void setCod_categoria(Integer cod_categoria) {
        this.cod_categoria = cod_categoria;
    }

    public String getFunc_abert() {
        return func_abert;
    }

    public void setFunc_abert(String func_abert) {
        this.func_abert = func_abert;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.os);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ObjVerOsUtil other = (ObjVerOsUtil) obj;
        if (!Objects.equals(this.prioridade, other.prioridade)) {
            return false;
        }
        if (!Objects.equals(this.categoria, other.categoria)) {
            return false;
        }
        if (!Objects.equals(this.setor_responsavel, other.setor_responsavel)) {
            return false;
        }
        if (!Objects.equals(this.data_hora_abert, other.data_hora_abert)) {
            return false;
        }
        if (!Objects.equals(this.data_hora_fecha, other.data_hora_fecha)) {
            return false;
        }
        if (!Objects.equals(this.sit, other.sit)) {
            return false;
        }
        if (!Objects.equals(this.cd_sit, other.cd_sit)) {
            return false;
        }
        if (!Objects.equals(this.historico, other.historico)) {
            return false;
        }
        if (!Objects.equals(this.observacao, other.observacao)) {
            return false;
        }
        if (!Objects.equals(this.setor_abertura, other.setor_abertura)) {
            return false;
        }
        if (!Objects.equals(this.func_abert, other.func_abert)) {
            return false;
        }
        if (!Objects.equals(this.os, other.os)) {
            return false;
        }
        if (!Objects.equals(this.cod_setor_abertura, other.cod_setor_abertura)) {
            return false;
        }
        if (!Objects.equals(this.cod_categoria, other.cod_categoria)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ObjVerOsUtil{" + "os=" + os + ", prioridade=" + prioridade + ", categoria=" + categoria + ", setor_responsavel=" + setor_responsavel + ", data_hora_abert=" + data_hora_abert + ", data_hora_fecha=" + data_hora_fecha + ", sit=" + sit + ", cd_sit=" + cd_sit + ", historico=" + historico + ", observacao=" + observacao + ", setor_abertura=" + setor_abertura + ", cod_setor_abertura=" + cod_setor_abertura + ", cod_categoria=" + cod_categoria + ", func_abert=" + func_abert + '}';
    }            
}
