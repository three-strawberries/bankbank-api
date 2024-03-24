package com.strawberries.bankbank.repository;

import com.strawberries.bankbank.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Repository
public class AccountCrudOrepations extends AutoCrudOperations<Account> {
        @Autowired
        public AccountCrudOrepations(DataSource dataSource) {
            super(dataSource);
        }

    }



