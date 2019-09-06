/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.controle;

import com.softbean.sindmepaOs.entidade.CadExterno;
import com.softbean.sindmepaOs.entidade.CadOs;
import com.softbean.sindmepaOs.entidade.Endereco;
import com.softbean.sindmepaOs.fachada.CadExternoFacade;
import com.softbean.sindmepaOs.fachada.CadOsFacade;
import com.softbean.sindmepaOs.fachada.EnderecoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 *
 * @author Desenv
 */
@Named(value = "cadExternoControle")
@SessionScoped
public class CadExternoControle implements Serializable {

    /**
     * Creates a new instance of CadExternoControle
     */
    @Inject
    CadExternoFacade cadExternoFacade;

    @Inject
    EnderecoFacade enderecoFacade;
    
    @Inject
    CadOsFacade cadOsFacade;

    public CadExternoControle() {
    }

    public List<Map<String, Object>> listarSetor() {
        return cadExternoFacade.listarSetor();
    }

    public List<Map<String, Object>> listarCategoria() {
        return cadExternoFacade.listarCategoria();
    }

    public List<Map<String, Object>> tipoPag() {
        return cadExternoFacade.tipoPag();
    }

    public List<Map<String, Object>> pagInstituicao() {
        return cadExternoFacade.pagInstituicao();
    }

    public Boolean salvarUsuarioExt(CadExterno cadExtObj) {
        try {
            
            cadExternoFacade.create(cadExtObj);
            return true;
            
        } catch (Exception e) {
            System.out.println("ERRO no método salvarUsuarioExt-Controle");
            e.printStackTrace();
            return false;
        }
    }

    public Boolean salvarEnderecoExt(Endereco endereco) {
        try {
            enderecoFacade.create(endereco);
            return true;
        } catch (Exception e) {
            System.out.println("ERRO no método salvarEnderecoExt-ControleUsuario Ext");
            e.printStackTrace();
            return false;
        }
    }
    
    public Boolean salvarOsExt(CadOs cadOs) {
        try {
            cadOsFacade.create(cadOs);
            return true;
        } catch (Exception e) {
            System.out.println("ERRO no método salvarOsExt-ControleUsuario Ext");
            e.printStackTrace();
            return false;
        }
    }
    

}
