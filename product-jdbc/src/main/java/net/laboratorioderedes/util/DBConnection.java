package net.laboratorioderedes.util;

import java.sql.Connection;

public class DBConnection {

    private static Connection connection = null;
    private static final String url = "jdbc:mysql://localhost:3306/sales_db";
    private static final String user = "root";
    private static final String password = "";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";


    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            connection = java.sql.DriverManager.getConnection(url, user, password);
            System.out.println("successful connection");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}


