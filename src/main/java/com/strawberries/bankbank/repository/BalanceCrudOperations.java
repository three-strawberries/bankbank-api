package com.strawberries.bankbank.repository;

import com.strawberries.bankbank.db.ConnectDB;

import com.strawberries.bankbank.entity.Balance;

import java.sql.Connection;

public class BalanceCrudOperations extends AutoCrudOperations<Balance> {
    private final ConnectDB db = ConnectDB.getInstance();
    private Connection connection = db.getConnection();
    public BalanceCrudOperations(Connection connection) {
        super(connection);
    }
}
