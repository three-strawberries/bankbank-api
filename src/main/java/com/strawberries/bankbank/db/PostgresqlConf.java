package com.strawberries.bankbank.db;

public class PostgresqlConf {
  public static final String URL = System.getenv("BANKBANK_DB_URL");
  public static final String USERNAME = System.getenv("BANKBANK_DB_USERNAME");
  public static final String PASSWORD = System.getenv("BANKBANK_DB_PASSWORD");
}
