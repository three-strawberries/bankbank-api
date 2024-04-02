package com.strawberries.bankbank.entity;


import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Balance {
  private int idBalance;
  private Timestamp timestamp;
  private double amount;
  private String typeBalance;
}
