package org.example.util;

import java.sql.*;

public class ConexaoFactory{ //OBS: usei MySQL os arquivos estão na pasta infomazDB
        private static final String URL = "url do banco";
        private static final String USER = "usuario";
        private static final String PASSWORD = "senha";

        public static Connection getConexao() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
    }
