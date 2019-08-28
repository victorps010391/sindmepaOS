/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.controle;

import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author cdi_vsilva
 */
@Named(value = "relatorioControle")
@SessionScoped
@SuppressWarnings({"CallToPrintStackTrace", "UnusedAssignment"})
public class RelatorioControle implements Serializable {

    /**
     * Creates a new instance of RelatorioControle
     */
    public RelatorioControle() {
    }

    Connection con = null;
    private static final String url = "jdbc:postgresql://localhost:5433/sindmepa_os_db";
    private static final String driver = "org.postgresql.Driver";
    private static final String login = "postgres";
    private static final String pwd = "123456";

    public Connection conectar() throws ClassNotFoundException {
        Connection conexao = null;
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, login, pwd);

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("ERRO no método conectar");
        }
        return conexao;
    }

    public byte[] geraRelatorioInputStr(String filtros, String tituloRelatorio, Map<String, Object> param, InputStream stream) throws ClassNotFoundException {
        
        JasperPrint viewer = null;
        byte[] output = null;
        try {           
            Map<String, Object> parametros = new HashMap<>();

//            InputStream streamBanparaImg = this.getClass().getResourceAsStream("");
//            InputStream streamAdmlogImg = this.getClass().getResourceAsStream("");
//
//            ImageIcon gtoBanpara = new ImageIcon(IOUtils.toByteArray(streamBanparaImg));
//            ImageIcon gtoAdmLog = new ImageIcon(IOUtils.toByteArray(streamAdmlogImg));
//            parametros.put("rel_url_img_banpara", gtoBanpara.getImage());
//            parametros.put("rel_url_img_admlog", gtoAdmLog.getImage());
//            parametros.put("rel_titulo", tituloRelatorio);
//            parametros.put("sqlReport", filtros);
//            parametros.put("rel_data", new Date());
            if (param != null) {
                parametros.putAll(param);
            }
            System.out.println(" " + parametros);
            viewer = JasperFillManager.fillReport(stream, parametros, getCon());
            output = JasperExportManager.exportReportToPdf(viewer);

            if (output.length <= 945) {
                InputStream streamErro = this.getClass().getResourceAsStream("rel_Erro.jasper");
                viewer = JasperFillManager.fillReport(streamErro, parametros, getCon());
                output = JasperExportManager.exportReportToPdf(viewer);
            }
            System.out.println("Relatório gerado.");
        } catch (JRException e) {
            System.out.println("erro  geraRelatorio() ");
            e.printStackTrace();
        }
        return output;
    }

    public Connection getCon() throws ClassNotFoundException {
        if (con == null) {
            con = conectar();
        }
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

}
