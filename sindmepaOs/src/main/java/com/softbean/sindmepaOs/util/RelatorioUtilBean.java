/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softbean.sindmepaOs.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author cdi_vsilva
 */
@Named(value = "relatorioUtilBean")
@SessionScoped
@SuppressWarnings({"CallToPrintStackTrace", "UnusedAssignment"})
public class RelatorioUtilBean implements Serializable {

    /**
     * Creates a new instance of RelatorioUtilBean
     */
    public RelatorioUtilBean() {
    }

    StreamedContent arquivoRetorno = null;
    byte[] relatorioByte = null;
    String relativePath;
    String nomeArquivo;
    String extensaoArquivoExportado;
    StreamedContent pdf;
    String caminhoArquivoRelatorio;
    private File arquivoGerado = null;

    private StreamedContent media;

    private byte[] conteudoPdf;

    // Caminho completo do arquivo informado pelo usuário.
    // Ex: C:\Meus Documentos\boletim.pdf
    private String arquivo;

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public void importarArquivo() {
        try {
            // Cria um objeto File a partir do caminho especificado

            File file = new File(getCaminhoArquivoRelatorio());

            // Inicializa o array bytes com o tamanho do arquivo especificado.
            // Note a conversao para int, restringindo a capacidade maxima do
            // arquivo em 2 GB
            conteudoPdf = new byte[(int) file.length()];

            // Cria um InputStream a partir do objeto File
            InputStream is = new FileInputStream(file);

            // Aqui o InputStream faz a leitura do arquivo, transformando em um
            // array de bytes
            is.read(conteudoPdf);

            // Fecha o InputStream, liberando seus recursos
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String retornaDirecao() {
        String retono = "_self";

        if (getRelatorioByte() != null) {
            retono = "_blank";

        }

        return retono;
    }

    public void visualizarPdf() {
        // geraRelatorio();
        //importarArquivo();
        try {

            FacesContext fc = FacesContext.getCurrentInstance();

            // Obtem o HttpServletResponse, objeto responsável pela resposta do
            // servidor ao browser
            HttpServletResponse response = (HttpServletResponse) fc
                    .getExternalContext().getResponse();

            // Limpa o buffer do response
            response.reset();

            // Seta o tipo de conteudo no cabecalho da resposta. No caso, indica que o
            // conteudo sera um documento pdf.
            response.setContentType("application/pdf");

            // Seta o tamanho do conteudo no cabecalho da resposta. No caso, o tamanho
            // em bytes do pdf
            if (getRelatorioByte() != null) {
                response.setContentLength(getRelatorioByte().length);

                // Seta o nome do arquivo e a disposição: "inline" abre no próprio
                // navegador.
                // Mude para "attachment" para indicar que deve ser feito um download
//                response.setHeader("Content-disposition", "inline; filename=arquivo.pdf");
                response.setHeader("target:", "_blank");

                // Envia o conteudo do arquivo PDF para o response
                response.getOutputStream().write(getRelatorioByte());

                // Descarrega o conteudo do stream, forçando a escrita de qualquer byte
                // ainda em buffer
                response.getOutputStream().flush();

                // Fecha o stream, liberando seus recursos
                response.getOutputStream().close();

                // Sinaliza ao JSF que a resposta HTTP para este pedido já foi gerada
                fc.responseComplete();
                setRelatorioByte(null);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ADMlog:", "Não há items para ser Gerados"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void visualizar() {
        // geraRelatorio();
        //importarArquivo();
        try {

            FacesContext fc = FacesContext.getCurrentInstance();

            // Obtem o HttpServletResponse, objeto responsável pela resposta do
            // servidor ao browser
            HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();

            // Limpa o buffer do response
            response.reset();

            // Seta o tipo de conteudo no cabecalho da resposta. No caso, indica que o
            // conteudo sera um documento pdf.
            System.out.println("application/" + getExtensaoArquivoExportado());
            response.setContentType("application/" + getExtensaoArquivoExportado());

            // Seta o tamanho do conteudo no cabecalho da resposta. No caso, o tamanho
            // em bytes do pdf
            if (getRelatorioByte() != null) {
                response.setContentLength(getRelatorioByte().length);

                // Seta o nome do arquivo e a disposição: "inline" abre no próprio
                // navegador.
                // Mude para "attachment" para indicar que deve ser feito um download
//                response.setHeader("Content-disposition", "inline; filename=arquivo.pdf");
                if (getExtensaoArquivoExportado().contains("pdf")) {
                    response.setHeader("target:", "_blank");
                    response.setHeader("Content-Disposition", "attachment; filename=" + getNomeArquivo() + ".pdf");
                } else {
                    System.out.println("nome:" + getNomeArquivo());
                    response.setHeader("Content-Disposition", "attachment; filename=" + getNomeArquivo() + ".xls");
                }

                // Envia o conteudo do arquivo PDF para o response
                response.getOutputStream().write(getRelatorioByte());

                // Descarrega o conteudo do stream, forçando a escrita de qualquer byte
                // ainda em buffer
                response.getOutputStream().flush();

                // Fecha o stream, liberando seus recursos
                response.getOutputStream().close();

                // Sinaliza ao JSF que a resposta HTTP para este pedido já foi gerada
                fc.responseComplete();
                setRelatorioByte(null);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ADMlog:", "Não há items para ser Gerados"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public StreamedContent todasImage() {
        StreamedContent im;

        im = new DefaultStreamedContent();
        System.out.println("############ " + getCaminhoArquivoRelatorio());
        ByteArrayInputStream inpByt = new ByteArrayInputStream(recuperaFileEmByte(new java.io.File(getCaminhoArquivoRelatorio())));
        InputStream stream = (InputStream) inpByt;
        im = new DefaultStreamedContent(stream, "application/pdf", getNomeArquivo());

        return im;
    }

    private byte[] recuperaFileEmByte(File inFile) {
        InputStream is = null;
        byte[] buffer = null;
        try {
            is = new FileInputStream(inFile);
            buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    public void apagarArquivo() {
        getArquivoGerado().delete();
    }

    public StreamedContent geraRelatorio() {
        arquivoRetorno = null;
        try {
            System.out.println("################    " + getRelatorioByte().length);

            InputStream conteudoRelatorio = new ByteArrayInputStream(getRelatorioByte());
            setArquivoRetorno(new DefaultStreamedContent(conteudoRelatorio, "application/" + extensaoArquivoExportado, getNomeArquivo() + "." + (extensaoArquivoExportado.equals("pdf") ? extensaoArquivoExportado : "xls")));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return getArquivoRetorno();
    }

    public StreamedContent getArquivoRetorno() {
        return arquivoRetorno;
    }

    public void setArquivoRetorno(StreamedContent arquivoRetorno) {
        this.arquivoRetorno = arquivoRetorno;
    }

    public byte[] getRelatorioByte() {
        return relatorioByte;
    }

    public void setRelatorioByte(byte[] relatorioByte) {
        this.relatorioByte = relatorioByte;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getExtensaoArquivoExportado() {
        return extensaoArquivoExportado;
    }

    public void setExtensaoArquivoExportado(String extensaoArquivoExportado) {
        this.extensaoArquivoExportado = extensaoArquivoExportado;
    }

    public File getArquivoGerado() {
        return arquivoGerado;
    }

    public void setArquivoGerado(File arquivoGerado) {
        this.arquivoGerado = arquivoGerado;
    }

    public StreamedContent getPdf() {
        //geraRelatorio();
        pdf = todasImage();

        return pdf;
    }

    public void setPdf(StreamedContent pdf) {
        this.pdf = pdf;
    }

    public String getCaminhoArquivoRelatorio() {
        return caminhoArquivoRelatorio;
    }

    public void setCaminhoArquivoRelatorio(String caminhoArquivoRelatorio) {
        this.caminhoArquivoRelatorio = caminhoArquivoRelatorio;
    }

}
