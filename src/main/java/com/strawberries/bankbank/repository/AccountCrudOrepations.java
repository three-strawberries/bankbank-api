package com.strawberries.bankbank.repository;


import com.strawberries.bankbank.db.ConnectDB;
import com.strawberries.bankbank.entity.Account;
import org.springframework.stereotype.Repository;

import java.sql.*;


@Repository
public class AccountCrudOrepations extends AutoCrudOperations<Account> {
    private final ConnectDB db = ConnectDB.getInstance();
    private final Connection connection = db.getConnection();
    public AccountCrudOrepations(Connection connection) {
        super(connection);
    }
}
