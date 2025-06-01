package org.example.controller;

import org.example.dao.ProdutoDAO;
import org.example.dao.EstoqueDAO;
import org.example.service.RkEstoqueService;

import java.sql.SQLException;

public class EstoqueController {
    private final ProdutoDAO produtoDAO;
    private final EstoqueDAO estoqueDAO;

    public EstoqueController(ProdutoDAO produtoDAO, EstoqueDAO estoqueDAO) {
        this.produtoDAO = produtoDAO;
        this.estoqueDAO = estoqueDAO;
    }

    public void rankingEstoque() throws SQLException {
        System.out.println("\n---Ranking Produtos por Estoque---\n");
        RkEstoqueService service = new RkEstoqueService(produtoDAO, estoqueDAO);
        service.gerarRankingEstoque();
        service.exibirRankingEstoque();
    }
}
