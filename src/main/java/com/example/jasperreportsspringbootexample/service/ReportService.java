package com.example.jasperreportsspringbootexample.service;

import com.example.jasperreportsspringbootexample.domain.Account;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

            return "Success exported at " + defaultExportReportsPath;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "Error FileNotFoundException " + e.getMessage();

        } catch (JRException e) {
            e.printStackTrace();
            return "Error JRException " + e.getMessage();
        }

    }

}
