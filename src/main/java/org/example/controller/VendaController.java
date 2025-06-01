package org.example.controller;

import org.example.dao.ProdutoDAO;
import org.example.dao.VendaDAO;
import org.example.service.RelatorioCategoriaService;
import org.example.service.MediaValorService;

import java.sql.SQLException;

public class VendaController {
    private final VendaDAO vendaDAO;
    private final ProdutoDAO produtoDAO;

    public VendaController(ProdutoDAO produtoDAO, VendaDAO vendaDAO) {
        this.produtoDAO = produtoDAO;
        this.vendaDAO = vendaDAO;
    }

    public void relatorioCategoria() throws SQLException {
        System.out.println("\n---Relatório de categoria---\n");
        RelatorioCategoriaService service = new RelatorioCategoriaService(vendaDAO, produtoDAO);
        service.gerarRelatorio();
        service.exibirRelatorios();
    }

    public void mediaVendas() throws SQLException {
        System.out.println("\n---Média de vendas---\n");
        MediaValorService service = new MediaValorService(produtoDAO, vendaDAO);
        service.calculaMediaVendas();
        service.exibirMediaVendas();
    }
}
