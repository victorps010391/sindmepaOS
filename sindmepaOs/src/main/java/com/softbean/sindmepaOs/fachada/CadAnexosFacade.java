/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.fachada;

import com.softbean.sindmepaOs.entidade.CadAnexos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Victor
 */
@Stateless
public class CadAnexosFacade extends AbstractFacade<CadAnexos> {

    @PersistenceContext(unitName = "com.softbean_sindmepaOs_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CadAnexosFacade() {
        super(CadAnexos.class);
    }
    
        public List<CadAnexos> gridPrincipal(String nome, Integer cod) {
        List<CadAnexos> list = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT a FROM Anexos a WHERE 1=1 ");
        try {
            if (cod != null) {
                sql.append(" AND a.anexosPK.anexoSeq = :anexoSeq ");
            }
            if (!nome.equals("")) {
                sql.append(" AND LOWER(a.anexoNmArq) LIKE :anexoNmArq ");
            }
            System.out.println("sql:" + sql.toString());
            Query query = em.createQuery(sql.toString());

            if (cod != null) {
                query.setParameter("anexoSeq", cod);
            }
            if (!nome.equals("")) {
                query.setParameter("anexoNmArq", "%" + nome.toLowerCase() + "%");
            }
            list = query.getResultList();
        } catch (Exception e) {
            System.out.println("sql:[" + sql.toString() + "]");
            e.printStackTrace();
        }
        return list;
    }
    
}
