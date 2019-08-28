/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author cdi_vsilva
 */
@Named(value = "utilBean")
@SessionScoped
@SuppressWarnings({"CallToPrintStackTrace", "UnusedAssignment"})
public class Util implements Serializable {

    /**
     * Creates a new instance of RelatorioUtilBean
     */
    public Util() {
    }

    public byte[] retornoByteArquivo(InputStream in) {
        byte[] bytes = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int read = 0;
            bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                bos.write(bytes, 0, read);
            }
            bytes = bos.toByteArray();
            in.close();
            in = null;
            bos.flush();
            bos.close();
            bos = null;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return bytes;
    }

}
