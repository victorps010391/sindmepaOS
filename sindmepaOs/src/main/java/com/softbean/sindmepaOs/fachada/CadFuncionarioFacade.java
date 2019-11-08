/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.fachada;

import com.softbean.sindmepaOs.entidade.CadFuncionario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Raphael
 */
@Stateless
public class CadFuncionarioFacade extends AbstractFacade<CadFuncionario> {

    @PersistenceContext(unitName = "com.softbean_sindmepaOs_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CadFuncionarioFacade() {
        super(CadFuncionario.class);
    }

    public Integer retornaCdFunc() {
        StringBuilder sql = new StringBuilder();

        sql.append(" select retorna_cod_func() ");

        try {
            Query q = em.createNativeQuery(sql.toString());
            return (Integer) q.getSingleResult();
        } catch (Exception e) {
            System.err.println("Erro no metodo retornaCdFunc " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public Integer verificaCpfCadastrado(String cpf) {
        StringBuilder sql = new StringBuilder();

        sql.append(" select cast(count(*) as integer) from cad_funcionario where cpf_func = '").append(cpf).append("'");

        try {
            Query q = em.createNativeQuery(sql.toString());
            return (Integer) q.getSingleResult();
        } catch (Exception e) {
            System.err.println("Erro no metodo verificaCpfCadastrado " + e.getMessage());
            e.printStackTrace();
            return 1;
        }
    }

    public CadFuncionario validaAcesso(String cpf, String email) {
        CadFuncionario usuario = null;
        StringBuilder sql = new StringBuilder();

        try {
            sql.append(" SELECT c FROM CadFuncionario c WHERE c.cadFuncionarioPK.cpfFunc = :cpfFunc ");
            sql.append(" AND c.emailFunc = :emailFunc ");

            Query createQuery = em.createQuery(sql.toString());
            createQuery.setParameter("cpfFunc", cpf);
            createQuery.setParameter("emailFunc", email);

            usuario = (CadFuncionario) createQuery.getSingleResult();

        } catch (Exception e) {
            System.out.println("Erro no metodo validaAcesso " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("::::::::: USUARIIO ::::: "+usuario);
        return usuario;

    }

}
