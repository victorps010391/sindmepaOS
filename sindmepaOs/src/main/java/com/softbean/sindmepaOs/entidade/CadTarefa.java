/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.entidade;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "cad_tarefa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CadTarefa.findAll", query = "SELECT c FROM CadTarefa c"),
    @NamedQuery(name = "CadTarefa.findByNrOsTarefa", query = "SELECT c FROM CadTarefa c WHERE c.cadTarefaPK.nrOsTarefa = :nrOsTarefa"),
    @NamedQuery(name = "CadTarefa.findBySeqTarefa", query = "SELECT c FROM CadTarefa c WHERE c.cadTarefaPK.seqTarefa = :seqTarefa"),
    @NamedQuery(name = "CadTarefa.findBySetorAbertTarefa", query = "SELECT c FROM CadTarefa c WHERE c.setorAbertTarefa = :setorAbertTarefa"),
    @NamedQuery(name = "CadTarefa.findByFuncAbertTarefa", query = "SELECT c FROM CadTarefa c WHERE c.funcAbertTarefa = :funcAbertTarefa"),
    @NamedQuery(name = "CadTarefa.findByDtAbertTarefa", query = "SELECT c FROM CadTarefa c WHERE c.dtAbertTarefa = :dtAbertTarefa"),
    @NamedQuery(name = "CadTarefa.findBySetorResponTarefa", query = "SELECT c FROM CadTarefa c WHERE c.setorResponTarefa = :setorResponTarefa"),
    @NamedQuery(name = "CadTarefa.findByFuncResponTarefa", query = "SELECT c FROM CadTarefa c WHERE c.funcResponTarefa = :funcResponTarefa"),
    @NamedQuery(name = "CadTarefa.findByDtFechaTarefa", query = "SELECT c FROM CadTarefa c WHERE c.dtFechaTarefa = :dtFechaTarefa"),
    @NamedQuery(name = "CadTarefa.findByHistTarefa", query = "SELECT c FROM CadTarefa c WHERE c.histTarefa = :histTarefa"),
    @NamedQuery(name = "CadTarefa.findByObsTarefa", query = "SELECT c FROM CadTarefa c WHERE c.obsTarefa = :obsTarefa")})
public class CadTarefa implements Serializable {

    @Size(max = 512)
    @Column(name = "hist_fecha_tarefa")
    private String histFechaTarefa;

    @Size(max = 2)
    @Column(name = "sit_tarefa")
    private String sitTarefa;
    @Column(name = "dt_ult_atu_tarefa")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtUltAtuTarefa;
    @Column(name = "func_ult_atu_tarefa")
    private Integer funcUltAtuTarefa;

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CadTarefaPK cadTarefaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "setor_abert_tarefa")
    private int setorAbertTarefa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "func_abert_tarefa")
    private int funcAbertTarefa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dt_abert_tarefa")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtAbertTarefa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "setor_respon_tarefa")
    private int setorResponTarefa;
    @Column(name = "func_respon_tarefa")
    private Integer funcResponTarefa;
    @Basic(optional = false)    
    @Column(name = "dt_fecha_tarefa")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtFechaTarefa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "hist_tarefa")
    private String histTarefa;
    @Size(max = 512)
    @Column(name = "obs_tarefa")
    private String obsTarefa;

    public CadTarefa() {
    }

    public CadTarefa(CadTarefaPK cadTarefaPK) {
        this.cadTarefaPK = cadTarefaPK;
    }

    public CadTarefa(CadTarefaPK cadTarefaPK, int setorAbertTarefa, int funcAbertTarefa, Date dtAbertTarefa, int setorResponTarefa, Date dtFechaTarefa, String histTarefa) {
        this.cadTarefaPK = cadTarefaPK;
        this.setorAbertTarefa = setorAbertTarefa;
        this.funcAbertTarefa = funcAbertTarefa;
        this.dtAbertTarefa = dtAbertTarefa;
        this.setorResponTarefa = setorResponTarefa;
        this.dtFechaTarefa = dtFechaTarefa;
        this.histTarefa = histTarefa;
    }

    public CadTarefa(String nrOsTarefa, int seqTarefa) {
        this.cadTarefaPK = new CadTarefaPK(nrOsTarefa, seqTarefa);
    }

    public CadTarefaPK getCadTarefaPK() {
        return cadTarefaPK;
    }

    public void setCadTarefaPK(CadTarefaPK cadTarefaPK) {
        this.cadTarefaPK = cadTarefaPK;
    }

    public int getSetorAbertTarefa() {
        return setorAbertTarefa;
    }

    public void setSetorAbertTarefa(int setorAbertTarefa) {
        this.setorAbertTarefa = setorAbertTarefa;
    }

    public int getFuncAbertTarefa() {
        return funcAbertTarefa;
    }

    public void setFuncAbertTarefa(int funcAbertTarefa) {
        this.funcAbertTarefa = funcAbertTarefa;
    }

    public Date getDtAbertTarefa() {
        return dtAbertTarefa;
    }

    public void setDtAbertTarefa(Date dtAbertTarefa) {
        this.dtAbertTarefa = dtAbertTarefa;
    }

    public int getSetorResponTarefa() {
        return setorResponTarefa;
    }

    public void setSetorResponTarefa(int setorResponTarefa) {
        this.setorResponTarefa = setorResponTarefa;
    }

    public Integer getFuncResponTarefa() {
        return funcResponTarefa;
    }

    public void setFuncResponTarefa(Integer funcResponTarefa) {
        this.funcResponTarefa = funcResponTarefa;
    }

    public Date getDtFechaTarefa() {
        return dtFechaTarefa;
    }

    public void setDtFechaTarefa(Date dtFechaTarefa) {
        this.dtFechaTarefa = dtFechaTarefa;
    }

    public String getHistTarefa() {
        return histTarefa;
    }

    public void setHistTarefa(String histTarefa) {
        this.histTarefa = histTarefa;
    }

    public String getObsTarefa() {
        return obsTarefa;
    }

    public void setObsTarefa(String obsTarefa) {
        this.obsTarefa = obsTarefa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cadTarefaPK != null ? cadTarefaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CadTarefa)) {
            return false;
        }
        CadTarefa other = (CadTarefa) object;
        if ((this.cadTarefaPK == null && other.cadTarefaPK != null) || (this.cadTarefaPK != null && !this.cadTarefaPK.equals(other.cadTarefaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softbean.sindmepaOs.entidade.CadTarefa[ cadTarefaPK=" + cadTarefaPK + " ]";
    }

    public String getSitTarefa() {
        return sitTarefa;
    }

    public void setSitTarefa(String sitTarefa) {
        this.sitTarefa = sitTarefa;
    }

    public Date getDtUltAtuTarefa() {
        return dtUltAtuTarefa;
    }

    public void setDtUltAtuTarefa(Date dtUltAtuTarefa) {
        this.dtUltAtuTarefa = dtUltAtuTarefa;
    }

    public Integer getFuncUltAtuTarefa() {
        return funcUltAtuTarefa;
    }

    public void setFuncUltAtuTarefa(Integer funcUltAtuTarefa) {
        this.funcUltAtuTarefa = funcUltAtuTarefa;
    }

    public String getHistFechaTarefa() {
        return histFechaTarefa;
    }

    public void setHistFechaTarefa(String histFechaTarefa) {
        this.histFechaTarefa = histFechaTarefa;
    }
    
}
