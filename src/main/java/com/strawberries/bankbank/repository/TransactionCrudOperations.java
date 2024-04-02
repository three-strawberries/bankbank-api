package com.strawberries.bankbank.repository;

import com.strawberries.bankbank.db.ConnectDB;
import com.strawberries.bankbank.entity.Transaction;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionCrudOperations implements CrudOperations<Transaction> {
  private final ConnectDB db = ConnectDB.getInstance();
  private Connection connection = db.getConnection();

  @Override
  public List<Transaction> findAll() {
    List<Transaction> transactionList = new ArrayList<>();
    String sql = "SELECT * FROM transaction ORDER BY Date";
    try (Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(sql)) {
      while (resultSet.next()) {
        Transaction transaction = new Transaction(
                resultSet.getInt("idTransaction"),
                resultSet.getTimestamp("Date").toLocalDateTime(),
                resultSet.getString("reference"),
                resultSet.getString("description"),
                resultSet.getDouble("debit"),
                resultSet.getDouble("credit"),
                resultSet.getInt("idAccountSender"),
                resultSet.getInt("idAccountReceiver"),
                resultSet.getInt("idTransactionGroup"),
                resultSet.getString("typeTransaction")
        );
        transactionList.add(transaction);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return transactionList;
  }

  @Override
  public Transaction save(Transaction toSave) {
    String insertQuery = "INSERT INTO transaction (Date, reference, description, debit, credit, idAccountSender, idAccountReceiver, idTransactionGroup, typeTransaction) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
      insertStatement.setTimestamp(1, Timestamp.valueOf(toSave.getDate()));
      insertStatement.setString(2, toSave.getReference());
      insertStatement.setString(3, toSave.getDescription());
      insertStatement.setDouble(4, toSave.getDebit());
      insertStatement.setDouble(5, toSave.getCredit());
      insertStatement.setInt(6, toSave.getIdAccountSender());
      insertStatement.setInt(7, toSave.getIdAccountReceiver());
      insertStatement.setInt(8, toSave.getIdTransactionGroup());
      insertStatement.setString(9, toSave.getTypeTransaction());

      int rowsAffected = insertStatement.executeUpdate();
      if (rowsAffected > 0) {
        ResultSet generatedKeys = insertStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
          toSave.setIdTransaction(generatedKeys.getInt(1));
          return toSave;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public boolean update(Transaction toSave) {
    String updateQuery = "UPDATE transaction SET Date=?, reference=?, description=?, debit=?, credit=?, idAccountSender=?, idAccountReceiver=?, idTransactionGroup=?, typeTransaction=? WHERE idTransaction=?";
    try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
      updateStatement.setTimestamp(1, Timestamp.valueOf(toSave.getDate()));
      updateStatement.setString(2, toSave.getReference());
      updateStatement.setString(3, toSave.getDescription());
      updateStatement.setDouble(4, toSave.getDebit());
      updateStatement.setDouble(5, toSave.getCredit());
      updateStatement.setInt(6, toSave.getIdAccountSender());
      updateStatement.setInt(7, toSave.getIdAccountReceiver());
      updateStatement.setInt(8, toSave.getIdTransactionGroup());
      updateStatement.setString(9, toSave.getTypeTransaction());
      updateStatement.setInt(10, toSave.getIdTransaction());

      int rowsAffected = updateStatement.executeUpdate();
      return rowsAffected > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }
}