package com.strawberries.bankbank.repository;

import com.strawberries.bankbank.db.ConnectDB;
import com.strawberries.bankbank.entity.TransactionGroup;

import java.sql.Connection;

public class TransactionGroupCrudOperations extends AutoCrudOperations<TransactionGroup>{
    private final ConnectDB db = ConnectDB.getInstance();
    private Connection connection = db.getConnection();

    public TransactionGroupCrudOperations(Connection connection) {
        super(connection);
    }
}
