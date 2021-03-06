package com.example.jasperreportsspringbootexample.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Builder
public class Account {

    @EqualsAndHashCode.Include
    @Id
    private Long id;

    private String name;
    private String bankname;
    private String agency;
    private String number;
    private BigDecimal balance;

}