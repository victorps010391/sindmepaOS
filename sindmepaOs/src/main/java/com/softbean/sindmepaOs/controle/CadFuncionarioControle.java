/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.controle;

import com.softbean.sindmepaOs.entidade.CadFuncionario;
import com.softbean.sindmepaOs.entidade.CadFuncionarioPK;
import com.softbean.sindmepaOs.fachada.CadFuncionarioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 *
 * @author Raphael
 */
@Named(value = "cadFuncionarioControle")
@SessionScoped
public class CadFuncionarioControle implements Serializable {

    /**
     * Creates a new instance of CadFuncionarioControle
     */
    public CadFuncionarioControle() {
    }
    @Inject
    CadFuncionarioFacade funcionarioFacade;

    public Boolean salvarFuncioControle(CadFuncionario obj, CadFuncionarioPK objPk) {
        try {

            obj.setCadFuncionarioPK(objPk);
            funcionarioFacade.create(obj);

            return true;
        } catch (Exception e) {
            System.out.println("Erro no método salvarFuncControle " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public Integer retornaCdFunc() {
        return funcionarioFacade.retornaCdFunc();
    }

    public Integer verificaCpfCadastrado(String cpf) {
        return funcionarioFacade.verificaCpfCadastrado(cpf);
    }

    public CadFuncionario validaAcesso(String cpf, String senha) {
        return funcionarioFacade.validaAcesso(cpf, senha);
    }

    public List<Map<String, Object>> listarSetorPesq() {
        return funcionarioFacade.listarSetorPesq();
    }

    public CadFuncionario retornaUsuario(String cpf, String senha) {
        return funcionarioFacade.retornaUsuario(cpf, senha);
    }

    public Boolean alterarSenha(CadFuncionario obj) {
        try {
            funcionarioFacade.edit(obj);
            return true;
        } catch (Exception e) {
            System.out.println("Erro no método alterarSenha " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public CadFuncionario retornaUsuarioSolicitacaoSenha(String cpf) {
        return funcionarioFacade.retornaUsuarioSolicitacaoSenha(cpf);
    }
}
