/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.manager;

import com.softbean.sindmepaOs.bean.LoginBean;
import com.softbean.sindmepaOs.controle.CadOsControle;
import com.softbean.sindmepaOs.controle.CadTarefaControle;
import com.softbean.sindmepaOs.util.Util;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

/**
 *
 * @author admin
 */
@Named(value = "indexManager")
@SessionScoped
public class IndexManager implements Serializable {

    /**
     * Creates a new instance of IndexManager
     */
//    private DashboardModel model;
    /**
     *
     */
    public IndexManager() {
//        this.model = new DefaultDashboardModel();
//        // Initialize the dashboard column #1
//        DashboardColumn column1 = new DefaultDashboardColumn();
//        // Initialize the dashboard column #2
//        DashboardColumn column2 = new DefaultDashboardColumn();
//        // Initialize the dashboard column #3
//        DashboardColumn column3 = new DefaultDashboardColumn();
//
//        // Add widget into column1
//        column1.addWidget("Sports");
//        // Add widget into column2
//        column2.addWidget("Finance");
//        // Add widget into column3
//        column3.addWidget("News");
//
//        // Add columns into your model
//        this.model.addColumn(column1);
//        this.model.addColumn(column2);
//        this.model.addColumn(column3);
    }

    @Inject
    LoginBean loginBean;
    @Inject
    CadOsControle osControle;
    @Inject
    CadTarefaControle tarefaControle;
    @Inject
    Util util;

    List<Map<String, Object>> usu;
    List<Map<String, Object>> usuTarefa;
    List<Map<String, Object>> usuDiretor;

    public String voltar() {
        RequestContext context = RequestContext.getCurrentInstance();
        carregaGrid();
        context.update(":frmIndex :frmDashboard");
        return "index";
    }

    public Boolean gridDiretoria() {
        return loginBean.getUsuario().getSetorFunc().getCdSetor() == 7;
    }

    public void carregaGrid() {
        try {
            setUsu(osControle.usuDashboard(loginBean.getUsuario().getSetorFunc().getCdSetor()));
            setUsuDiretor(osControle.usuDiretorDashboard(loginBean.getUsuario().getSetorFunc().getCdSetor()));
        } catch (Exception e) {
            System.out.println("Erro no metodo carregaGrid " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void carregaGridTarefa() {
        try {
            setUsuTarefa(tarefaControle.usuDashboardTarefa(loginBean.getUsuario().getSetorFunc().getCdSetor()));
        } catch (Exception e) {
            System.out.println("Erro no metodo carregaGridTarefa " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void carregaGrids() {        
        RequestContext context = RequestContext.getCurrentInstance();        
        carregaGrid();
        carregaGridTarefa();
    }
    
    public boolean validaAvisoPrimeiroAcesso(){
        return loginBean.getUsuario().getSenhaFunc().equals(util.converteParaMd5("102030"));
    }

//    public DashboardModel getModel() {
//        return model;
//    }
//
//    public void setModel(DashboardModel model) {
//        this.model = model;
//    }
    public List<Map<String, Object>> getUsuTarefa() {
        return usuTarefa;
    }

    public void setUsuTarefa(List<Map<String, Object>> usuTarefa) {
        this.usuTarefa = usuTarefa;
    }

    public List<Map<String, Object>> getUsu() {
        return usu;
    }

    public void setUsu(List<Map<String, Object>> usu) {
        this.usu = usu;
    }

    public List<Map<String, Object>> getUsuDiretor() {
        return usuDiretor;
    }

    public void setUsuDiretor(List<Map<String, Object>> usuDiretor) {
        this.usuDiretor = usuDiretor;
    }
}
