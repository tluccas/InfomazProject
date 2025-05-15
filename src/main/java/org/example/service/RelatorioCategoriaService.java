package org.example.service;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.example.dao.*;
import org.example.model.*;
import org.example.model.RelatorioCategoria;

public class RelatorioCategoriaService {

    private VendaDAO vendaDAO;
    private ProdutoDAO produtoDAO;
    private List<RelatorioCategoria> relatorios;


    public RelatorioCategoriaService(VendaDAO vendaDAO, ProdutoDAO produtoDAO) {
        this.vendaDAO = vendaDAO;
        this.produtoDAO = produtoDAO;
        this.relatorios = new ArrayList<>();
    }


    public void gerarRelatorio() throws SQLException {
        List<Venda> vendas = vendaDAO.listarVenda();
        List<Produto> produtos = produtoDAO.listarProduto();
        List<RelatorioCategoria> relatorio = new ArrayList<RelatorioCategoria>();
        for (Venda venda : vendas){
            Produto p = null;
            for (Produto produto : produtos){
                if(produto.getIdProduto() == venda.getIdProduto()){ //Verifica se o produto esta na tabela de venda
                    p = produto;
                    break;
                }
            }

            if (p != null){

                String categoria = p.getCategoria(); //Pega a categoria do produto
                double totalVenda = venda.getValorItem() * venda.getQtdItem(); //Calcula o valor total da venda de acordo com a qtd na tabela

                RelatorioCategoria existente = null;
                for (RelatorioCategoria r : relatorio){
                    if(r.getCategoria().equals(categoria)){
                        existente = r;
                        break;
                    }
                }

                if (existente != null){
                    existente.addVenda(totalVenda);
                }else {
                    relatorio.add(new RelatorioCategoria(categoria, totalVenda));
                }
            }
        }

        this.relatorios = relatorio; //salva na lista principal

    }
    public List<RelatorioCategoria> getRelatorios() {
        return relatorios;
    }
}
