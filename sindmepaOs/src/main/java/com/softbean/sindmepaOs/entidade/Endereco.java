/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.entidade;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Raphael
 */
@Entity
@Table(name = "endereco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Endereco.findAll", query = "SELECT e FROM Endereco e"),
    @NamedQuery(name = "Endereco.findByIdEnd", query = "SELECT e FROM Endereco e WHERE e.idEnd = :idEnd"),
    @NamedQuery(name = "Endereco.findByCepEnd", query = "SELECT e FROM Endereco e WHERE e.cepEnd = :cepEnd"),
    @NamedQuery(name = "Endereco.findByNumEnd", query = "SELECT e FROM Endereco e WHERE e.numEnd = :numEnd"),
    @NamedQuery(name = "Endereco.findByEndEnd", query = "SELECT e FROM Endereco e WHERE e.endEnd = :endEnd"),
    @NamedQuery(name = "Endereco.findByCompEnd", query = "SELECT e FROM Endereco e WHERE e.compEnd = :compEnd"),
    @NamedQuery(name = "Endereco.findByBairroExt", query = "SELECT e FROM Endereco e WHERE e.bairroExt = :bairroExt"),
    @NamedQuery(name = "Endereco.findByCidExt", query = "SELECT e FROM Endereco e WHERE e.cidExt = :cidExt"),
    @NamedQuery(name = "Endereco.findByEstExt", query = "SELECT e FROM Endereco e WHERE e.estExt = :estExt"),
    @NamedQuery(name = "Endereco.findByTelComExt", query = "SELECT e FROM Endereco e WHERE e.telComExt = :telComExt"),
    @NamedQuery(name = "Endereco.findByCelExt", query = "SELECT e FROM Endereco e WHERE e.celExt = :celExt"),
    @NamedQuery(name = "Endereco.findByWspExt", query = "SELECT e FROM Endereco e WHERE e.wspExt = :wspExt")})
public class Endereco implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_end")
    private Integer idEnd;
    @Size(max = 8)
    @Column(name = "cep_end")
    private String cepEnd;
    @Size(max = 5)
    @Column(name = "num_end")
    private String numEnd;
    @Size(max = 512)
    @Column(name = "end_end")
    private String endEnd;
    @Size(max = 512)
    @Column(name = "comp_end")
    private String compEnd;
    @Size(max = 512)
    @Column(name = "bairro_ext")
    private String bairroExt;
    @Size(max = 512)
    @Column(name = "cid_ext")
    private String cidExt;
    @Size(max = 10)
    @Column(name = "est_ext")
    private String estExt;
    @Size(max = 11)
    @Column(name = "tel_com_ext")
    private String telComExt;
    @Size(max = 11)
    @Column(name = "cel_ext")
    private String celExt;
    @Size(max = 11)
    @Column(name = "wsp_ext")
    private String wspExt;
    @OneToMany(mappedBy = "idEnd")
    private List<CadExterno> cadExternoList;

    public Endereco() {
    }

    public Endereco(Integer idEnd) {
        this.idEnd = idEnd;
    }

    public Integer getIdEnd() {
        return idEnd;
    }

    public void setIdEnd(Integer idEnd) {
        this.idEnd = idEnd;
    }

    public String getCepEnd() {
        return cepEnd;
    }

    public void setCepEnd(String cepEnd) {
        this.cepEnd = cepEnd;
    }

    public String getNumEnd() {
        return numEnd;
    }

    public void setNumEnd(String numEnd) {
        this.numEnd = numEnd;
    }

    public String getEndEnd() {
        return endEnd;
    }

    public void setEndEnd(String endEnd) {
        this.endEnd = endEnd;
    }

    public String getCompEnd() {
        return compEnd;
    }

    public void setCompEnd(String compEnd) {
        this.compEnd = compEnd;
    }

    public String getBairroExt() {
        return bairroExt;
    }

    public void setBairroExt(String bairroExt) {
        this.bairroExt = bairroExt;
    }

    public String getCidExt() {
        return cidExt;
    }

    public void setCidExt(String cidExt) {
        this.cidExt = cidExt;
    }

    public String getEstExt() {
        return estExt;
    }

    public void setEstExt(String estExt) {
        this.estExt = estExt;
    }

    public String getTelComExt() {
        return telComExt;
    }

    public void setTelComExt(String telComExt) {
        this.telComExt = telComExt;
    }

    public String getCelExt() {
        return celExt;
    }

    public void setCelExt(String celExt) {
        this.celExt = celExt;
    }

    public String getWspExt() {
        return wspExt;
    }

    public void setWspExt(String wspExt) {
        this.wspExt = wspExt;
    }

    @XmlTransient
    public List<CadExterno> getCadExternoList() {
        return cadExternoList;
    }

    public void setCadExternoList(List<CadExterno> cadExternoList) {
        this.cadExternoList = cadExternoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEnd != null ? idEnd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Endereco)) {
            return false;
        }
        Endereco other = (Endereco) object;
        if ((this.idEnd == null && other.idEnd != null) || (this.idEnd != null && !this.idEnd.equals(other.idEnd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softbean.sindmepaOs.entidade.Endereco[ idEnd=" + idEnd + " ]";
    }
    
}
