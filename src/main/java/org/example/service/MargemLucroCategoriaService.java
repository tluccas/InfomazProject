package org.example.service;

import org.example.dao.EstoqueDAO;
import org.example.dao.ProdutoDAO;
import org.example.dao.VendaDAO;
import org.example.model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MargemLucroCategoriaService {
    private EstoqueDAO estoqueDAO;
    private ProdutoDAO produtoDAO;
    private VendaDAO vendaDAO;
    private List<MargemLucroCategoria> rankingLucro;

    public MargemLucroCategoriaService(EstoqueDAO estoqueDAO, ProdutoDAO produtoDAO, VendaDAO vendaDAO) {
        this.estoqueDAO = estoqueDAO;
        this.produtoDAO = produtoDAO;
        this.vendaDAO = vendaDAO;
        this.rankingLucro = new ArrayList<MargemLucroCategoria>();
    }

    public void calcularRankingLucro() throws SQLException {
        List<Venda> vendas = vendaDAO.listarVenda();
        List<MargemLucroCategoria> rank = new ArrayList<>();

        for (Venda v : vendas) {
            int idProduto = v.getIdProduto();
            String categoria = produtoDAO.buscarProduto(idProduto).getCategoria();
            int idPrdEstoque = produtoDAO.buscarProduto(idProduto).getIdEstoque();
            Estoque estoque = estoqueDAO.buscarEstoque(idPrdEstoque);
            if (estoque == null) {
                System.out.println("Estoque não encontrado para o produto ID: " + idProduto);
                continue;  // ignora produtos que nao estão registrados em estoque
            }

            double custoUnitario = estoque.getValorEstoque() / estoque.getQtdEstoque();
            double custoTotalVenda = custoUnitario * v.getQtdItem();
            /* 'valorEstoque' é o custo total do estoque atual. Para calcular o custo da venda,
 divide-se pelo estoque disponível para obter o custo unitário e o multiplicamos pela quantidade vendida,
 foi a solução que procurei e achei pois ao checar a tabela estoque e fazer alguns testes na calculadora vi que existe um certo lucro por valor unitario na tabela vendas.*/

            MargemLucroCategoria existente = null;
            for (MargemLucroCategoria m : rank) {
                if (m.getCategoria().equals(categoria)) {
                    existente = m;
                    break;
                }
            }
            if (existente != null) {
                existente.addCusto(custoTotalVenda);
                existente.addVenda(v.getValorItem(), v.getQtdItem());
            } else {
                MargemLucroCategoria novo = new MargemLucroCategoria(categoria); //Os demais services de ranking seguem essa mesma lógica
                novo.addCusto(custoTotalVenda);
                novo.addVenda(v.getValorItem(), v.getQtdItem());
                rank.add(novo);
            }
        }
        for (MargemLucroCategoria m : rank) {
            m.calcularLucro();
        }

        this.rankingLucro = rank;
    }

    public List<MargemLucroCategoria> getRanking() {
        rankingLucro.sort(Comparator.comparingDouble(MargemLucroCategoria::getLucroTotal).reversed());
        return rankingLucro;
    }

    public void exibirRanking() {
        List<MargemLucroCategoria> ranking = getRanking();
        int posicao = 1;

        for (MargemLucroCategoria m : ranking) {
            System.out.println(posicao + "º - " + m);
            posicao++;
        }
    }
}
