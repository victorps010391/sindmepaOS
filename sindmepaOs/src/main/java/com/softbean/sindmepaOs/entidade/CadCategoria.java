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
@Table(name = "cad_categoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CadCategoria.findAll", query = "SELECT c FROM CadCategoria c")
    , @NamedQuery(name = "CadCategoria.findByIdCategoria", query = "SELECT c FROM CadCategoria c WHERE c.idCategoria = :idCategoria")
    , @NamedQuery(name = "CadCategoria.findByDescCategoria", query = "SELECT c FROM CadCategoria c WHERE c.descCategoria = :descCategoria")
    , @NamedQuery(name = "CadCategoria.findByCodPriorCategoria", query = "SELECT c FROM CadCategoria c WHERE c.codPriorCategoria = :codPriorCategoria")
    , @NamedQuery(name = "CadCategoria.findByDtUltAtuCategoria", query = "SELECT c FROM CadCategoria c WHERE c.dtUltAtuCategoria = :dtUltAtuCategoria")
    , @NamedQuery(name = "CadCategoria.findByUsuUltAtuCategoria", query = "SELECT c FROM CadCategoria c WHERE c.usuUltAtuCategoria = :usuUltAtuCategoria")
    , @NamedQuery(name = "CadCategoria.findByDtRegCategoria", query = "SELECT c FROM CadCategoria c WHERE c.dtRegCategoria = :dtRegCategoria")
    , @NamedQuery(name = "CadCategoria.findByUsuRegCateg", query = "SELECT c FROM CadCategoria c WHERE c.usuRegCateg = :usuRegCateg")})
public class CadCategoria implements Serializable {

    @Column(name = "usu_categoria")
    private Character usuCategoria;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_categoria")
    private Integer idCategoria;
    @Size(max = 100)
    @Column(name = "desc_categoria")
    private String descCategoria;
    @Size(max = 2)
    @Column(name = "cod_prior_categoria")
    private String codPriorCategoria;
    @Column(name = "dt_ult_atu_categoria")
    @Temporal(TemporalType.DATE)
    private Date dtUltAtuCategoria;
    @Column(name = "usu_ult_atu_categoria")
    private Integer usuUltAtuCategoria;
    @Column(name = "dt_reg_categoria")
    @Temporal(TemporalType.DATE)
    private Date dtRegCategoria;
    @Column(name = "usu_reg_categ")
    private Integer usuRegCateg;

    public CadCategoria() {
    }

    public CadCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescCategoria() {
        return descCategoria;
    }

    public void setDescCategoria(String descCategoria) {
        this.descCategoria = descCategoria;
    }

    public String getCodPriorCategoria() {
        return codPriorCategoria;
    }

    public void setCodPriorCategoria(String codPriorCategoria) {
        this.codPriorCategoria = codPriorCategoria;
    }

    public Date getDtUltAtuCategoria() {
        return dtUltAtuCategoria;
    }

    public void setDtUltAtuCategoria(Date dtUltAtuCategoria) {
        this.dtUltAtuCategoria = dtUltAtuCategoria;
    }

    public Integer getUsuUltAtuCategoria() {
        return usuUltAtuCategoria;
    }

    public void setUsuUltAtuCategoria(Integer usuUltAtuCategoria) {
        this.usuUltAtuCategoria = usuUltAtuCategoria;
    }

    public Date getDtRegCategoria() {
        return dtRegCategoria;
    }

    public void setDtRegCategoria(Date dtRegCategoria) {
        this.dtRegCategoria = dtRegCategoria;
    }

    public Integer getUsuRegCateg() {
        return usuRegCateg;
    }

    public void setUsuRegCateg(Integer usuRegCateg) {
        this.usuRegCateg = usuRegCateg;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategoria != null ? idCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CadCategoria)) {
            return false;
        }
        CadCategoria other = (CadCategoria) object;
        if ((this.idCategoria == null && other.idCategoria != null) || (this.idCategoria != null && !this.idCategoria.equals(other.idCategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softbean.sindmepaOs.entidade.CadCategoria[ idCategoria=" + idCategoria + " ]";
    }

    public Character getUsuCategoria() {
        return usuCategoria;
    }

    public void setUsuCategoria(Character usuCategoria) {
        this.usuCategoria = usuCategoria;
    }
    
}
