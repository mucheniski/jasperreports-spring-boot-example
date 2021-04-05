package com.example.jasperreportsspringbootexample.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@Service
public class Base64Service {

    @Value("${default.exportReportsPath}")
    private String defaultExportReportsPath;

    public void decodeBase64ToImageAndSaveFile(String fileName) {
        try {
            FileInputStream inputStream = null;
            inputStream = new FileInputStream(defaultExportReportsPath + fileName);
            byte[] bytesTxt = inputStream.readAllBytes();
            byte[] bytes64 = Base64.getDecoder().decode(bytesTxt);
            // Export file
            FileOutputStream fileOutputStream = new FileOutputStream(defaultExportReportsPath + UUID.randomUUID().toString() + ".pdf");
            fileOutputStream.write(bytes64);
            fileOutputStream.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            // TODO: tratar as exceptions
            e.printStackTrace();
        } catch (IOException e) {
            // TODO: tratar as exceptions
            e.printStackTrace();
        }
    }

}
