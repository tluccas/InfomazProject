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
    // DAOs para acessar os dados de produto e estoque
    private ProdutoDAO produtoDAO;
    private EstoqueDAO estoqueDAO;
    // Lista que armazena o ranking de estoque
    private List<RkEstoque> rankingEstoque;

    // Construtor que recebe os DAOs necessários
    public RkEstoqueService(ProdutoDAO produtoDAO, EstoqueDAO estoqueDAO) {
        this.produtoDAO = produtoDAO;
        this.estoqueDAO = estoqueDAO;
        this.rankingEstoque = new ArrayList<>();
    }

    // Método do DESAFIO 10
    public void gerarRankingEstoque() throws SQLException {
        // Pega todos os produtos e estoques do banco de dados
        List<Produto> produtos = produtoDAO.listarProduto();
        List<Estoque> estoque = estoqueDAO.listarEstoque();
        List<RkEstoque> lista = new ArrayList<>();

        // Para cada item no estoque...
        for (Estoque est : estoque) {
            int idEstoque = est.getIdEstoque();
            Produto prodExistente = null;

            // Procura o produto correspondente no cadastro de produtos
            for (Produto produto : produtos) {
                if (produto.getIdEstoque() == idEstoque) {
                    // Se encontrar, armazena o produto
                    prodExistente = produto;
                    break;
                }
            }

            // Se não encontrar o produto, mostra mensagem de erro
            if (prodExistente == null) {
                System.out.println("Produto de id: " + idEstoque + "inexistente");
            }
            String nomeProduto = prodExistente.getNomeProduto();

            int quantidade = est.getQtdEstoque();

            // Verifica se já existe um ranking para este produto
            RkEstoque existente = null;
            for (RkEstoque rkestoque : lista) {
                if (rkestoque.getIdEstoque() == idEstoque) {
                    existente = rkestoque;
                }
            }

            // Se já existe, atualiza a quantidade
            // Se não existe, cria um novo item no ranking
            if (existente != null) {
                existente.setQuantidade(quantidade);
            } else {
                RkEstoque novo = new RkEstoque(idEstoque, nomeProduto);
                novo.setQuantidade(quantidade);
                lista.add(novo);
            }
        }
        this.rankingEstoque = lista; // Atualiza a lista principal com os novos dados
    }

    // Retorna o ranking ordenado por quantidade (do maior para o menor)
    public List<RkEstoque> getRankingEstoque() {
        rankingEstoque.sort(Comparator.comparingInt(RkEstoque::getQuantidade).reversed());
        return rankingEstoque;
    }

    // Método para exibir o ranking de forma bonitinha no console
    public void exibirRankingEstoque() {
        List<RkEstoque> lista = getRankingEstoque();
        int posicao = 1;

        System.out.println("=======================================");
        System.out.printf("%-5s | %-25s | %-10s\n", "Pos", "Produto", "Quantidade");
        System.out.println("---------------------------------------");

        for (RkEstoque r : lista) {
            System.out.printf("%-5d | %-25s | %-10d\n",
                    posicao,
                    r.getNomeProduto(),
                    r.getQuantidade());
            posicao++;
        }

        System.out.println("=======================================");
    }

}