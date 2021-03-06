/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Raphael
 */
@Entity
@Table(name = "cad_funcionario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CadFuncionario.findAll", query = "SELECT c FROM CadFuncionario c"),
    @NamedQuery(name = "CadFuncionario.findByCpfFunc", query = "SELECT c FROM CadFuncionario c WHERE c.cadFuncionarioPK.cpfFunc = :cpfFunc"),
    @NamedQuery(name = "CadFuncionario.findByCdFunc", query = "SELECT c FROM CadFuncionario c WHERE c.cadFuncionarioPK.cdFunc = :cdFunc"),
    @NamedQuery(name = "CadFuncionario.findByNmFunc", query = "SELECT c FROM CadFuncionario c WHERE c.nmFunc = :nmFunc"),
    @NamedQuery(name = "CadFuncionario.findByDtNascFunc", query = "SELECT c FROM CadFuncionario c WHERE c.dtNascFunc = :dtNascFunc"),
    @NamedQuery(name = "CadFuncionario.findByEmailFunc", query = "SELECT c FROM CadFuncionario c WHERE c.emailFunc = :emailFunc"),
    @NamedQuery(name = "CadFuncionario.findBySenhaFunc", query = "SELECT c FROM CadFuncionario c WHERE c.senhaFunc = :senhaFunc")})
public class CadFuncionario implements Serializable {

    @Size(max = 8000)
    @Column(name = "senha_func")
    private String senhaFunc;

    @JoinColumn(name = "setor_func", referencedColumnName = "cd_setor")
    @ManyToOne(optional = false)
    private CadSetor setorFunc;

    @Size(max = 100)
    @Column(name = "email_func")
    private String emailFunc;
    @Column(name = "dt_reg_func")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtRegFunc;
    @Column(name = "dt_ult_atu_func")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtUltAtuFunc;
    @Column(name = "func_reg_func")
    private Integer funcRegFunc;
    @Column(name = "func_ult_atu_func")
    private Integer funcUltAtuFunc;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CadFuncionarioPK cadFuncionarioPK;
    @Size(max = 100)
    @Column(name = "nm_func")
    private String nmFunc;
    @Column(name = "dt_nasc_func")
    @Temporal(TemporalType.DATE)
    private Date dtNascFunc;

    private static final Logger LOG = Logger.getLogger(CadFuncionario.class.getName());

    public CadFuncionario() {
    }

    public CadFuncionario(CadFuncionarioPK cadFuncionarioPK) {
        this.cadFuncionarioPK = cadFuncionarioPK;
    }

    public CadFuncionario(String cpfFunc, int cdFunc) {
        this.cadFuncionarioPK = new CadFuncionarioPK(cpfFunc, cdFunc);
    }

    public CadFuncionarioPK getCadFuncionarioPK() {
        return cadFuncionarioPK;
    }

    public void setCadFuncionarioPK(CadFuncionarioPK cadFuncionarioPK) {
        this.cadFuncionarioPK = cadFuncionarioPK;
    }

    public String getNmFunc() {
        return nmFunc;
    }

    public void setNmFunc(String nmFunc) {
        this.nmFunc = nmFunc;
    }

    public Date getDtNascFunc() {
        return dtNascFunc;
    }

    public void setDtNascFunc(Date dtNascFunc) {
        this.dtNascFunc = dtNascFunc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cadFuncionarioPK != null ? cadFuncionarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CadFuncionario)) {
            return false;
        }
        CadFuncionario other = (CadFuncionario) object;
        if ((this.cadFuncionarioPK == null && other.cadFuncionarioPK != null) || (this.cadFuncionarioPK != null && !this.cadFuncionarioPK.equals(other.cadFuncionarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softbean.sindmepaOs.entidade.CadFuncionario[ cadFuncionarioPK=" + cadFuncionarioPK + " ]";
    }

    public String getEmailFunc() {
        return emailFunc;
    }

    public void setEmailFunc(String emailFunc) {
        this.emailFunc = emailFunc;
    }

    public Date getDtRegFunc() {
        return dtRegFunc;
    }

    public void setDtRegFunc(Date dtRegFunc) {
        this.dtRegFunc = dtRegFunc;
    }

    public Date getDtUltAtuFunc() {
        return dtUltAtuFunc;
    }

    public void setDtUltAtuFunc(Date dtUltAtuFunc) {
        this.dtUltAtuFunc = dtUltAtuFunc;
    }

    public Integer getFuncRegFunc() {
        return funcRegFunc;
    }

    public void setFuncRegFunc(Integer funcRegFunc) {
        this.funcRegFunc = funcRegFunc;
    }

    public Integer getFuncUltAtuFunc() {
        return funcUltAtuFunc;
    }

    public void setFuncUltAtuFunc(Integer funcUltAtuFunc) {
        this.funcUltAtuFunc = funcUltAtuFunc;
    }

    public CadSetor getSetorFunc() {
        return setorFunc;
    }

    public void setSetorFunc(CadSetor setorFunc) {
        this.setorFunc = setorFunc;
    }

    public String getSenhaFunc() {
        return senhaFunc;
    }

    public void setSenhaFunc(String senhaFunc) {
        this.senhaFunc = senhaFunc;
    }

}
