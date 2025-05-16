package org.example;

import org.example.dao.*;
import org.example.service.*;
import org.example.util.ConexaoFactory;

import java.util.*;
import java.sql.*;

public class Main { //Esse código foi baseado em 10 desafios que encontram-se nas ultimas linhas desse arquivo
    public static void main(String[] args) throws SQLException {  //Obs: infelizmente nao consegui aplicar outras boas praticas como controllers devido ao tempo
        Scanner sc = new Scanner(System.in);

        try (Connection conn = ConexaoFactory.getConexao()) { // fazendo conexao com o banco
            ClienteDAO clienteDAO = new ClienteDAO(conn);
            EstoqueDAO estoqueDAO = new EstoqueDAO(conn);
            FornecedorDAO fornecedorDAO = new FornecedorDAO(conn);
            ProdutoDAO produtoDAO = new ProdutoDAO(conn);
            VendaDAO vendaDAO = new VendaDAO(conn);

            System.out.println("Conexão bem sucedida.");
            String opcao;
            do {

                System.out.println("\n[ 1 ] Relatorio de categoria\n[ 2 ] Margem dos produtos\n[ 3 ] Ranking Clientes" +
                        "\n[ 4 ] Ranking Fornecedores\n[ 5 ] Ranking Produtos por QTD\n[ 6 ] Ranking Produtos por Venda" +
                        "\n[ 7 ] Média de Vendas\n[ 8 ] Margem de Lucro\n[ 9 ] Histórico de compras por cliente\n[ 10 ] Ranking de Estoque\n[ 0 ] Sair");
                opcao = sc.nextLine();
                switch (opcao) {
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
                        break;
                    case "4":
                        System.out.println("\n---Ranking Fornecedores---\n");
                        RankingFornecedorService rnkFornecedorSvs = new RankingFornecedorService(estoqueDAO, fornecedorDAO);
                        rnkFornecedorSvs.CalcularRanking();
                        rnkFornecedorSvs.exibirRanking();
                        break;
                    case "5":
                        System.out.println("\n---Ranking Produtos por QTD---\n");
                        RankingProdutoQtdService rnkProdQtd = new RankingProdutoQtdService(vendaDAO, produtoDAO);
                        rnkProdQtd.CalcularRanking();
                        rnkProdQtd.exibirRanking();
                        break;
                    case "6":
                        System.out.println("\n---Ranking Produtos por Venda---\n");
                        RkProdutoVendaService rkProdutoVendaService = new RkProdutoVendaService(vendaDAO, produtoDAO);
                        rkProdutoVendaService.CalcularRanking();
                        rkProdutoVendaService.exibirRanking();
                        break;
                    case "7":
                        System.out.println("\n---Média Vendas---\n");
                        MediaValorService mvservice = new MediaValorService(produtoDAO, vendaDAO);
                        mvservice.calculaMediaVendas();
                        System.out.println(mvservice.exibirMediaVendas());
                        break;
                    case "8":
                        System.out.println("\n---Ranking Produtos por Margem de Lucro---\n");
                        MargemLucroCategoriaService mlcService = new MargemLucroCategoriaService(estoqueDAO, produtoDAO, vendaDAO);
                        mlcService.calcularRankingLucro();
                        mlcService.exibirRanking();
                        break;
                    case "9":
                        System.out.println("\n---Histórico de compras por cliente---\n");
                        HistoricoClienteService hcService = new HistoricoClienteService(clienteDAO, vendaDAO, produtoDAO);
                        hcService.gerarHistorico();
                        System.out.println(hcService.getHistoricoClientes());
                        break;
                    case "10":
                        System.out.println("\n---Ranking Produtos por QTD---\n");
                        RkEstoqueService rkEstoqueService = new RkEstoqueService(produtoDAO, estoqueDAO);
                        rkEstoqueService.gerarRankingEstoque();
                        rkEstoqueService.exibirRankingEstoque();
                        break;
                    case "0":
                        System.out.println("Encerrando...");
                        break;
                    default:
                        System.out.println("Opção inválida");
                        break;
                }
            } while (!opcao.equals("0"));
        } catch (SQLException e) {
            System.err.println("ERRO NA CONEXÃO: " + e.getMessage());
        }
        sc.close();
    }
}
    /*
1) Calcule o valor total de venda dos produtos por categoria. Utilize as tabelas CADASTRO_PRODUTOS e TRANSACOES_VENDAS.
2) Calcule a margem dos produtos subtraindo o valor unitario pelo valor de venda. Utilize as tabelas CADASTRO_ESTOQUE e TRANSACOES_VENDAS.
3) Calcule um ranking de clientes por quantidade de produtos comprados por mês. Utilize as tabelas CADASTRO_CLIENTES e TRANSACOES_VENDAS.
4) Calcule um ranking de fornecedores por quantidade de estoque disponivel por mês. Utilize as tabelas CADASTRO_FORNECEDORES e CADASTRO_ESTOQUE.
5) Calcule um ranking de produtos por quantidade de venda por mês. Utilize a tabela TRANSACOES_VENDAS.
6) Calcule um ranking de produtos por valor de venda por mês. Utilize a tabela TRANSACOES_VENDAS.
7) Calcule a média de valor de venda por categoria de produto por mês. Utiliza as tabelas CADASTRO_PRODUTOS e TRANSACOES_VENDAS.
8) Calcule um ranking de margem de lucro por categoria
9) Liste produtos comprados por clientes
10) Ranking de produtos por quantidade de estoque
*/