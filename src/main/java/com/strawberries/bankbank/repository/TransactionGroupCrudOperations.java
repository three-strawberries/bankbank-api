package com.strawberries.bankbank.repository;

import com.strawberries.bankbank.db.ConnectDB;
import com.strawberries.bankbank.entity.TransactionGroup;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionGroupCrudOperations implements CrudOperations<TransactionGroup> {
  private final ConnectDB db = ConnectDB.getInstance();
  private Connection connection = db.getConnection();

  @Override
  public List<TransactionGroup> findAll() {
    List<TransactionGroup> transactionGroupList = new ArrayList<>();
    String sql = "SELECT * FROM transaction_group ORDER BY date";
    try (Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(sql)) {
      while (resultSet.next()) {
        TransactionGroup transactionGroup = new TransactionGroup(
                resultSet.getInt("idTransactionGroup"),
                resultSet.getTimestamp("timestamp"),
                resultSet.getString("description"),
                resultSet.getString("method"),
                resultSet.getInt("idAccountSender")
        );
        transactionGroupList.add(transactionGroup);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return transactionGroupList;
  }

  @Override
  public TransactionGroup save(TransactionGroup toSave) {
    String insertQuery = "INSERT INTO transaction_group (date, description, method, idAccountSender) VALUES (?, ?, ?, ?)";
    try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
      insertStatement.setTimestamp(1, Timestamp.valueOf(toSave.getTimestamp().toLocalDateTime()));
      insertStatement.setString(2, toSave.getDescription());
      insertStatement.setString(3, toSave.getMethod());
      insertStatement.setInt(4, toSave.getIdAccountSender());

      int rowsAffected = insertStatement.executeUpdate();
      if (rowsAffected > 0) {
        ResultSet generatedKeys = insertStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
          toSave.setIdTransactionGroup(generatedKeys.getInt(1));
          return toSave;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public boolean update(TransactionGroup toSave) {
    String updateQuery = "UPDATE transaction_group SET date=?, description=?, method=?, idAccountSender=? WHERE idTransactionGroup=?";
    try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
      updateStatement.setTimestamp(1, Timestamp.valueOf(toSave.getTimestamp().toLocalDateTime()));
      updateStatement.setString(2, toSave.getDescription());
      updateStatement.setString(3, toSave.getMethod());
      updateStatement.setInt(4, toSave.getIdAccountSender());
      updateStatement.setInt(5, toSave.getIdTransactionGroup());

      int rowsAffected = updateStatement.executeUpdate();
      return rowsAffected > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }
}
