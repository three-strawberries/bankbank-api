package com.strawberries.bankbank.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
  private static String url = null;
  private static String username = null;
  private static String password = null;
  private static ConnectDB instance;
  private static Connection connection;

  private ConnectDB(String url, String username, String password) {
    this.url = url;
    this.username = username;
    this.password = password;
  }

  public static ConnectDB getInstance() {
    if (instance == null) {
      instance =
          new ConnectDB(PostgresqlConf.URL, PostgresqlConf.USERNAME, PostgresqlConf.PASSWORD);
    }
    return instance;
  }

  public static Connection getConnection() {
    try {
      if (connection == null || connection.isClosed()) {
        connection = DriverManager.getConnection(url, username, password);
      }
      return connection;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException("\n" + "Error connecting to database.", e);
    }
  }
}
