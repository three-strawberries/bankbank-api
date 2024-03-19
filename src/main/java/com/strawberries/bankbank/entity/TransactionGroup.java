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
    private int id;
    private int accountIdSender;
    private LocalDateTime dateTime;
    private String description;
}
