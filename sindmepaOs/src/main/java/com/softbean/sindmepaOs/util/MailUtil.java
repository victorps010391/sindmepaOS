/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.util;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;

/**
 *
 * @author Victor
 */
@Named(value = "mailUtil")
@SessionScoped
public class MailUtil implements Serializable {

    /**
     * Creates a new instance of MailUtil
     */
    public MailUtil() {
    }

    public void enviar(String assunto, String destinatario, String corpo) {
        HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.gmail.com");
        email.setSslSmtpPort("587");
        email.setStartTLSRequired(true);
        email.setStartTLSEnabled(true);
        email.setSSLOnConnect(true);

        email.setAuthenticator(new DefaultAuthenticator("victorps91@gmail.com", "victor@1106"));
        try {
            email.setFrom("victorps91@gmail.com");

            email.setSubject(assunto);
            email.addTo(destinatario);
            email.setHtmlMsg(corpo);

            email.send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
