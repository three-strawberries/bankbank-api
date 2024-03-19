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
    private int id;
    private String lastName;
    private String firstname;
    private Date birthDate;
    private double monthlySalary;
}
