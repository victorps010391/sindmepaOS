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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cdi_vsilva
 */
@Entity
@Table(name = "cad_detalhe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CadDetalhe.findAll", query = "SELECT c FROM CadDetalhe c")
    , @NamedQuery(name = "CadDetalhe.findByIdDetalhe", query = "SELECT c FROM CadDetalhe c WHERE c.idDetalhe = :idDetalhe")
    , @NamedQuery(name = "CadDetalhe.findByCodItemDetalhe", query = "SELECT c FROM CadDetalhe c WHERE c.codItemDetalhe = :codItemDetalhe")
    , @NamedQuery(name = "CadDetalhe.findByCodValorDetalhe", query = "SELECT c FROM CadDetalhe c WHERE c.codValorDetalhe = :codValorDetalhe")
    , @NamedQuery(name = "CadDetalhe.findByDtRegDetalhe", query = "SELECT c FROM CadDetalhe c WHERE c.dtRegDetalhe = :dtRegDetalhe")
    , @NamedQuery(name = "CadDetalhe.findByUsuRegDetalhe", query = "SELECT c FROM CadDetalhe c WHERE c.usuRegDetalhe = :usuRegDetalhe")
    , @NamedQuery(name = "CadDetalhe.findByDtUltAtuDetalhe", query = "SELECT c FROM CadDetalhe c WHERE c.dtUltAtuDetalhe = :dtUltAtuDetalhe")
    , @NamedQuery(name = "CadDetalhe.findByUsuUltAtuDetalhe", query = "SELECT c FROM CadDetalhe c WHERE c.usuUltAtuDetalhe = :usuUltAtuDetalhe")
    , @NamedQuery(name = "CadDetalhe.findByDescDetalhe", query = "SELECT c FROM CadDetalhe c WHERE c.descDetalhe = :descDetalhe")})
public class CadDetalhe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalhe")
    private Integer idDetalhe;
    @Size(max = 5)
    @Column(name = "cod_item_detalhe")
    private String codItemDetalhe;
    @Size(max = 100)
    @Column(name = "cod_valor_detalhe")
    private String codValorDetalhe;
    @Column(name = "dt_reg_detalhe")
    @Temporal(TemporalType.DATE)
    private Date dtRegDetalhe;
    @Column(name = "usu_reg_detalhe")
    private Integer usuRegDetalhe;
    @Column(name = "dt_ult_atu_detalhe")
    @Temporal(TemporalType.DATE)
    private Date dtUltAtuDetalhe;
    @Column(name = "usu_ult_atu_detalhe")
    private Integer usuUltAtuDetalhe;
    @Size(max = 100)
    @Column(name = "desc_detalhe")
    private String descDetalhe;

    public CadDetalhe() {
    }

    public CadDetalhe(Integer idDetalhe) {
        this.idDetalhe = idDetalhe;
    }

    public Integer getIdDetalhe() {
        return idDetalhe;
    }

    public void setIdDetalhe(Integer idDetalhe) {
        this.idDetalhe = idDetalhe;
    }

    public String getCodItemDetalhe() {
        return codItemDetalhe;
    }

    public void setCodItemDetalhe(String codItemDetalhe) {
        this.codItemDetalhe = codItemDetalhe;
    }

    public String getCodValorDetalhe() {
        return codValorDetalhe;
    }

    public void setCodValorDetalhe(String codValorDetalhe) {
        this.codValorDetalhe = codValorDetalhe;
    }

    public Date getDtRegDetalhe() {
        return dtRegDetalhe;
    }

    public void setDtRegDetalhe(Date dtRegDetalhe) {
        this.dtRegDetalhe = dtRegDetalhe;
    }

    public Integer getUsuRegDetalhe() {
        return usuRegDetalhe;
    }

    public void setUsuRegDetalhe(Integer usuRegDetalhe) {
        this.usuRegDetalhe = usuRegDetalhe;
    }

    public Date getDtUltAtuDetalhe() {
        return dtUltAtuDetalhe;
    }

    public void setDtUltAtuDetalhe(Date dtUltAtuDetalhe) {
        this.dtUltAtuDetalhe = dtUltAtuDetalhe;
    }

    public Integer getUsuUltAtuDetalhe() {
        return usuUltAtuDetalhe;
    }

    public void setUsuUltAtuDetalhe(Integer usuUltAtuDetalhe) {
        this.usuUltAtuDetalhe = usuUltAtuDetalhe;
    }

    public String getDescDetalhe() {
        return descDetalhe;
    }

    public void setDescDetalhe(String descDetalhe) {
        this.descDetalhe = descDetalhe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalhe != null ? idDetalhe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CadDetalhe)) {
            return false;
        }
        CadDetalhe other = (CadDetalhe) object;
        if ((this.idDetalhe == null && other.idDetalhe != null) || (this.idDetalhe != null && !this.idDetalhe.equals(other.idDetalhe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softbean.sindmepaOs.entidade.CadDetalhe[ idDetalhe=" + idDetalhe + " ]";
    }
    
}
