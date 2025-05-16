package org.example.service;

import org.example.dao.EstoqueDAO;
import org.example.dao.ProdutoDAO;
import org.example.model.Estoque;
import org.example.model.Produto;
import org.example.model.RkEstoque;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RkEstoqueService {
    private ProdutoDAO produtoDAO;
    private EstoqueDAO estoqueDAO;
    private List<RkEstoque> rankingEstoque;

    public RkEstoqueService(ProdutoDAO produtoDAO, EstoqueDAO estoqueDAO) {
        this.produtoDAO = produtoDAO;
        this.estoqueDAO = estoqueDAO;
        this.rankingEstoque = new ArrayList<>();
    }

    public void gerarRankingEstoque() throws SQLException {
        List<Produto> produtos = produtoDAO.listarProduto();
        List<Estoque> estoque = estoqueDAO.listarEstoque();
        List<RkEstoque> lista = new ArrayList<>();

        for (Estoque est : estoque) {
            int idEstoque = est.getIdEstoque();
            Produto prodExistente = null;
            for (Produto produto : produtos) {
                if (produto.getIdEstoque() == idEstoque) { //Verifica se o produto na tabela CADASTRO_ESTOQUE está com o mesmo registro (Id de estoque) em CADASTRO_PRODUTOS
                    prodExistente = produto;
                    break;
                }
            }
            if (prodExistente == null) {
                System.out.println("Produto de id: " + idEstoque + "inexistente");
            }
            String nomeProduto = prodExistente.getNomeProduto();

            int quantidade = est.getQtdEstoque();

            RkEstoque existente = null;
            for (RkEstoque rkestoque : lista) {
                if (rkestoque.getIdEstoque() == idEstoque) {
                    existente = rkestoque;
                }
            }
            if (existente != null) {
                existente.setQuantidade(quantidade);
            } else {
                RkEstoque novo = new RkEstoque(idEstoque, nomeProduto);
                novo.setQuantidade(quantidade);
                lista.add(novo);
            }
        }
        this.rankingEstoque = lista; // Atualiza a lista principal
    }

    public List<RkEstoque> getRankingEstoque() {
        rankingEstoque.sort(Comparator.comparingInt(RkEstoque::getQuantidade).reversed());
        return rankingEstoque;
    }

    public void exibirRankingEstoque() {
        List<RkEstoque> lista = getRankingEstoque();
        int posicao = 1;

        for (RkEstoque r : lista) { // Apenas estético novamente
            System.out.println(posicao + "º - " + r);
            posicao++;
        }
    }
}
