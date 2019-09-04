/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.bean;

import com.softbean.sindmepaOs.controle.CadExternoControle;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author Desenv
 */
@Named(value = "cadExternoBean")
@SessionScoped
public class CadExternoBean implements Serializable {

    /**
     * Creates a new instance of CadExternoBean
     */
    @Inject
    CadExternoControle cadExternoControle;

    List<Map<String, Object>> comboSetor;
    List<Map<String, Object>> comboCategoria;
    List<Map<String, Object>> comboTipoPag;
    List<Map<String, Object>> comboPagInstituicao;

    //VARIÁVEIS DE ARMAZENAMENTO
    Integer categoria, setor, pagamento, instituicao;
    boolean liAceito;

    //variáveis view
    String sindicaliza, abriOsExt;

    //rendered
    String tipoPag, anuidadeResidente, debMenCartCred, debCCorrente, debCCheque;

    public CadExternoBean() {
    }

    public void validador() {
        setAbriOsExt("false");
        setSindicaliza("false");

        if (getCategoria() == 7) {
            setSindicaliza("true");
        } else {
            setAbriOsExt("true");
        }
    }

    public void validaDadoBancario() {
        setAnuidadeResidente("false");
        setDebMenCartCred("false");
        setDebCCorrente("false");
        setDebCCheque("false");

        if (getPagamento() == 12) {
            //"12 - ANUIDADE RESIDENTE"
            setAnuidadeResidente("true");
        } else if (getPagamento() == 13) {
            //"13 - DÉBITO MENSAL NO CARTÃO DE CRÉDITO"
            setDebMenCartCred("true");
        } else if(getPagamento() == 10 ){
            //"10 - DÉBITO EM CONTA CORRENTE"
            setDebCCorrente("true");
        } else if(getPagamento() == 11){
            //"11 - DÉBITO EM CONTRA-CHEQUE"
            setDebCCheque("true");
        }

    }

    public List<Map<String, Object>> comboCategoriaView() {
        try {
            setComboCategoria(cadExternoControle.listarCategoria());
        } catch (Exception e) {
            System.out.println("Erro no método comboSetorView()");
        }

        return getComboCategoria();
    }

    public List<Map<String, Object>> comboSetorView() {
        try {
            setComboSetor(cadExternoControle.listarSetor());
        } catch (Exception e) {
            System.out.println("Erro no método comboSetorView()");
        }

        return getComboSetor();
    }

    public List<Map<String, Object>> comboTipoPagView() {
        try {
            setComboTipoPag(cadExternoControle.tipoPag());
        } catch (Exception e) {
            System.out.println("Erro no método comboSetorView()");
        }

        return getComboTipoPag();
    }
    
    public List<Map<String, Object>> comboPagInstituicaoView() {
        try {
            setComboPagInstituicao(cadExternoControle.pagInstituicao());
        } catch (Exception e) {
            System.out.println("Erro no método comboPagInstituicaoView()");
        }

        return getComboPagInstituicao();
    }

    public CadExternoControle getCadExternoControle() {
        return cadExternoControle;
    }

    public void setCadExternoControle(CadExternoControle cadExternoControle) {
        this.cadExternoControle = cadExternoControle;
    }

    public List<Map<String, Object>> getComboSetor() {
        return comboSetor;
    }

    public void setComboSetor(List<Map<String, Object>> comboSetor) {
        this.comboSetor = comboSetor;
    }

    public List<Map<String, Object>> getComboCategoria() {
        return comboCategoria;
    }

    public void setComboCategoria(List<Map<String, Object>> comboCategoria) {
        this.comboCategoria = comboCategoria;
    }

    public String getSindicaliza() {
        return sindicaliza;
    }

    public void setSindicaliza(String sindicaliza) {
        this.sindicaliza = sindicaliza;
    }

    public String getAbriOsExt() {
        return abriOsExt;
    }

    public void setAbriOsExt(String abriOsExt) {
        this.abriOsExt = abriOsExt;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public Integer getSetor() {
        return setor;
    }

    public void setSetor(Integer setor) {
        this.setor = setor;
    }

    public String onFlowProcess(FlowEvent event) {
//        if(skip) {
//            skip = false;   //reset in case user goes back
//            return "confirm";
//        }
//        else {
//            return event.getNewStep();
//        }
        return event.getNewStep();
    }

    public boolean isLiAceito() {
        return liAceito;
    }

    public void setLiAceito(boolean liAceito) {
        this.liAceito = liAceito;
    }

    public List<Map<String, Object>> getComboTipoPag() {
        return comboTipoPag;
    }

    public void setComboTipoPag(List<Map<String, Object>> comboTipoPag) {
        this.comboTipoPag = comboTipoPag;
    }

    public Integer getPagamento() {
        return pagamento;
    }

    public void setPagamento(Integer pagamento) {
        this.pagamento = pagamento;
    }

    public String getTipoPag() {
        return tipoPag;
    }

    public void setTipoPag(String tipoPag) {
        this.tipoPag = tipoPag;
    }

    public String getAnuidadeResidente() {
        return anuidadeResidente;
    }

    public void setAnuidadeResidente(String anuidadeResidente) {
        this.anuidadeResidente = anuidadeResidente;
    }

    public String getDebMenCartCred() {
        return debMenCartCred;
    }

    public void setDebMenCartCred(String debMenCartCred) {
        this.debMenCartCred = debMenCartCred;
    }

    public String getDebCCorrente() {
        return debCCorrente;
    }

    public void setDebCCorrente(String debCCorrente) {
        this.debCCorrente = debCCorrente;
    }

    public String getDebCCheque() {
        return debCCheque;
    }

    public void setDebCCheque(String debCCheque) {
        this.debCCheque = debCCheque;
    }

    public List<Map<String, Object>> getComboPagInstituicao() {
        return comboPagInstituicao;
    }

    public void setComboPagInstituicao(List<Map<String, Object>> comboPagInstituicao) {
        this.comboPagInstituicao = comboPagInstituicao;
    }

    public Integer getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Integer instituicao) {
        this.instituicao = instituicao;
    }

    
}
