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
import javax.persistence.Entity;
import javax.persistence.Id;
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
 * @author admin
 */
@Entity
@Table(name = "cad_os")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CadOs.findAll", query = "SELECT c FROM CadOs c"),
    @NamedQuery(name = "CadOs.findByNrOs", query = "SELECT c FROM CadOs c WHERE c.nrOs = :nrOs"),
    @NamedQuery(name = "CadOs.findByCategOs", query = "SELECT c FROM CadOs c WHERE c.categOs = :categOs"),
    @NamedQuery(name = "CadOs.findByFuncResponOs", query = "SELECT c FROM CadOs c WHERE c.funcResponOs = :funcResponOs"),
    @NamedQuery(name = "CadOs.findByFuncAbertOs", query = "SELECT c FROM CadOs c WHERE c.funcAbertOs = :funcAbertOs"),
    @NamedQuery(name = "CadOs.findByHistOs", query = "SELECT c FROM CadOs c WHERE c.histOs = :histOs"),
    @NamedQuery(name = "CadOs.findByObsOs", query = "SELECT c FROM CadOs c WHERE c.obsOs = :obsOs"),
    @NamedQuery(name = "CadOs.findBySitOs", query = "SELECT c FROM CadOs c WHERE c.sitOs = :sitOs"),
    @NamedQuery(name = "CadOs.findByTipEnvioOs", query = "SELECT c FROM CadOs c WHERE c.tipEnvioOs = :tipEnvioOs"),
    @NamedQuery(name = "CadOs.findByDtAbertOs", query = "SELECT c FROM CadOs c WHERE c.dtAbertOs = :dtAbertOs"),
    @NamedQuery(name = "CadOs.findByDtFechaOs", query = "SELECT c FROM CadOs c WHERE c.dtFechaOs = :dtFechaOs"),
    @NamedQuery(name = "CadOs.findByDtUltAtuOs", query = "SELECT c FROM CadOs c WHERE c.dtUltAtuOs = :dtUltAtuOs"),
    @NamedQuery(name = "CadOs.findByFuncUltAtuOs", query = "SELECT c FROM CadOs c WHERE c.funcUltAtuOs = :funcUltAtuOs")})
public class CadOs implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "func_ult_atu_os")
    private int funcUltAtuOs;

    @Basic(optional = false)
    @NotNull
    @Column(name = "categ_os")
    private int categOs;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "nr_os")
    private Integer nrOs;
    @Column(name = "func_respon_os")
    private Integer funcResponOs;
    @Basic(optional = false)
    @NotNull
    @Column(name = "func_abert_os")
    private int funcAbertOs;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "hist_os")
    private String histOs;
    @Size(max = 512)
    @Column(name = "obs_os")
    private String obsOs;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "sit_os")
    private String sitOs;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "tipo_envio_os")
    private String tipEnvioOs;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dt_abert_os")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtAbertOs;
    @Column(name = "dt_fecha_os")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtFechaOs;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dt_ult_atu_os")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtUltAtuOs;
    @JoinColumn(name = "setor_respon_os", referencedColumnName = "cd_setor")
    @ManyToOne(optional = false)
    private CadSetor setorResponOs;
    @JoinColumn(name = "setor_abert_os", referencedColumnName = "cd_setor")
    @ManyToOne(optional = false)
    private CadSetor setorAbertOs;

    public CadOs() {
    }

    public CadOs(Integer nrOs) {
        this.nrOs = nrOs;
    }

    public CadOs(Integer nrOs, int categOs, int funcAbertOs, String histOs, String sitOs, String tipEnvioOs, Date dtAbertOs, Date dtUltAtuOs) {
        this.nrOs = nrOs;
        this.categOs = categOs;
        this.funcAbertOs = funcAbertOs;
        this.histOs = histOs;
        this.sitOs = sitOs;
        this.tipEnvioOs = tipEnvioOs;
        this.dtAbertOs = dtAbertOs;
        this.dtUltAtuOs = dtUltAtuOs;
    }

    public Integer getNrOs() {
        return nrOs;
    }

    public void setNrOs(Integer nrOs) {
        this.nrOs = nrOs;
    }

    public int getCategOs() {
        return categOs;
    }

    public void setCategOs(int categOs) {
        this.categOs = categOs;
    }

    public Integer getFuncResponOs() {
        return funcResponOs;
    }

    public void setFuncResponOs(Integer funcResponOs) {
        this.funcResponOs = funcResponOs;
    }

    public int getFuncAbertOs() {
        return funcAbertOs;
    }

    public void setFuncAbertOs(int funcAbertOs) {
        this.funcAbertOs = funcAbertOs;
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

    public String getTipEnvioOs() {
        return tipEnvioOs;
    }

    public void setTipEnvioOs(String tipEnvioOs) {
        this.tipEnvioOs = tipEnvioOs;
    }

    public Date getDtAbertOs() {
        return dtAbertOs;
    }

    public void setDtAbertOs(Date dtAbertOs) {
        this.dtAbertOs = dtAbertOs;
    }

    public Date getDtFechaOs() {
        return dtFechaOs;
    }

    public void setDtFechaOs(Date dtFechaOs) {
        this.dtFechaOs = dtFechaOs;
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

    public CadSetor getSetorResponOs() {
        return setorResponOs;
    }

    public void setSetorResponOs(CadSetor setorResponOs) {
        this.setorResponOs = setorResponOs;
    }

    public CadSetor getSetorAbertOs() {
        return setorAbertOs;
    }

    public void setSetorAbertOs(CadSetor setorAbertOs) {
        this.setorAbertOs = setorAbertOs;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nrOs != null ? nrOs.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CadOs)) {
            return false;
        }
        CadOs other = (CadOs) object;
        if ((this.nrOs == null && other.nrOs != null) || (this.nrOs != null && !this.nrOs.equals(other.nrOs))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softbean.sindmepaOs.entidade.CadOs[ nrOs=" + nrOs + " ]";
    }
}
