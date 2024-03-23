package com.strawberries.bankbank.repository;

import com.strawberries.bankbank.db.ConnectDB;
import com.strawberries.bankbank.entity.Transaction;

import java.sql.Connection;

public class TransactionCrudOperations extends AutoCrudOperations<Transaction>{
    private final ConnectDB db = ConnectDB.getInstance();
    private Connection connection = db.getConnection();

    public TransactionCrudOperations(Connection connection) {
        super(connection);
    }
}
