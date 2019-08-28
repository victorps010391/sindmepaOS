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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author Victor
 */
@Entity
@Table(name = "cad_os")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CadOs.findAll", query = "SELECT c FROM CadOs c")
    , @NamedQuery(name = "CadOs.findByIdOs", query = "SELECT c FROM CadOs c WHERE c.cadOsPK.idOs = :idOs")
    , @NamedQuery(name = "CadOs.findByNrOs", query = "SELECT c FROM CadOs c WHERE c.cadOsPK.nrOs = :nrOs")
    , @NamedQuery(name = "CadOs.findByCategOs", query = "SELECT c FROM CadOs c WHERE c.cadOsPK.categOs = :categOs")
    , @NamedQuery(name = "CadOs.findByFuncAbertOs", query = "SELECT c FROM CadOs c WHERE c.funcAbertOs = :funcAbertOs")
    , @NamedQuery(name = "CadOs.findByDtAbertOs", query = "SELECT c FROM CadOs c WHERE c.dtAbertOs = :dtAbertOs")
    , @NamedQuery(name = "CadOs.findByFuncResponOs", query = "SELECT c FROM CadOs c WHERE c.funcResponOs = :funcResponOs")
    , @NamedQuery(name = "CadOs.findByDtFechaOs", query = "SELECT c FROM CadOs c WHERE c.dtFechaOs = :dtFechaOs")
    , @NamedQuery(name = "CadOs.findByHistOs", query = "SELECT c FROM CadOs c WHERE c.histOs = :histOs")
    , @NamedQuery(name = "CadOs.findByObsOs", query = "SELECT c FROM CadOs c WHERE c.obsOs = :obsOs")
    , @NamedQuery(name = "CadOs.findBySitOs", query = "SELECT c FROM CadOs c WHERE c.sitOs = :sitOs")
    , @NamedQuery(name = "CadOs.findByDtUltAtuOs", query = "SELECT c FROM CadOs c WHERE c.dtUltAtuOs = :dtUltAtuOs")
    , @NamedQuery(name = "CadOs.findByFuncUltAtuOs", query = "SELECT c FROM CadOs c WHERE c.funcUltAtuOs = :funcUltAtuOs")})
public class CadOs implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CadOsPK cadOsPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "func_abert_os")
    private int funcAbertOs;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dt_abert_os")
    @Temporal(TemporalType.DATE)
    private Date dtAbertOs;
    @Column(name = "func_respon_os")
    private Integer funcResponOs;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dt_fecha_os")
    @Temporal(TemporalType.DATE)
    private Date dtFechaOs;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "hist_os")
    private String histOs;
    @Size(max = 512)
    @Column(name = "obs_os")
    private String obsOs;
    @Size(max = 2)
    @Column(name = "sit_os")
    private String sitOs;
    @Column(name = "dt_ult_atu_os")
    @Temporal(TemporalType.DATE)
    private Date dtUltAtuOs;
    @Column(name = "func_ult_atu_os")
    private Integer funcUltAtuOs;
    @JoinColumn(name = "setor_os", referencedColumnName = "cd_setor")
    @ManyToOne(optional = false)
    private CadSetor setorOs;
    @JoinColumn(name = "setor_respon_os", referencedColumnName = "cd_setor")
    @ManyToOne(optional = false)
    private CadSetor setorResponOs;

    public CadOs() {
    }

    public CadOs(CadOsPK cadOsPK) {
        this.cadOsPK = cadOsPK;
    }

    public CadOs(CadOsPK cadOsPK, int funcAbertOs, Date dtAbertOs, Date dtFechaOs, String histOs) {
        this.cadOsPK = cadOsPK;
        this.funcAbertOs = funcAbertOs;
        this.dtAbertOs = dtAbertOs;
        this.dtFechaOs = dtFechaOs;
        this.histOs = histOs;
    }

    public CadOs(int idOs, int nrOs, String categOs) {
        this.cadOsPK = new CadOsPK(idOs, nrOs, categOs);
    }

    public CadOsPK getCadOsPK() {
        return cadOsPK;
    }

    public void setCadOsPK(CadOsPK cadOsPK) {
        this.cadOsPK = cadOsPK;
    }

    public int getFuncAbertOs() {
        return funcAbertOs;
    }

    public void setFuncAbertOs(int funcAbertOs) {
        this.funcAbertOs = funcAbertOs;
    }

    public Date getDtAbertOs() {
        return dtAbertOs;
    }

    public void setDtAbertOs(Date dtAbertOs) {
        this.dtAbertOs = dtAbertOs;
    }

    public Integer getFuncResponOs() {
        return funcResponOs;
    }

    public void setFuncResponOs(Integer funcResponOs) {
        this.funcResponOs = funcResponOs;
    }

    public Date getDtFechaOs() {
        return dtFechaOs;
    }

    public void setDtFechaOs(Date dtFechaOs) {
        this.dtFechaOs = dtFechaOs;
    }

    public String getHistOs() {
        return histOs;
    }

    public void setHistOs(String histOs) {
        this.histOs = histOs;
    }

    public String getObsOs() {
        return obsOs;
    }

    public void setObsOs(String obsOs) {
        this.obsOs = obsOs;
    }

    public String getSitOs() {
        return sitOs;
    }

    public void setSitOs(String sitOs) {
        this.sitOs = sitOs;
    }

    public Date getDtUltAtuOs() {
        return dtUltAtuOs;
    }

    public void setDtUltAtuOs(Date dtUltAtuOs) {
        this.dtUltAtuOs = dtUltAtuOs;
    }

    public Integer getFuncUltAtuOs() {
        return funcUltAtuOs;
    }

    public void setFuncUltAtuOs(Integer funcUltAtuOs) {
        this.funcUltAtuOs = funcUltAtuOs;
    }

    public CadSetor getSetorOs() {
        return setorOs;
    }

    public void setSetorOs(CadSetor setorOs) {
        this.setorOs = setorOs;
    }

    public CadSetor getSetorResponOs() {
        return setorResponOs;
    }

    public void setSetorResponOs(CadSetor setorResponOs) {
        this.setorResponOs = setorResponOs;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cadOsPK != null ? cadOsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CadOs)) {
            return false;
        }
        CadOs other = (CadOs) object;
        if ((this.cadOsPK == null && other.cadOsPK != null) || (this.cadOsPK != null && !this.cadOsPK.equals(other.cadOsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softbean.sindmepaOs.entidade.CadOs[ cadOsPK=" + cadOsPK + " ]";
    }
    
}
