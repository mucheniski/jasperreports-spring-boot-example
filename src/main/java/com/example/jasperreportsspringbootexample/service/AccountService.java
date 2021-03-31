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

    public List<Account> list() {
        return repository.findAll();
    }

}
