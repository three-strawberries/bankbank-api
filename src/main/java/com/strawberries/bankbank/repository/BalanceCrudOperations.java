package com.strawberries.bankbank.repository;

import com.strawberries.bankbank.entity.Balance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Repository
public class BalanceCrudOperations extends AutoCrudOperations<Balance> {

    public BalanceCrudOperations(DataSource dataSource) {
        super(dataSource);
    }
}
