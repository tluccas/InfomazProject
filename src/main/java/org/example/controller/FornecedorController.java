package org.example.controller;

import org.example.dao.EstoqueDAO;
import org.example.dao.FornecedorDAO;
import org.example.service.RankingFornecedorService;

import java.sql.SQLException;

public class FornecedorController {
    private final EstoqueDAO estoqueDAO;
    private final FornecedorDAO fornecedorDAO;

    public FornecedorController(FornecedorDAO fornecedorDAO, EstoqueDAO estoqueDAO) {
        this.fornecedorDAO = fornecedorDAO;
        this.estoqueDAO = estoqueDAO;
    }

    public void rankingFornecedores() throws SQLException {
        System.out.println("\n---Ranking Fornecedores---\n");
        RankingFornecedorService service = new RankingFornecedorService(estoqueDAO, fornecedorDAO);
        service.CalcularRanking();
        service.exibirRanking();
    }
}
