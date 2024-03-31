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
public class Transaction {
  private int idTransaction;
  private Timestamp timestamp;
  private String reference;
  private String description;
  private double debit;
  private double credit;
  private int idAccountSender;
  private int idAccountReceiver;
  private int idTransactionGroup;
  private String typeTransaction;
}
