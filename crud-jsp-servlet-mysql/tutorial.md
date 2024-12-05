
# Tutorial: CRUD con JSP, Servlets y MySQL

---

## 1. Requisitos Previos

### Software Necesario:

- **Java JDK** (versión 8 o superior).
- **Apache Tomcat** (versión 9 o superior).
- **MySQL Server**.
- **IDE** (Netbeans, Eclipse o IntelliJ IDEA).
- **Conector MySQL para Java** (`mysql-connector-java.jar`).

---

## 2. Configuración de la Base de Datos

1. **Crear Base de Datos:**

```sql
CREATE DATABASE crud_jsp_servlet;
```

2. **Crear Tabla:**

```sql
USE crud_jsp_servlet;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    country VARCHAR(100) NOT NULL
);
```

3. **Insertar Datos Iniciales (Opcional):**

```sql
INSERT INTO users (name, email, country) VALUES
('Juan', 'juan@example.com', 'USA'),
('Mary Smith', 'mary@example.com', 'UK');
```

---

## 3. Crear Proyecto en el IDE

1. **Nuevo Proyecto:**
   - En tu IDE, crea un proyecto de tipo *Dynamic Web Project*.

2. **Configurar Apache Tomcat:**
   - Asegúrate de configurar Apache Tomcat como el servidor.

3. **Agregar `mysql-connector-java.jar`:**
   - Descarga el archivo JAR desde [MySQL Connector](https://dev.mysql.com/downloads/connector/j/).
   - Añádelo al proyecto en `WEB-INF/lib`.

---

## 4. Estructura del Proyecto

```
WebContent/
├── index.jsp
├── add-user.jsp
├── edit-user.jsp
├── list-users.jsp
WEB-INF/
├── web.xml
src/
└── com.example.crud
    ├── User.java
    ├── UserDAO.java
    ├── UserServlet.java
```

---

## 5. Implementación

### 5.1. Clase `User.java`

```java
package com.example.crud;

public class User {
    private int id;
    private String name;
    private String email;
    private String country;

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
```

### 5.2. Clase `UserDAO.java`

```java
package com.example.crud;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/crud_jsp_servlet";
    private String jdbcUsername = "root";
    private String jdbcPassword = "password";

    private static final String INSERT_USERS_SQL = "INSERT INTO users (name, email, country) VALUES (?, ?, ?);";
    private static final String SELECT_USER_BY_ID = "SELECT id, name, email, country FROM users WHERE id = ?;";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users;";
    private static final String DELETE_USERS_SQL = "DELETE FROM users WHERE id = ?;";
    private static final String UPDATE_USERS_SQL = "UPDATE users SET name = ?, email = ?, country = ? WHERE id = ?;";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertUser(User user) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User selectUser(int id) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                user = new User();
                user.setId(id);
                user.setName(name);
                user.setEmail(email);
                user.setCountry(country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setEmail(email);
                user.setCountry(country);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getCountry());
            statement.setInt(4, user.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}
```

### 5.3. Clase `UserServlet.java`

```java
package com.example.crud;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/")
public class UserServlet extends HttpServlet {
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertUser(request, response);
                    break;
                case "/delete":
                    deleteUser(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateUser(request, response);
                    break;
                default:
                    listUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        List<User> listUser = userDAO.selectAllUsers();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("list-users.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("add-user.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userDAO.selectUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit-user.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setCountry(country);
        userDAO.insertUser(newUser);
        response.sendRedirect("list");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        user.setCountry(country);

        userDAO.updateUser(user);
        response.sendRedirect("list");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDAO.deleteUser(id);
        response.sendRedirect("list");
    }
}
```

### 1. index.jsp
Este archivo sirve como la página de inicio y redirige al listado de usuarios.

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>CRUD con JSP y Servlets</title>
</head>
<body>
    <h1>Bienvenido al CRUD</h1>
    <a href="list-users.jsp">Ver Lista de Usuarios</a>
</body>
</html>

```
### 2. list-users.jsp
Lista todos los usuarios en la base de datos con opciones para agregar, editar y eliminar.

```jsp
<%@ page import="com.example.crud.User" %>
<%@ page import="com.example.crud.UserDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Usuarios</title>
</head>
<body>
    <h1>Usuarios</h1>
    <a href="add-user.jsp">Agregar Nuevo Usuario</a>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Email</th>
                <th>País</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <%
                UserDAO userDAO = new UserDAO();
                for (User user : userDAO.selectAllUsers()) {
            %>
            <tr>
                <td><%= user.getId() %></td>
                <td><%= user.getName() %></td>
                <td><%= user.getEmail() %></td>
                <td><%= user.getCountry() %></td>
                <td>
                    <a href="edit-user.jsp?id=<%= user.getId() %>">Editar</a>
                    <a href="UserServlet?action=delete&id=<%= user.getId() %>" onclick="return confirm('¿Estás seguro?')">Eliminar</a>
                </td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>

```
### 3. add-user.jsp
Formulario para agregar un nuevo usuario.
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agregar Usuario</title>
</head>
<body>
    <h1>Agregar Nuevo Usuario</h1>
    <form action="UserServlet" method="post">
        <input type="hidden" name="action" value="insert">
        <label for="name">Nombre:</label>
        <input type="text" id="name" name="name" required><br><br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>
        <label for="country">País:</label>
        <input type="text" id="country" name="country" required><br><br>
        <button type="submit">Guardar</button>
    </form>
</body>
</html>
```
### 4. edit-user.jsp
Formulario para editar un usuario existente.
```jsp
<%@ page import="com.example.crud.User" %>
<%@ page import="com.example.crud.UserDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Usuario</title>
</head>
<body>
    <%
        int id = Integer.parseInt(request.getParameter("id"));
        UserDAO userDAO = new UserDAO();
        User user = userDAO.selectUser(id);
    %>
    <h1>Editar Usuario</h1>
    <form action="UserServlet" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%= user.getId() %>">
        <label for="name">Nombre:</label>
        <input type="text" id="name" name="name" value="<%= user.getName() %>" required><br><br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="<%= user.getEmail() %>" required><br><br>
        <label for="country">País:</label>
        <input type="text" id="country" name="country" value="<%= user.getCountry() %>" required><br><br>
        <button type="submit">Actualizar</button>
    </form>
</body>
</html>
<%@ page import="com.example.crud.User" %>
<%@ page import="com.example.crud.UserDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Usuario</title>
</head>
<body>
    <%
        int id = Integer.parseInt(request.getParameter("id"));
        UserDAO userDAO = new UserDAO();
        User user = userDAO.selectUser(id);
    %>
    <h1>Editar Usuario</h1>
    <form action="UserServlet" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%= user.getId() %>">
        <label for="name">Nombre:</label>
        <input type="text" id="name" name="name" value="<%= user.getName() %>" required><br><br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="<%= user.getEmail() %>" required><br><br>
        <label for="country">País:</label>
        <input type="text" id="country" name="country" value="<%= user.getCountry() %>" required><br><br>
        <button type="submit">Actualizar</button>
    </form>
</body>
</html>

```
##FIN
