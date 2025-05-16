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

    // Método que calcula o ranking de lucro agrupado por categoria de produto
    public void calcularRankingLucro() throws SQLException {
        // Obtém todas as vendas registradas
        List<Venda> vendas = vendaDAO.listarVenda();
        // Lista temporária para armazenar o ranking durante o cálculo
        List<MargemLucroCategoria> rank = new ArrayList<>();

        // Para cada venda no sistema...
        for (Venda v : vendas) {
            // Obtém informações do produto e estoque
            int idProduto = v.getIdProduto();
            String categoria = produtoDAO.buscarProduto(idProduto).getCategoria();
            int idPrdEstoque = produtoDAO.buscarProduto(idProduto).getIdEstoque();

            // Busca o estoque correspondente
            Estoque estoque = estoqueDAO.buscarEstoque(idPrdEstoque);

            // Se não encontrar estoque, registra e pula para próxima venda (um dos produtos não estava com o ID de estoque correspondente gerando um erro)
            if (estoque == null) {
                System.out.println("Estoque não encontrado para o produto ID: " + idProduto);
                continue;
            }

            // CÁLCULO DO CUSTO: (alterei após pesquisar sobre margem de lucro pois estava aplicando a lógica errada)
            // 1. Calcula custo unitário (custo total do estoque dividido pela quantidade em estoque)
            // 2. Calcula custo total da venda (custo unitário × quantidade vendida)
            double custoUnitario = estoque.getValorEstoque() / estoque.getQtdEstoque();
            double custoTotalVenda = custoUnitario * v.getQtdItem();

            // Verifica se já existe registro para esta categoria no ranking
            MargemLucroCategoria existente = null;
            for (MargemLucroCategoria m : rank) {
                if (m.getCategoria().equals(categoria)) {
                    existente = m;
                    break;
                }
            }

            // Se a categoria já existe no ranking...
            if (existente != null) {
                // Acumula custos e vendas para esta categoria
                existente.addCusto(custoTotalVenda);
                existente.addVenda(v.getValorItem(), v.getQtdItem());
            }
            // Se é uma categoria nova...
            else {
                // Cria novo registro para a categoria
                MargemLucroCategoria novo = new MargemLucroCategoria(categoria);
                novo.addCusto(custoTotalVenda);
                novo.addVenda(v.getValorItem(), v.getQtdItem());
                rank.add(novo);
            }
        }

        // Dps processar todas as vendas, calcula o lucro para cada categoria
        for (MargemLucroCategoria m : rank) {
            m.calcularLucro();
        }

        // Atualiza o ranking principal
        this.rankingLucro = rank;
    }

    // Retorna o ranking ordenado por lucro total (do maior para o menor)
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
