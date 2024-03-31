package com.strawberries.bankbank.db;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.stereotype.Component;

@Component
public class ConnectDB {

  private final DataSource dataSource;

  public ConnectDB(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public Connection getConnection() {
    try {
      return dataSource.getConnection();
    } catch (SQLException e) {
      throw new RuntimeException("Error connecting to database.", e);
    }
  }
}
