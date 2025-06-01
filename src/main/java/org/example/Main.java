package org.example;

import org.example.util.ConexaoFactory;
import org.example.view.MenuView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (Connection conn = ConexaoFactory.getConexao()) {
            System.out.println("Conexão bem sucedida.");
            MenuView mainView = new MenuView(scanner, conn);
            mainView.exibirMenuPrincipal();
        } catch (SQLException e) {
            System.err.println("Erro na conexão: " + e.getMessage());
        }

        scanner.close();
    }
}
