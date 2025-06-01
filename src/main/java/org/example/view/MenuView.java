package org.example.view;

import org.example.controller.*;
import org.example.dao.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuView {
    private final Scanner scanner;

    // Controllers
    private final ClienteController clienteController;
    private final ProdutoController produtoController;
    private final VendaController vendaController;
    private final FornecedorController fornecedorController;
    private final EstoqueController estoqueController;

    public MenuView(Scanner scanner, Connection conn) {
        this.scanner = scanner;

        // DAOs
        ClienteDAO clienteDAO = new ClienteDAO(conn);
        ProdutoDAO produtoDAO = new ProdutoDAO(conn);
        VendaDAO vendaDAO = new VendaDAO(conn);
        EstoqueDAO estoqueDAO = new EstoqueDAO(conn);
        FornecedorDAO fornecedorDAO = new FornecedorDAO(conn);

        // Controllers
        this.clienteController = new ClienteController(clienteDAO, vendaDAO, produtoDAO);
        this.produtoController = new ProdutoController(produtoDAO, vendaDAO, estoqueDAO);
        this.vendaController = new VendaController(produtoDAO, vendaDAO);
        this.fornecedorController = new FornecedorController(fornecedorDAO, estoqueDAO);
        this.estoqueController = new EstoqueController(produtoDAO, estoqueDAO);
    }

    public void exibirMenuPrincipal() throws SQLException {
        String opcao;
        do {
            System.out.println("\n[ 1 ] Relatório de categoria");
            System.out.println("[ 2 ] Margem dos produtos");
            System.out.println("[ 3 ] Ranking de clientes");
            System.out.println("[ 4 ] Ranking de fornecedores");
            System.out.println("[ 5 ] Ranking de produtos por QTD");
            System.out.println("[ 6 ] Ranking de produtos por venda");
            System.out.println("[ 7 ] Média de vendas");
            System.out.println("[ 8 ] Ranking por margem de lucro");
            System.out.println("[ 9 ] Histórico de compras por cliente");
            System.out.println("[10 ] Ranking de estoque");
            System.out.println("[ 0 ] Sair");
            System.out.print("Insira uma opção: ");
            opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> vendaController.relatorioCategoria();
                case "2" -> produtoController.margemProdutos();
                case "3" -> clienteController.rankingClientes();
                case "4" -> fornecedorController.rankingFornecedores();
                case "5" -> produtoController.rankingPorQuantidade();
                case "6" -> produtoController.rankingPorVenda();
                case "7" -> vendaController.mediaVendas();
                case "8" -> produtoController.rankingMargemLucro();
                case "9" -> clienteController.historicoCompras();
                case "10" -> estoqueController.rankingEstoque();
                case "0" -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (!opcao.equals("0"));
    }
}

