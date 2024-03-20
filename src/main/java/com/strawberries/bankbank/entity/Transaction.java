package com.strawberries.bankbank.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Transaction {
    private int idTransaction;
    private LocalDateTime positionningDate;
    private String reference;
    private  String description;
    private double debit;
    private double credit;
    private int accountId;
    private String type;

}
