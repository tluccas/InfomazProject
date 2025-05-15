package org.example.service;

import org.example.dao.ProdutoDAO;
import org.example.dao.VendaDAO;
import org.example.model.RkProdutoVenda;
import org.example.model.Venda;
import org.example.model.RankingProdutoQtd;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RkProdutoVendaService implements RankingService{
    private ProdutoDAO produtoDAO;
    private VendaDAO vendaDAO;
    private List<RkProdutoVenda> rankingProdutos;

    public RkProdutoVendaService(VendaDAO vendaDAO, ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
        this.vendaDAO = vendaDAO;
        this.rankingProdutos = new ArrayList<RkProdutoVenda>();
    }

    @Override
    public void CalcularRanking() throws SQLException {
        List<Venda> vendas = vendaDAO.listarVenda();
        List<RkProdutoVenda> ranking = new ArrayList<>();

        for (Venda venda : vendas) {

            LocalDate data = venda.getDataNota().toLocalDate();
            int mes = data.getMonthValue();
            int ano = data.getYear();
            int idPrd = venda.getIdProduto();
            String nome = produtoDAO.buscarProduto(idPrd).getNomeProduto();

            RkProdutoVenda existente = null;
            for (RkProdutoVenda p : ranking){
                if(p.getIdProduto() == venda.getIdProduto() && p.getMes() == mes && p.getAno() == ano){
                    existente = p;
                    break;
                }
            }
            if(existente != null){
                existente.setValorVendido(venda.getQtdItem() * venda.getQtdItem());
            }else{
                double totalvenda = venda.getValorItem() * venda.getQtdItem();
                ranking.add(new RkProdutoVenda(nome, idPrd, mes, ano, totalvenda));
            }
        }
        this.rankingProdutos = ranking;
    }

    @Override
    public List<RkProdutoVenda> getRanking() {
        rankingProdutos.sort(Comparator.comparingDouble(RkProdutoVenda::getValorVendido).reversed());
        return rankingProdutos;
    }

    @Override
    public void exibirRanking() {
        List<RkProdutoVenda> ranking = getRanking();
        int posicao = 1;

        for (RkProdutoVenda r : ranking) {
            System.out.println(posicao + "º - " + r);
            posicao++;
        }
    }
}
