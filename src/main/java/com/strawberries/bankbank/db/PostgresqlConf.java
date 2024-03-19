package com.strawberries.bankbank.db;

public class PostgresqlConf {
    public final static String URL = System.getenv("BANKBANK_DB_URL");
    public final static String USERNAME = System.getenv("BANKBANK_DB_USERNAME") ;
    public final static String PASSWORD = System.getenv("BANKBANK_DB_PASSWORD") ;
}
