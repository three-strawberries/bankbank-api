package com.strawberries.bankbank.repository;

import com.strawberries.bankbank.db.ConnectDB;
import com.strawberries.bankbank.entity.Transaction;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
@Repository
public class TransactionCrudOperations extends AutoCrudOperations<Transaction>{
    private final ConnectDB db = ConnectDB.getInstance();
    private final Connection connection = db.getConnection();

    public TransactionCrudOperations(Connection connection) {
        super(connection);
    }
}
