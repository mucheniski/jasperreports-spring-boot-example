package com.example.jasperreportsspringbootexample.service;

import com.example.jasperreportsspringbootexample.domain.Account;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class ReportService {

    @Value("${default.exportReportsPath}")
    private String defaultExportReportsPath;

    public String exportReport(List<Account> accounts, String reportFormat) {

        try {
            File file = ResourceUtils.getFile("classpath:reports/account/accounts.jrxml");

            JasperDesign jasperDesign;
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(accounts);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("createdBy","Diego Mucheniski");
            parameters.put("image","classpath:images/dinossaur.png");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            /*
            * JasperExportManager has a exportReportToPdfStream()
            * */

            if (reportFormat.equalsIgnoreCase("html")) {
                JasperExportManager.exportReportToHtmlFile(jasperPrint,defaultExportReportsPath + "accounts.html");
            }

            if (reportFormat.equalsIgnoreCase("pdf")) {
                JasperExportManager.exportReportToPdfFile(jasperPrint,defaultExportReportsPath + "accounts.pdf");
            }

            byte[] jasperBytes = JasperExportManager.exportReportToPdf(jasperPrint);
            return Base64.getEncoder().encodeToString(jasperBytes);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "Error FileNotFoundException " + e.getMessage();

        } catch (JRException e) {
            e.printStackTrace();
            return "Error JRException " + e.getMessage();
        }

    }

    public String exportReportIndividual(Account account, String reportFormat) {

        try {
            File file = ResourceUtils.getFile("classpath:reports/account/individualAccount.jrxml");

            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Arrays.asList(account));

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("createdBy","Diego Mucheniski");
            parameters.put("image","classpath:images/dinossaur.png");
            parameters.put("REPORT_LOCALE", new Locale("pt", "BR"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            /*
             * JasperExportManager has a exportReportToPdfStream()
             * */

            if (reportFormat.equalsIgnoreCase("html")) {
                JasperExportManager.exportReportToHtmlFile(jasperPrint,defaultExportReportsPath + "accountById.html");
            }

            if (reportFormat.equalsIgnoreCase("pdf")) {
                JasperExportManager.exportReportToPdfFile(jasperPrint,defaultExportReportsPath + "accountById.pdf");
            }

            byte[] bitmapBytes = convertToBitmap(jasperPrint);

            // Retornar o PDF com encode em base64
            // byte[] jasperBytes = JasperExportManager.exportReportToPdf(jasperPrint);
            // return Base64.getEncoder().encodeToString(jasperBytes);

            // Retornar o Bitmap com encode em base64
            return Base64.getEncoder().encodeToString(bitmapBytes);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "Error FileNotFoundException " + e.getMessage();

        } catch (JRException e) {
            e.printStackTrace();
            return "Error JRException " + e.getMessage();
        }

    }

    private byte[] convertToBitmap(JasperPrint jasperPrint) {
        try{
            Path tempFile = Files.createTempFile("sampleBitmap","bmp");
            OutputStream ouputStream = new FileOutputStream(tempFile.toFile());
            JasperPrintManager printManager = JasperPrintManager.getInstance(DefaultJasperReportsContext.getInstance());

            BufferedImage bufferedImage = (BufferedImage) printManager.printPageToImage(jasperPrint, 0,1.6f);

            // Se quiser salvar o arquivo precisa passar um file no lugar de tempFile
            // File file = new File("caminho\\completo\\nomearquivo.bmp");
            // ImageIO.write(bufferedImage, "bmp", ouputStream);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "bmp", baos);
            return baos.toByteArray();

        } catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
