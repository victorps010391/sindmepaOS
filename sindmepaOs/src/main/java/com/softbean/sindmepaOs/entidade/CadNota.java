/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.entidade;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Victor
 */
@Entity
@Table(name = "cad_nota")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CadNota.findAll", query = "SELECT c FROM CadNota c")
    , @NamedQuery(name = "CadNota.findByNrOsNota", query = "SELECT c FROM CadNota c WHERE c.cadNotaPK.nrOsNota = :nrOsNota")
    , @NamedQuery(name = "CadNota.findBySerialNota", query = "SELECT c FROM CadNota c WHERE c.cadNotaPK.serialNota = :serialNota")
    , @NamedQuery(name = "CadNota.findByHistNota", query = "SELECT c FROM CadNota c WHERE c.histNota = :histNota")
    , @NamedQuery(name = "CadNota.findByDtRegiNota", query = "SELECT c FROM CadNota c WHERE c.dtRegiNota = :dtRegiNota")
    , @NamedQuery(name = "CadNota.findByFuncRegiNota", query = "SELECT c FROM CadNota c WHERE c.funcRegiNota = :funcRegiNota")
    , @NamedQuery(name = "CadNota.findByDtUltAtuNota", query = "SELECT c FROM CadNota c WHERE c.dtUltAtuNota = :dtUltAtuNota")
    , @NamedQuery(name = "CadNota.findByFuncUltAtuNota", query = "SELECT c FROM CadNota c WHERE c.funcUltAtuNota = :funcUltAtuNota")})
public class CadNota implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CadNotaPK cadNotaPK;
    @Size(max = 2147483647)
    @Column(name = "hist_nota")
    private String histNota;
    @Column(name = "dt_regi_nota")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtRegiNota;
    @Column(name = "func_regi_nota")
    private Integer funcRegiNota;
    @Column(name = "dt_ult_atu_nota")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtUltAtuNota;
    @Column(name = "func_ult_atu_nota")
    private Integer funcUltAtuNota;

    public CadNota() {
    }

    public CadNota(CadNotaPK cadNotaPK) {
        this.cadNotaPK = cadNotaPK;
    }

    public CadNota(int nrOsNota, int serialNota) {
        this.cadNotaPK = new CadNotaPK(nrOsNota, serialNota);
    }

    public CadNotaPK getCadNotaPK() {
        return cadNotaPK;
    }

    public void setCadNotaPK(CadNotaPK cadNotaPK) {
        this.cadNotaPK = cadNotaPK;
    }

    public String getHistNota() {
        return histNota;
    }

    public void setHistNota(String histNota) {
        this.histNota = histNota;
    }

    public Date getDtRegiNota() {
        return dtRegiNota;
    }

    public void setDtRegiNota(Date dtRegiNota) {
        this.dtRegiNota = dtRegiNota;
    }

    public Integer getFuncRegiNota() {
        return funcRegiNota;
    }

    public void setFuncRegiNota(Integer funcRegiNota) {
        this.funcRegiNota = funcRegiNota;
    }

    public Date getDtUltAtuNota() {
        return dtUltAtuNota;
    }

    public void setDtUltAtuNota(Date dtUltAtuNota) {
        this.dtUltAtuNota = dtUltAtuNota;
    }

    public Integer getFuncUltAtuNota() {
        return funcUltAtuNota;
    }

    public void setFuncUltAtuNota(Integer funcUltAtuNota) {
        this.funcUltAtuNota = funcUltAtuNota;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cadNotaPK != null ? cadNotaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CadNota)) {
            return false;
        }
        CadNota other = (CadNota) object;
        if ((this.cadNotaPK == null && other.cadNotaPK != null) || (this.cadNotaPK != null && !this.cadNotaPK.equals(other.cadNotaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softbean.sindmepaOs.entidade.CadNota[ cadNotaPK=" + cadNotaPK + " ]";
    }
    
}
