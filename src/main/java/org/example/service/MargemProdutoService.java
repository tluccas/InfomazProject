package org.example.service;

import org.example.model.Estoque;
import org.example.model.Produto;
import org.example.dao.*;
import org.example.model.ProdutoMargem;
import org.example.model.Venda;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MargemProdutoService {

    private VendaDAO vendaDAO;
    private EstoqueDAO estoqueDAO;
    private ProdutoDAO produtoDAO;
    private List<ProdutoMargem> margemProdutos;

    public MargemProdutoService(VendaDAO vendaDAO, EstoqueDAO estoqueDAO, ProdutoDAO produtoDAO) {
        this.vendaDAO = vendaDAO;
        this.estoqueDAO = estoqueDAO;
        this.produtoDAO = produtoDAO;
        this.margemProdutos = new ArrayList<ProdutoMargem>();
    }

    // Método do DESAFIO 2
    public void gerarMargem() throws SQLException {
        // Obtém todas as vendas, estoques e produtos do banco
        List<Venda> vendas = vendaDAO.listarVenda();
        List<Estoque> estoque = estoqueDAO.listarEstoque();
        List<Produto> produtos = produtoDAO.listarProduto();

        // Lista temporária para armazenar as margens calculadas
        List<ProdutoMargem> margem = new ArrayList<ProdutoMargem>();

        // Para cada venda registrada...
        for (Venda venda : vendas) {
            Produto produto = null;

            // Procura o produto correspondente na lista de produtos
            for (Produto p : produtos) {
                if (p.getIdProduto() == venda.getIdProduto()) {
                    produto = p;
                    break;  // Produto encontrado, pode parar de procurar
                }
            }

            // Se encontrou o produto...
            if (produto != null) {
                // Procura o estoque correspondente a este produto
                for (Estoque est : estoque) {
                    if (produto.getIdEstoque() == est.getIdEstoque()) {
                        int id = produto.getIdProduto();
                        String nome = produto.getNomeProduto();

                        // Calcula a margem unitária (preço venda - custo estoque)
                        double margemUnitaria = venda.getValorItem() - est.getValorEstoque();

                        // Calcula a margem total para esta venda (margem unitária × quantidade vendida)
                        double calculo = margemUnitaria * venda.getQtdItem();

                        // Verifica se já existe um registro para este produto
                        ProdutoMargem existente = null;
                        for (ProdutoMargem m : margem) {
                            if (m.getIdProduto() == id) {
                                existente = m;
                                break;  // Produto encontrado no relatório
                            }
                        }

                        // Se o produto já está no relatório de margens...
                        if (existente != null) {
                            existente.calcMargem(calculo); // Acumula o valor da margem
                        } else {
                            // Se não existe, cria um novo registro
                            margem.add(new ProdutoMargem(id, nome, calculo));
                        }
                    }
                }
            }
        }

        // Atualiza a lista principal com as margens calculadas
        this.margemProdutos = margem;
    }

    public List<ProdutoMargem> getMargemProdutos() {
        return margemProdutos;
    }

    public void exibirMargemProdutos() {
        List<ProdutoMargem> margemProdutos = getMargemProdutos();

        // Exibição da tabela
        System.out.println("==========================================================");
        System.out.printf("%-5s | %-25s | %-15s\n",
                "Pos", "Produto", "Margem Produto");
        System.out.println("----------------------------------------------------------");

        int posicao = 1;
        for (ProdutoMargem m : margemProdutos) {
            System.out.printf("%-5d | %-25s | %-15s\n",
                    posicao,
                    m.getNomeProduto(),
                    m.getMargem());
            posicao++;
        }

        System.out.println("==========================================================");
    }
}
