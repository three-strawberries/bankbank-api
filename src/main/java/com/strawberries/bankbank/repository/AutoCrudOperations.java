package com.strawberries.bankbank.repository;

import javax.sql.DataSource;
import java.lang.reflect.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoCrudOperations<T> implements CrudOperations<T> {
    private final DataSource dataSource;

    public AutoCrudOperations(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private List<String> getAttributes(Class<T> clazz) {
        List<String> attributeNames = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            attributeNames.add(field.getName());
        }
        return attributeNames;
    }

    private String getTableName(Class<T> clazz) {
        return clazz.getSimpleName();
    }

    @Override
    public List<T> findAll() {
        List<T> resultList = new ArrayList<>();
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];

        List<String> attributeNames = getAttributes(clazz);
        String tableName = getTableName(clazz);
        String FIND_ALL_QUERY = "SELECT {COLUMNS} FROM {TABLES}";
        String query = FIND_ALL_QUERY
                .replace("{COLUMNS}", String.join(", ", attributeNames))
                .replace("{TABLES}", tableName);
        System.out.println("Generated SQL query: " + query);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                T instance = clazz.getDeclaredConstructor().newInstance();
                for (String attributeName : attributeNames) {
                    Field field = clazz.getDeclaredField(attributeName);
                    field.setAccessible(true);
                    if (field.getType() == LocalDateTime.class) {
                        Object value = resultSet.getTimestamp(attributeName).toLocalDateTime();
                        field.set(instance, value);
                    } else {
                        Object value = resultSet.getObject(attributeName);
                        field.set(instance, value);
                    }
                }
                resultList.add(instance);
            }
        } catch (SQLException | IllegalAccessException | InstantiationException |
                 NoSuchMethodException | InvocationTargetException | NoSuchFieldException e) {
            throw new RuntimeException("Error executing query", e);
        }
        return resultList;
    }

    @Override
    public T save(T toSave) {
        if (toSave == null) {
            throw new IllegalArgumentException("Object to save is null");
        }

        Class<T> clazz = (Class<T>) toSave.getClass();
        String tableName = getTableName(clazz);
        String SAVE_QUERY = "INSERT INTO " + tableName + " ({COLUMN_NAMES}) VALUES ({PLACEHOLDERS})";

        List<String> attributeNames = getAttributes(clazz);
        String query = SAVE_QUERY
                .replace("{COLUMN_NAMES}", String.join(", ", attributeNames))
                .replace("{PLACEHOLDERS}", String.join(", ", Collections.nCopies(attributeNames.size(), "?")));

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            for (int i = 0; i < attributeNames.size(); i++) {
                Field field = clazz.getDeclaredField(attributeNames.get(i));
                field.setAccessible(true);
                statement.setObject(i + 1, field.get(toSave));
            }
            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException("Error saving entity", e);
        }
        return toSave;
    }

    @Override
    public boolean update(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity to update is null");
        }

        Class<T> clazz = (Class<T>) entity.getClass();
        String tableName = getTableName(clazz);

        List<String> attributeNames = getAttributes(clazz);
        StringBuilder updateColumns = new StringBuilder();
        for (String attributeName : attributeNames) {
            if (!attributeName.equals("id")) {
                updateColumns.append(attributeName).append(" = ?, ");
            }
        }

        if (updateColumns.length() > 0) {
            updateColumns.delete(updateColumns.length() - 2, updateColumns.length());
        } else {
            throw new IllegalArgumentException("No columns to update specified (except ID)");
        }

        String UPDATE_QUERY = "UPDATE " + tableName + " SET " + updateColumns.toString() + " WHERE id = ?";
        System.out.println("Generated SQL query: " + UPDATE_QUERY);

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            for (int i = 0; i < attributeNames.size(); i++) {
                Field field = clazz.getDeclaredField(attributeNames.get(i));
                field.setAccessible(true);
                statement.setObject(i + 1, field.get(entity));
            }
            Field idField = clazz.getDeclaredField("id");
            idField.setAccessible(true);
            statement.setObject(attributeNames.size() + 1, idField.get(entity));

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException | IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException("Error updating entity", e);
        }
    }
}
