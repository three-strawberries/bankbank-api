package com.strawberries.bankbank.entity;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
  private String authorizedCredit;
  private String bankName;
  private int idBalance;
}
