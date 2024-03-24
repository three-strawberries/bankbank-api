package com.strawberries.bankbank.repository;

import com.strawberries.bankbank.db.ConnectDB;

import com.strawberries.bankbank.entity.Balance;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
@Repository
public class BalanceCrudOperations extends AutoCrudOperations<Balance> {
    private final ConnectDB db = ConnectDB.getInstance();
    private final Connection connection = db.getConnection();
    public BalanceCrudOperations() {
        super(ConnectDB.getConnection());
    }
}
