package org.example.service;

import org.example.dao.ProdutoDAO;
import org.example.dao.VendaDAO;
import org.example.model.Venda;
import org.example.model.RankingProdutoQtd;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RankingProdutoQtdService implements RankingService{
    private ProdutoDAO produtoDAO;
    private VendaDAO vendaDAO;
    private List<RankingProdutoQtd> rankingProdutos;

    public RankingProdutoQtdService(VendaDAO vendaDAO, ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
        this.vendaDAO = vendaDAO;
        this.rankingProdutos = new ArrayList<RankingProdutoQtd>();
    }

    @Override
    public void CalcularRanking() throws SQLException {
        List<Venda> vendas = vendaDAO.listarVenda();
        List<RankingProdutoQtd> ranking = new ArrayList<>();

        for (Venda venda : vendas) {

            LocalDate data = venda.getDataNota().toLocalDate();
            int mes = data.getMonthValue();
            int ano = data.getYear();
            int idPrd = venda.getIdProduto();
            String nome = produtoDAO.buscarProduto(idPrd).getNomeProduto();

            RankingProdutoQtd existente = null;
            for (RankingProdutoQtd p : ranking){
                if(p.getIdProduto() == venda.getIdProduto() && p.getMes() == mes && p.getAno() == ano){
                    existente = p;
                    break;
                }
            }
            if(existente != null){
                existente.setTotalVendido(venda.getQtdItem());
            }else{
                ranking.add(new RankingProdutoQtd(nome, idPrd, mes, ano, venda.getQtdItem()));
            }
        }
        this.rankingProdutos = ranking;
    }

    @Override
    public List<RankingProdutoQtd> getRanking() {
        rankingProdutos.sort(Comparator.comparingInt(RankingProdutoQtd::getTotalVendido).reversed());
        return rankingProdutos;
    }

    @Override
    public void exibirRanking() {
        List<RankingProdutoQtd> ranking = getRanking();
        int posicao = 1;

        for (RankingProdutoQtd r : ranking) {
            System.out.println(posicao + "º - " + r);
            posicao++;
        }
    }
}
