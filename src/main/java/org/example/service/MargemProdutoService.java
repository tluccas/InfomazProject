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

    public void gerarMargem() throws SQLException {
        List<Venda> vendas = vendaDAO.listarVenda();
        List<Estoque> estoque = estoqueDAO.listarEstoque();
        List<Produto> produtos = produtoDAO.listarProduto();
        List<ProdutoMargem> margem = new ArrayList<ProdutoMargem>();

        for (Venda venda : vendas) {
            Produto produto = null;
            for (Produto p : produtos) {
                if (p.getIdProduto() == venda.getIdProduto()) {
                    produto = p;
                }
            }

            if (produto != null) {
                for (Estoque est : estoque) {
                    if (produto.getIdEstoque() == est.getIdEstoque()) {
                        int id = produto.getIdProduto();
                        String nome = produto.getNomeProduto();
                        double margemUnitaria = venda.getValorItem() - est.getValorEstoque();  // valor de venda por unidade
                        double calculo = margemUnitaria * venda.getQtdItem();  // calcula a margem total da venda (por produto)

                        ProdutoMargem existente = null;
                        for (ProdutoMargem m : margem) {
                            if (m.getIdProduto() == id) {
                                existente = m;
                                break;
                            }
                        }

                        if (existente != null) {
                            existente.calcMargem(calculo);
                        } else {
                            margem.add(new ProdutoMargem(id, nome, calculo));
                        }
                    }
                }
            }
        }

        this.margemProdutos = margem;
    }

    public List<ProdutoMargem> getMargemProdutos() {
        return margemProdutos;
    }
}
