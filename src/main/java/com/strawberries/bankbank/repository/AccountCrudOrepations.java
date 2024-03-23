package com.strawberries.bankbank.repository;


import com.strawberries.bankbank.db.ConnectDB;
import com.strawberries.bankbank.entity.Account;
import java.sql.*;



public class AccountCrudOrepations extends AutoCrudOperations<Account> {
    private final ConnectDB db = ConnectDB.getInstance();
    private Connection connection = db.getConnection();
    public AccountCrudOrepations(Connection connection) {
        super(connection);
    }
}
