package org.example.controller;

import org.example.dao.ProdutoDAO;
import org.example.dao.VendaDAO;
import org.example.dao.EstoqueDAO;
import org.example.service.MargemProdutoService;
import org.example.service.MargemLucroCategoriaService;
import org.example.service.RankingProdutoQtdService;
import org.example.service.RkProdutoVendaService;

import java.sql.SQLException;

public class ProdutoController {
    private final ProdutoDAO produtoDAO;
    private final VendaDAO vendaDAO;
    private final EstoqueDAO estoqueDAO;

    public ProdutoController(ProdutoDAO produtoDAO, VendaDAO vendaDAO, EstoqueDAO estoqueDAO) {
        this.produtoDAO = produtoDAO;
        this.vendaDAO = vendaDAO;
        this.estoqueDAO = estoqueDAO;
    }

    public void margemProdutos() throws SQLException {
        System.out.println("\n---Margem dos produtos---\n");
        MargemProdutoService service = new MargemProdutoService(vendaDAO, estoqueDAO, produtoDAO);
        service.gerarMargem();
        service.exibirMargemProdutos();
    }

    public void rankingPorQuantidade() throws SQLException {
        System.out.println("\n---Ranking Produtos por QTD---\n");
        RankingProdutoQtdService service = new RankingProdutoQtdService(vendaDAO, produtoDAO);
        service.CalcularRanking();
        service.exibirRanking();
    }

    public void rankingPorVenda() throws SQLException {
        System.out.println("\n---Ranking Produtos por Venda---\n");
        RkProdutoVendaService service = new RkProdutoVendaService(vendaDAO, produtoDAO);
        service.CalcularRanking();
        service.exibirRanking();
    }

    public void rankingMargemLucro() throws SQLException {
        System.out.println("\n---Ranking Produtos por Margem de Lucro---\n");
        MargemLucroCategoriaService service = new MargemLucroCategoriaService(estoqueDAO, produtoDAO, vendaDAO);
        service.calcularRankingLucro();
        service.exibirRanking();
    }
}
