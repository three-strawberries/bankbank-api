package com.strawberries.bankbank.repository;


import com.strawberries.bankbank.db.ConnectDB;
import com.strawberries.bankbank.entity.Balance;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BalanceCrudOperations implements CrudOperations<Balance> {
  private final ConnectDB db = ConnectDB.getInstance();
  private Connection connection = db.getConnection();

  @Override
  public List<Balance> findAll() {
    List<Balance> balanceList = new ArrayList<>();
    String sql = "SELECT * FROM balance ORDER BY date";
    try (Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(sql)) {
      while (resultSet.next()) {
        Balance balance = new Balance(
                resultSet.getInt("idBalance"),
                resultSet.getTimestamp("date").toLocalDateTime(),
                resultSet.getDouble("amount"),
                resultSet.getString("typeBalance")
        );
        balanceList.add(balance);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return balanceList;
  }

  @Override
  public Balance save(Balance toSave) {
    String insertQuery = "INSERT INTO balance (date, amount, typeBalance) VALUES (?, ?, ?)";
    try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
      insertStatement.setTimestamp(1, Timestamp.valueOf(toSave.getDate()));
      insertStatement.setDouble(2, toSave.getAmount());
      insertStatement.setString(3, toSave.getTypeBalance());

      int rowsAffected = insertStatement.executeUpdate();
      if (rowsAffected > 0) {
        ResultSet generatedKeys = insertStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
          toSave.setIdBalance(generatedKeys.getInt(1));
          return toSave;
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public boolean update(Balance toSave) {
    String updateQuery = "UPDATE balance SET date=?, amount=?, typeBalance=? WHERE idBalance=?";
    try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
      updateStatement.setTimestamp(1, Timestamp.valueOf(toSave.getDate()));
      updateStatement.setDouble(2, toSave.getAmount());
      updateStatement.setString(3, toSave.getTypeBalance());
      updateStatement.setInt(4, toSave.getIdBalance());

      int rowsAffected = updateStatement.executeUpdate();
      return rowsAffected > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

}
