package com.strawberries.bankbank.repository;

import com.strawberries.bankbank.db.ConnectDB;
import com.strawberries.bankbank.entity.TransactionGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Repository
public class TransactionGroupCrudOperations extends AutoCrudOperations<TransactionGroup> {

    public TransactionGroupCrudOperations(DataSource dataSource) {
        super(dataSource);
    }
}
