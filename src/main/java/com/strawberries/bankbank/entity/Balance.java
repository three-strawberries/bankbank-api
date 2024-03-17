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
public class Balance {
    private int id;
    private LocalDateTime dateTime;
    private double amount;
    private int accountId;
}
