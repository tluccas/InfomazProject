package org.example.util;

import java.sql.*;

public class ConexaoFactory{
        private static final String URL = "jdbc:mysql://localhost:3306/infomaz?user=root";
        private static final String USER = "root";
        private static final String PASSWORD = "rootlocadora";

        public static Connection getConexao() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
    }
