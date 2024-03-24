package com.strawberries.bankbank.repository;


import com.strawberries.bankbank.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Repository
public class TransactionCrudOperations extends AutoCrudOperations<Transaction> {

    public TransactionCrudOperations(DataSource dataSource) {
        super(dataSource);
    }
}

