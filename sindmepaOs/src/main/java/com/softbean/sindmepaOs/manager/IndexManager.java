/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.manager;

import com.softbean.sindmepaOs.bean.LoginBean;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

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

    public String voltar() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.update(":frmIndex :frmDashboard");
        return "index";
    }

    public Boolean gridDiretoria() {
        return loginBean.getUsuario().getSetorFunc().getCdSetor() == 7;
    }

//    public DashboardModel getModel() {
//        return model;
//    }
//
//    public void setModel(DashboardModel model) {
//        this.model = model;
//    }
}
