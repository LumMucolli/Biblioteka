package com.probitacademy.biblioteka.dao;
import java.sql.*;
public class ConnectionFactory {
public static final String JDBC_URL = "jdbc:mariadb://localhost:3306/bookrent";
public static final String JDBC_USER = "root";
public static final String JDBC_PASSWORD = "";
public static Connection getConnection() {
try {
return DriverManager.getConnection(JDBC_URL, JDBC_USER,
JDBC_PASSWORD);
} catch (SQLException ex) {
throw new RuntimeException("Nuk mund te krijohet lidhje",
ex);
}
}
}