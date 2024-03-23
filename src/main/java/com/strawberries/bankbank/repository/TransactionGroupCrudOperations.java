package com.strawberries.bankbank.repository;

import com.strawberries.bankbank.db.ConnectDB;
import com.strawberries.bankbank.entity.TransactionGroup;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
@Repository
public class TransactionGroupCrudOperations extends AutoCrudOperations<TransactionGroup>{
    private final ConnectDB db = ConnectDB.getInstance();
    private final Connection connection = db.getConnection();

    public TransactionGroupCrudOperations(Connection connection) {
        super(connection);
    }
}
