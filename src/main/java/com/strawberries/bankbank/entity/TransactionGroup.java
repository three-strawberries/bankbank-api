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
public class TransactionGroup {
    private int idTransactionGroup;
    private LocalDateTime date;
    private String description;
    private String method;
    private int idAccountSender;
}
