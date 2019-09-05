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
 * @author Desenv
 */
@Entity
@Table(name = "endereco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Endereco.findAll", query = "SELECT e FROM Endereco e"),
    @NamedQuery(name = "Endereco.findByIdEnd", query = "SELECT e FROM Endereco e WHERE e.idEnd = :idEnd"),
    @NamedQuery(name = "Endereco.findByEndereco", query = "SELECT e FROM Endereco e WHERE e.endereco = :endereco"),
    @NamedQuery(name = "Endereco.findByCepEnd", query = "SELECT e FROM Endereco e WHERE e.cepEnd = :cepEnd"),
    @NamedQuery(name = "Endereco.findByNmEnd", query = "SELECT e FROM Endereco e WHERE e.nmEnd = :nmEnd"),
    @NamedQuery(name = "Endereco.findByBairroEnd", query = "SELECT e FROM Endereco e WHERE e.bairroEnd = :bairroEnd"),
    @NamedQuery(name = "Endereco.findByCidEnd", query = "SELECT e FROM Endereco e WHERE e.cidEnd = :cidEnd"),
    @NamedQuery(name = "Endereco.findByEstEnd", query = "SELECT e FROM Endereco e WHERE e.estEnd = :estEnd"),
    @NamedQuery(name = "Endereco.findByTelComEnd", query = "SELECT e FROM Endereco e WHERE e.telComEnd = :telComEnd"),
    @NamedQuery(name = "Endereco.findByCelEnd", query = "SELECT e FROM Endereco e WHERE e.celEnd = :celEnd"),
    @NamedQuery(name = "Endereco.findByWtpEnd", query = "SELECT e FROM Endereco e WHERE e.wtpEnd = :wtpEnd")})
public class Endereco implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_end")
    private Integer idEnd;
    @Size(max = 512)
    @Column(name = "endereco")
    private String endereco;
    @Size(max = 8)
    @Column(name = "cep_end")
    private String cepEnd;
    @Size(max = 10)
    @Column(name = "nm_end")
    private String nmEnd;
    @Size(max = 512)
    @Column(name = "bairro_end")
    private String bairroEnd;
    @Size(max = 512)
    @Column(name = "cid_end")
    private String cidEnd;
    @Size(max = 512)
    @Column(name = "est_end")
    private String estEnd;
    @Size(max = 15)
    @Column(name = "tel_com_end")
    private String telComEnd;
    @Size(max = 11)
    @Column(name = "cel_end")
    private String celEnd;
    @Size(max = 11)
    @Column(name = "wtp_end")
    private String wtpEnd;
    @OneToMany(mappedBy = "idEndExt")
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCepEnd() {
        return cepEnd;
    }

    public void setCepEnd(String cepEnd) {
        this.cepEnd = cepEnd;
    }

    public String getNmEnd() {
        return nmEnd;
    }

    public void setNmEnd(String nmEnd) {
        this.nmEnd = nmEnd;
    }

    public String getBairroEnd() {
        return bairroEnd;
    }

    public void setBairroEnd(String bairroEnd) {
        this.bairroEnd = bairroEnd;
    }

    public String getCidEnd() {
        return cidEnd;
    }

    public void setCidEnd(String cidEnd) {
        this.cidEnd = cidEnd;
    }

    public String getEstEnd() {
        return estEnd;
    }

    public void setEstEnd(String estEnd) {
        this.estEnd = estEnd;
    }

    public String getTelComEnd() {
        return telComEnd;
    }

    public void setTelComEnd(String telComEnd) {
        this.telComEnd = telComEnd;
    }

    public String getCelEnd() {
        return celEnd;
    }

    public void setCelEnd(String celEnd) {
        this.celEnd = celEnd;
    }

    public String getWtpEnd() {
        return wtpEnd;
    }

    public void setWtpEnd(String wtpEnd) {
        this.wtpEnd = wtpEnd;
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
