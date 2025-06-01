package org.example.util;

import java.sql.*;

public class ConexaoFactory{ //OBS: usei MySQL os arquivos estão na pasta infomazDB
        private static final String URL = "***";
        private static final String USER = "**";
        private static final String PASSWORD = "***";

        public static Connection getConexao() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
    }
