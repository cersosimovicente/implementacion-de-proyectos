
# 🧪 Laboratorio JDBC

🧠 Preguntas de Investigación sobre JDBC
1. ¿Qué es la API JDBC y cuál es su propósito principal en Java?
   - Investiga qué significa JDBC (Java Database Connectivity) y cómo permite la conexión entre aplicaciones Java y bases de datos.
     
2. ¿Qué es un Driver JDBC y cuál es su función?
   - Explica qué papel cumple un driver dentro de la arquitectura JDBC y cómo se relaciona con una base de datos específica (por ejemplo, MySQL, PostgreSQL).
     
3. ¿Qué tipo de drivers JDBC existen?
   - Investiga y clasifica los tipos de drivers según la especificación JDBC (Tipo 1, Tipo 2, Tipo 3 y Tipo 4). Da un ejemplo de cada uno.
     
4. ¿Qué interfaces componen la API JDBC y qué responsabilidad tiene cada una?
   - Describe brevemente la función de las clases e interfaces principales:
     - java.sql.Connection
     - java.sql.Statement y PreparedStatement
     - java.sql.ResultSet
     - java.sql.Driver
     - java.sql.SQLException
       
5. ¿Por qué JDBC está basado en interfaces? ¿Qué ventajas ofrece esto?
  - Reflexiona sobre los principios de programación orientada a interfaces y cómo esto permite desacoplar la lógica de negocio del acceso a datos.
    
6. ¿Cómo se implementan estas interfaces en un driver JDBC específico?
  - Da un ejemplo de cómo el driver de MySQL (com.mysql.cj.jdbc.Driver) implementa las interfaces de JDBC para funcionar correctamente con la base de datos.

7. ¿Qué clase o interfaz se encarga de registrar un driver JDBC?
  - Investiga qué hace el método DriverManager.registerDriver() y qué ocurre cuando se llama Class.forName("com.mysql.cj.jdbc.Driver").
    
8. ¿Qué rol juega el DriverManager en la arquitectura JDBC?
   - Describe su función como intermediario entre la aplicación Java y los drivers disponibles.

9. ¿Cuál es la diferencia entre Statement y PreparedStatement?
  - Explica por qué se recomienda usar PreparedStatement en la mayoría de los casos (seguridad, rendimiento, reutilización).

10. ¿Qué sucede internamente cuando se ejecuta una consulta SQL con JDBC?
  - Describe el flujo desde la llamada al método executeQuery() hasta la obtención de resultados con ResultSet.

❓ Preguntas Teóricas para Investigar
¿Qué es una inyección SQL y cómo se puede prevenir?

¿Qué pasa si una conexión no se cierra adecuadamente?

¿Qué patrones de diseño se pueden aplicar para manejar la capa de acceso a datos en Java?

¿Qué ventajas tiene usar un ORM frente a JDBC directo?


## 📁 Estructura de Paquetes

1. Crear una aplicacion Java Whit Maven.
   - 1.1 Crear la siguiente estructura de paquetes
```bash
src/
├── dao/                ← Acceso a datos (JDBC)
│   └── ProductDAO.java
├── model/              ← Modelo de datos
│   └── Product.java
├── service/            ← Lógica de negocio
│   └── ProductService.java
├── util/               ← Utilidades como conexión a la BD
│   └── ConnectionDB.java
└── app/                ← Punto de entrada
    └── Main.java
```
## 📦 Contenido por Paquete
### **model/Product.java**

```java
package model;

public class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private int quantity;

    // Getters, Setters, and Constructor
    public Product() {}

    public Product(int id, String name, String description, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and Setters omitted for brevity
}
```
###  **util/ConnectionDB.java**
```java
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/product_management", "root", "");
    }
}
```
### **dao/ProductDAO.java**

```java
package dao;

import model.Product;
import util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public void create(Product p) {
        String sql = "INSERT INTO products (name, description, price, quantity) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getName());
            stmt.setString(2, p.getDescription());
            stmt.setDouble(3, p.getPrice());
            stmt.setInt(4, p.getQuantity());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> list() {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Connection conn = ConnectionUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Product p = new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getInt("quantity")
                );
                productList.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public void update(Product p) {
        String sql = "UPDATE products SET name=?, description=?, price=?, quantity=? WHERE id=?";
        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getName());
            stmt.setString(2, p.getDescription());
            stmt.setDouble(3, p.getPrice());
            stmt.setInt(4, p.getQuantity());
            stmt.setInt(5, p.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM products WHERE id=?";
        try (Connection conn = ConnectionUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

```
### **app/Main.java**
```java
package app;

import model.Product;
import service.ProductService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProductService service = new ProductService();
        Scanner sc = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n1. Add product");
            System.out.println("2. List products");
            System.out.println("3. Update product");
            System.out.println("4. Delete product");
            System.out.println("0. Exit");
            System.out.print("Option: ");
            option = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (option) {
                case 1 -> {
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Description: ");
                    String desc = sc.nextLine();
                    System.out.print("Price: ");
                    double price = sc.nextDouble();
                    System.out.print("Quantity: ");
                    int quantity = sc.nextInt();
                    service.addProduct(new Product(0, name, desc, price, quantity));
                }
                case 2 -> {
                    List<Product> products = service.getAll();
                    products.forEach(p -> System.out.println(
                            p.getId() + " - " + p.getName() + " - $" + p.getPrice()));
                }
                case 3 -> {
                    System.out.print("Product ID: ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.print("New name: ");
                    String name = sc.nextLine();
                    System.out.print("Description: ");
                    String desc = sc.nextLine();
                    System.out.print("Price: ");
                    double price = sc.nextDouble();
                    System.out.print("Quantity: ");
                    int quantity = sc.nextInt();
                    service.updateProduct(new Product(id, name, desc, price, quantity));
                }
                case 4 -> {
                    System.out.print("ID of the product to delete: ");
                    int id = sc.nextInt();
                    service.deleteProduct(id);
                }
            }
        } while (option != 0);
    }
}
```

📤 Entregable Actualizado
Código fuente completo, con paquetes bien organizados.

Capturas de pantalla demostrando:

La estructura del proyecto en el IDE

Las operaciones funcionando en consola

Archivo README.md con:

Instrucciones de ejecución

Respuestas a las preguntas teóricas

