### Ejercicio 1
Crear una clase DBConnection que contenga un método estático que devuelva una conexión a la base de datos.
El método debe manejar las excepciones y devolver null en caso de error.
En el main, llamar al método y mostrar un mensaje indicando si la conexión fue exitosa o no.

```java
ackage org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection = null;
    private static final String USER = "cerso";
    private static final String PASSWORD = "cerso";
    private static final String URL = "jdbc:mysql://localhost:3306/empresa";

    public static Connection getConnection(){

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexion exitosa");
        } catch (SQLException e) {
            System.out.println("Error de conexion");
            e.printStackTrace();
        }
        return connection;
    }
}

package org.example.ejer1;

import java.sql.Connection;
import org.example.DBConnection;

public class Ejer1 {
    public static void main(String[] args) {
       Connection connection=  DBConnection.getConnection();
    }
}
```
### Ejercicio 2
Instar un registro en la tabla de empleados con los siguientes datos:
```java
package org.example.ejer2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.example.DBConnection;

/**
 *
 * @author cerso
 */
public class Ejer2 {
    
    public static void main(String[] args) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO empleados (nombre, apellido, email, salario) VALUES (?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "vicente");
            preparedStatement.setString(2, "Cerso");
            preparedStatement.setString(3, "cerso@gmail.com");
            preparedStatement.setDouble(4, 50000.0);
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Un nuevo empleado fue insertado exitosamente!");
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar el registro");
            e.printStackTrace();
        }

    }
    
}
```
### Ejercicio 3
Desarrolla un programa que consulta todos los registros de la tabla productos y los muestrea en consola.
La tabla tiene los campos: id, nombre, precio, stock.
```java
package org.example.ejer3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.example.DBConnection;

/**
 *
 * @author cerso
 */
public class Ejer3 {

    public static void main(String[] args) {
        Connection connection = DBConnection.getConnection();

        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM productos";

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                double precio = resultSet.getDouble("precio");
                int stock = resultSet.getInt("stock");
                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Precio: " + precio + ", Stock: " + stock);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta");
            e.printStackTrace();
        }
    }

}
```
###  Ejercicio 4: Búsqueda por parámetro
Modifica el ejercicio anterior para que el usuario pueda buscar productos por nombre (o parte del nombre) desde la consola.
Utiliza un PreparedStatement para evitar inyecciones SQL.
Muestra los resultados en consola.
```java
package org.example.ejer4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.example.DBConnection;

/**
 *
 * @author cerso
 */
public class Ejer4 {

    public static void main(String[] args) {

        String nombreBuscado = "mon";
        String sql = "SELECT * FROM productos WHERE nombre LIKE ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = DBConnection.getConnection();
        try {

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + nombreBuscado + "%");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                double precio = resultSet.getDouble("precio");
                int stock = resultSet.getInt("stock");
                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Precio: " + precio + ", Stock: " + stock);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta");
            e.printStackTrace();
        }

    }

}

```
### Ejercicio 5
Crea una función que permita actualizar el salario de un empleado, 
recibiendo como parámetros su id y el nuevo salario.
```java
package org.example.ejer5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.example.DBConnection;

/**
 *
 * @author cerso
 */
public class Ejer5 {

    public static void main(String[] args) {
        int id = 2;
        double salario = 8000;
        actualizaSalario(id, salario);

    }

    public static void actualizaSalario(int id, double salario) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "update empleados set salario = ? where id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, salario);
            preparedStatement.setInt(2, id);
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Un nuevo salario fue actualizado exitosamente!");
            }
            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            System.out.println("Error al actualizar el registro");
            ex.printStackTrace();
        }

    }
}
```
### Ejercicio 6: Eliminar registros
Implemente un método para eliminar un registro de la tabla de empleados que recibe el id como parámetro.

```java
package org.example.ejer6;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.example.DBConnection;

/**
 *
 * @author cerso
 */
public class Ejer6 {
    public static void main(String[] args) {

        String sql = "DELETE FROM empleados WHERE id = ?";
        int id = 3;
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = null;
        
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id); 
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Un empleado fue eliminado exitosamente!");
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
```

