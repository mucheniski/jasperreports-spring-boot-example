package com.example.jasperreportsspringbootexample.controller;

import com.example.jasperreportsspringbootexample.domain.Account;
import com.example.jasperreportsspringbootexample.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService service;

    @GetMapping
    public List<Account> list() {
        return service.list();
    }

}
