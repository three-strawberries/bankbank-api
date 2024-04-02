package com.strawberries.bankbank.repository;

import com.strawberries.bankbank.db.ConnectDB;
import com.strawberries.bankbank.entity.Account;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountCrudOperations implements CrudOperations<Account> {
    private final ConnectDB db = ConnectDB.getInstance();
    private final Connection connection = db.getConnection();

    @Override
    public List<Account> findAll() {
        List<Account> accountList = new ArrayList<>();
        String sql = "SELECT * FROM account ORDER BY lastName, firstName";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Account account = new Account(
                        resultSet.getInt("idAccount"),
                        resultSet.getString("lastName"),
                        resultSet.getString("firstName"),
                        resultSet.getDate("birthDate"),
                        resultSet.getDouble("monthlySalary"),
                        resultSet.getString("authorizedCredit"),
                        resultSet.getString("bankName"),
                        resultSet.getInt("idBalance")
                );
                accountList.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountList;
    }

    @Override
    public Account save(Account toSave) {
        String insertQuery = "INSERT INTO account (lastName, firstName, birthDate, monthlySalary, authorizedCredit, bankName, idBalance) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            insertStatement.setString(1, toSave.getLastName());
            insertStatement.setString(2, toSave.getFirstName());
            insertStatement.setDate(3, toSave.getBirthDate());
            insertStatement.setDouble(4, toSave.getMonthlySalary());
            insertStatement.setString(5, toSave.getAuthorizedCredit());
            insertStatement.setString(6, toSave.getBankName());
            insertStatement.setInt(7, toSave.getIdBalance());

            int rowsAffected = insertStatement.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = insertStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    toSave.setIdAccount(generatedKeys.getInt(1));
                    return toSave;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Account toSave) {
        String updateQuery = "UPDATE account SET lastName=?, firstName=?, birthDate=?, " +
                "monthlySalary=?, authorizedCredit=?, bankName=?, " +
                "idBalance=? WHERE idAccount=?";

        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
            updateStatement.setString(1, toSave.getLastName());
            updateStatement.setString(2, toSave.getFirstName());
            updateStatement.setDate(3, toSave.getBirthDate());
            updateStatement.setDouble(4, toSave.getMonthlySalary());
            updateStatement.setString(5, toSave.getAuthorizedCredit());
            updateStatement.setString(6, toSave.getBankName());
            updateStatement.setInt(7, toSave.getIdBalance());
            updateStatement.setInt(8, toSave.getIdAccount());

            int rowsAffected = updateStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

