package net.laboratorioderedes.dao;

import net.laboratorioderedes.Product;
import net.laboratorioderedes.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private final Connection connection = DBConnection.getConnection();
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private String query;

    //Listar todos los productos
    public List<Product> listAllProducts() {
        List<Product> products = new ArrayList<>();
        query = "SELECT * FROM products";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(new Product(resultSet.getLong("idProduct"), resultSet.getString("name"),
                        resultSet.getDouble("price")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    //Buscar un producto por su id
    public Product findById(int id) {
        query = "SELECT * FROM products WHERE idproduct = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Product(resultSet.getLong("idproduct"), resultSet.getString("name"),
                        resultSet.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Product not found");
        System.exit(1); // Terminates the program with exit code 1.
        return null;
    }

    public void save(Product product) {
        query = "INSERT INTO products (name, price) VALUES (?, ?)";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Product saved");
        System.exit(1); // Terminates the program with exit code 1.
    }

}
