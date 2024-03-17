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
    private int id;
    private LocalDateTime dateTime;
    private String reference;
    private  String description;
    private double debitMGA;
    private double creditMGA;
    private int accountId;
    private String type;

}
