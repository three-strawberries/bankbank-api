package com.strawberries.bankbank.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

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
  private LocalDateTime date;
  private double amount;
  private String typeBalance;
}
