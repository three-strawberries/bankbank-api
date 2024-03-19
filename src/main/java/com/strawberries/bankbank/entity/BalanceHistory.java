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
public class BalanceHistory {
    private int idBalance;
    private LocalDateTime lastModification;
    private double amount;
    private int idAccount;
}
