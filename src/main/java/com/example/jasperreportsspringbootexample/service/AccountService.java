package com.example.jasperreportsspringbootexample.service;

import com.example.jasperreportsspringbootexample.domain.Account;
import com.example.jasperreportsspringbootexample.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private ReportService reportService;

    public List<Account> list() {
        return repository.findAll();
    }

    public String generateReport(String format) {
        List<Account> accounts = list();
        return reportService.exportReport(accounts, format);
    }

}
