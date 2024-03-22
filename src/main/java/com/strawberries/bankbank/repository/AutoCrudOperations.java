package com.strawberries.bankbank.repository;

import java.lang.reflect.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoCrudOperations<T> implements CrudOperations<T> {
    private final Connection connection;
    public AutoCrudOperations(Connection connection) {
        this.connection = connection;
    }
    private List<String> getAttributes(Class<T> clazz) throws IllegalAccessException {
        List<String> attributeNames = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
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

        List<String> attributeNames;
        try {
            attributeNames = getAttributes(clazz);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return resultList;
        }
        String tableName = getTableName(clazz);
        String FIND_ALL_QUERY = "SELECT {COLUMNS} FROM {TABLES}";
        String query = FIND_ALL_QUERY
                .replace("{COLUMNS}", String.join(", ", attributeNames))
                .replace("{TABLES}", tableName);
        System.out.println("Generated SQL query: " + query);
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                T instance = clazz.getDeclaredConstructor().newInstance();
                for (String attributeName : attributeNames) {
                    try {
                        Field field = clazz.getDeclaredField(attributeName);
                        field.setAccessible(true);
                        if (field.getType() == LocalDateTime.class) {
                            Object value = resultSet.getTimestamp(attributeName).toLocalDateTime();
                            field.set(instance, value);
                        } else {
                            Object value = resultSet.getObject(attributeName);
                            field.set(instance, value);
                        }

                    } catch (NoSuchFieldException | IllegalAccessException | SQLException e) {
                        e.printStackTrace();
                        throw new RuntimeException("Error processing result set", e);
                    }
                }
                resultList.add(instance);
            }

        } catch (SQLException | IllegalAccessException | InstantiationException |
                 NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        return resultList;
    }
    @Override
    public T save(T toSave) {
        if (toSave == null) {
            throw new IllegalArgumentException("Object to save is null");
        }

        String tableName = getTableName((Class<T>) toSave.getClass());
        String SAVE_QUERY = "INSERT INTO " + tableName + " ({COLUMN_NAMES}) VALUES ({PLACEHOLDERS})";

        List<String> attributeNames;
        try {
            attributeNames = getAttributes((Class<T>) toSave.getClass());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }

        String query = SAVE_QUERY
                .replace("{COLUMN_NAMES}", String.join(", ", attributeNames))
                .replace("{PLACEHOLDERS}", String.join(", ", Collections.nCopies(attributeNames.size(), "?")));

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            for (int i = 0; i < attributeNames.size(); i++) {
                try {
                    Field field = toSave.getClass().getDeclaredField(attributeNames.get(i));
                    field.setAccessible(true);
                    statement.setObject(i + 1, field.get(toSave));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving entity", e);
        }
        return toSave;
    }


    @Override
    public boolean update(T entity) {
        Class<?> clazz = entity.getClass();
        List<String> attributeNames;
        try {
            attributeNames = getAttributes((Class<T>) clazz);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }

        String tableName = getTableName((Class<T>) clazz);

        StringBuilder updateColumns = new StringBuilder();
        for (String attributeName : attributeNames) {
            if (!attributeName.equals("id")) {
                updateColumns.append(attributeName).append(" = ?, ");
            }
        }


        if (updateColumns.length() > 0) {
            updateColumns.delete(updateColumns.length() - 2, updateColumns.length()); // Supprimer la virgule finale et l'espace
        } else {
            throw new IllegalArgumentException("No columns to update specified (except ID)");
        }


        String UPDATE_QUERY = "UPDATE " + tableName + " SET " + updateColumns.toString() + " WHERE id = ?";
        System.out.println("Generated SQL query: " + UPDATE_QUERY);

        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            setParameters(statement, entity, attributeNames);
            statement.setObject(attributeNames.size() + 1, getPrimaryKeyValue(entity));
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating entity", e);
        }
    }

    private Object getPrimaryKeyValue(T entity) throws IllegalAccessException {
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals("id")) {
                field.setAccessible(true);
                return field.get(entity);
            }
        }
        throw new IllegalArgumentException("Primary key not found in entity");
    }

    private void setParameters(PreparedStatement statement, T entity, List<String> attributeNames) throws SQLException, IllegalAccessException {
        for (int i = 0; i < attributeNames.size(); i++) {
            String attributeName = attributeNames.get(i);
            Field field = null;
            try {
                field = entity.getClass().getDeclaredField(attributeName);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            if (field != null) {
                field.setAccessible(true);
                Object value = field.get(entity);
                if (value instanceof LocalDateTime) {
                    statement.setObject(i + 1, Timestamp.valueOf((LocalDateTime) value));
                } else {
                    statement.setObject(i + 1, value);
                }
            }
        }
    }


}
