package org.example;

import org.example.dao.*;
import org.example.service.*;
import org.example.util.ConexaoFactory;

import java.util.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        try (Connection conn = ConexaoFactory.getConexao()) { // fazendo conexao com o banco
            ClienteDAO clienteDAO = new ClienteDAO(conn);
            EstoqueDAO estoqueDAO = new EstoqueDAO(conn);
            FornecedorDAO fornecedorDAO = new FornecedorDAO(conn);
            ProdutoDAO produtoDAO = new ProdutoDAO(conn);
            VendaDAO vendaDAO = new VendaDAO(conn);

            System.out.println("Conexão bem sucedida.");

            System.out.println("\n[ 1 ] Relatorio de categoria\n[ 2 ] Margem dos produtos\n[ 3 ] Ranking Clientes");

            switch (sc.nextLine()) {
                case "1":
                    System.out.println("\n---Relatórios---\n");
                    RelatorioCategoriaService relservice = new RelatorioCategoriaService(vendaDAO, produtoDAO);
                    relservice.gerarRelatorio();
                    System.out.println(relservice.getRelatorios());
                    break;

                case "2":
                    System.out.println("\n---Margem dos produtos---\n");
                    MargemProdutoService margemService = new MargemProdutoService(vendaDAO, estoqueDAO, produtoDAO);
                    margemService.gerarMargem();
                    System.out.println(margemService.getMargemProdutos());
                    break;
                case "3":
                    System.out.println("\n---Ranking Clientes---\n");
                    RankingServiceCliente rankingServiceCliente = new RankingServiceCliente(vendaDAO, clienteDAO);
                    rankingServiceCliente.CalcularRanking();
                    rankingServiceCliente.exibirRanking();
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } catch (SQLException e) {
            System.err.println("ERRO NA CONEXÃO: " + e.getMessage());
        }
        sc.close();
    }
}