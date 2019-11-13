/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.entidade;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author admin
 */
@Embeddable
public class CadTarefaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "nr_os_tarefa")
    private String nrOsTarefa;
    @Basic(optional = false)
    @Column(name = "seq_tarefa")
    private int seqTarefa;

    public CadTarefaPK() {
    }

    public CadTarefaPK(String nrOsTarefa, int seqTarefa) {
        this.nrOsTarefa = nrOsTarefa;
        this.seqTarefa = seqTarefa;
    }

    public String getNrOsTarefa() {
        return nrOsTarefa;
    }

    public void setNrOsTarefa(String nrOsTarefa) {
        this.nrOsTarefa = nrOsTarefa;
    }

    public int getSeqTarefa() {
        return seqTarefa;
    }

    public void setSeqTarefa(int seqTarefa) {
        this.seqTarefa = seqTarefa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nrOsTarefa != null ? nrOsTarefa.hashCode() : 0);
        hash += (int) seqTarefa;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CadTarefaPK)) {
            return false;
        }
        CadTarefaPK other = (CadTarefaPK) object;
        if ((this.nrOsTarefa == null && other.nrOsTarefa != null) || (this.nrOsTarefa != null && !this.nrOsTarefa.equals(other.nrOsTarefa))) {
            return false;
        }
        if (this.seqTarefa != other.seqTarefa) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softbean.sindmepaOs.entidade.CadTarefaPK[ nrOsTarefa=" + nrOsTarefa + ", seqTarefa=" + seqTarefa + " ]";
    }
    
}
