package org.example;

import org.example.dao.*;
import org.example.service.*;
import org.example.util.ConexaoFactory;

import java.util.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {  //Obs: infelizmente nao consegui aplicar outras boas praticas como controllers devido ao tempo
        Scanner sc = new Scanner(System.in);

        try (Connection conn = ConexaoFactory.getConexao()) { // fazendo conexao com o banco
            ClienteDAO clienteDAO = new ClienteDAO(conn);
            EstoqueDAO estoqueDAO = new EstoqueDAO(conn);
            FornecedorDAO fornecedorDAO = new FornecedorDAO(conn);
            ProdutoDAO produtoDAO = new ProdutoDAO(conn);
            VendaDAO vendaDAO = new VendaDAO(conn);

            System.out.println("Conexão bem sucedida :).");

            System.out.println("\n[ 1 ] Relatorio de categoria\n[ 2 ] Margem dos produtos\n[ 3 ] Ranking Clientes" +
                    "\n[ 4 ] Ranking Fornecedores\n[ 5 ] Ranking Produtos por QTD\n[ 6 ] Ranking Produtos por Venda" +
                    "\n[ 7 ] Média de Vendas");

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
                case "4":
                    System.out.println("\n---Ranking Fornecedores---\n");
                    RankingFornecedorService rnkFornecedorSvs = new RankingFornecedorService(estoqueDAO, fornecedorDAO);
                    rnkFornecedorSvs.CalcularRanking();
                    rnkFornecedorSvs.exibirRanking();
                case "5":
                    System.out.println("\n---Ranking Produtos por QTD---\n");
                    RankingProdutoQtdService rnkProdQtd = new RankingProdutoQtdService(vendaDAO, produtoDAO);
                    rnkProdQtd.CalcularRanking();
                    rnkProdQtd.exibirRanking();

                case "6":
                    System.out.println("\n---Ranking Produtos por Venda---\n");
                    RkProdutoVendaService rkProdutoVendaService = new RkProdutoVendaService(vendaDAO, produtoDAO);
                    rkProdutoVendaService.CalcularRanking();
                    rkProdutoVendaService.exibirRanking();
                case "7":
                    System.out.println("\n---Média Vendas---\n");
                    MediaValorService mvservice = new MediaValorService(produtoDAO, vendaDAO);
                    mvservice.calculaMediaVendas();
                    System.out.println(mvservice.exibirMediaVendas());

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