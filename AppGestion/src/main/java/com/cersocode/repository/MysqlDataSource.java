package com.cersocode.repository;

import com.cersocode.model.DataSourceStrategy;
import com.cersocode.model.Product;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlDataSource implements DataSourceStrategy {

    String url = "jdbc:mysql://localhost:3306/cerso";
    String user = "root";
    String passwd = "";
    String sql = "";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, passwd);
    }

    @Override
    public void save(Product product) {
        try (Connection connection = getConnection()) {
            sql = "INSERT INTO products (name, price) VALUES (?, ?)";
            var statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product findById(int id) {
        try (Connection connection = getConnection()) {
            sql = "SELECT * FROM products WHERE id = ?";
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();

        try (Connection connection = getConnection()) {
            sql = "SELECT * FROM products";
            var statement = connection.prepareStatement(sql);
            var resultSet = statement.executeQuery();
            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public void update(Product product) {
        try (Connection connection = getConnection()) {
            sql = "UPDATE products SET name = ?, price = ? WHERE id = ?";
            var statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(int id) {
        try (Connection connection = getConnection()) {
            sql = "DELETE FROM products WHERE id = ?";
            var statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
