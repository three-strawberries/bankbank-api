package com.strawberries.bankbank.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Account {
    private int idAccount;
    private String lastName;
    private String firstName;
    private Date birthDate;
    private double monthlySalary;
    private int idBalance;
}
