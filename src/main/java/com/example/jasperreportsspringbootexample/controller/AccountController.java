package com.example.jasperreportsspringbootexample.controller;

import com.example.jasperreportsspringbootexample.domain.Account;
import com.example.jasperreportsspringbootexample.service.AccountService;
import com.example.jasperreportsspringbootexample.service.Base64Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    public static final String FORMAT_NOT_ALLOWED = "Format not allowed, choose pdf or html please!";

    @Autowired
    private AccountService service;

    @Autowired
    private Base64Service base64Service;

    @GetMapping
    public List<Account> list() {
        return service.list();
    }

    @GetMapping("/report")
    public String generateReport(@RequestParam String format) {
        return service.generateReport(format);
    }

    @GetMapping("/report/{id}")
    public String generateReportindividualPDF(@PathVariable Long id, @RequestParam String format) {
        return service.generateReportById(id, format);
    }

    @GetMapping("/base64-decode")
    public void decodeBase64ToFile(@RequestParam String fileName, @RequestParam String format) {
        base64Service.decodeBase64ToImageAndSaveFile(fileName, format);
    }

}
