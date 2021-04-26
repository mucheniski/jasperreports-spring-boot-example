package com.example.jasperreportsspringbootexample.service;

import com.example.jasperreportsspringbootexample.domain.Account;
import com.example.jasperreportsspringbootexample.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class AccountServiceTest {

    @MockBean
    private AccountRepository repository;

    @Autowired
    private AccountService service;

    @BeforeEach
    void setUp() {

        var account1 = Account.builder()
                .id(1L)
                .name("Account 1")
                .bankname("Bankname 1")
                .agency("Agency 1")
                .number("number1")
                .balance(new BigDecimal(10))
                .build();

        var account2 = Account.builder()
                .id(2L)
                .name("Account 2")
                .bankname("Bankname 2")
                .agency("Agency 2")
                .number("number2")
                .balance(new BigDecimal(20))
                .build();

        List<Account> accountList = new ArrayList<>();
        accountList.add(account1);
        accountList.add(account2);

        when(repository.findAll()).thenReturn(accountList);
        when(repository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(account1));

    }

    @Test
    void list() {
        List<Account> accountList = repository.findAll();
        assertNotNull(accountList);
    }

    @Test
    void findById() {
        var account1 = repository.findById(1L);
        assertEquals(account1.get().getName(), "Account 1");
    }

    @Test
    void generateReport() {
        var report = service.generateReport("pdf");
        assertNotNull(report);
    }

    @Test
    void generateReportById() {
        var report = service.generateReportById(1L,"pdf");
        assertNotNull(report);
    }
}