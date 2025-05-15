package org.example.service;

import org.example.dao.EstoqueDAO;
import org.example.dao.FornecedorDAO;
import org.example.model.Estoque;
import org.example.model.Fornecedor;
import org.example.model.RankingCliente;
import org.example.model.RankingFornecedores;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RankingFornecedor implements RankingService{

    private EstoqueDAO estoqueDAO;
    private FornecedorDAO fornecedorDAO;
    private List<RankingFornecedores> rankingFornecedores;

    public RankingFornecedor(EstoqueDAO estoqueDAO, FornecedorDAO fornecedorDAO, List<RankingFornecedores> rankingFornecedores) {
        this.estoqueDAO = estoqueDAO;
        this.fornecedorDAO = fornecedorDAO;
        this.rankingFornecedores = rankingFornecedores;
    }

    @Override
    public void CalcularRanking() throws SQLException {
        List<Estoque> estoque = estoqueDAO.listarEstoque();
        List<Fornecedor> fornecedor = fornecedorDAO.listarFornecedor();
        List<RankingFornecedores> ranking = new ArrayList<>();

        for (Estoque est : estoque) {
            LocalDate data = est.getDataEstoque().toLocalDate();
            int mes = data.getMonthValue();
            int ano = data.getYear();
            String id = est.getIdFornecedor();
            String nome = fornecedorDAO.buscarFornecedor(id).getNomeFornecedor();

            //verificando se existe fornecedor no mes/ano
            RankingFornecedores existente = null;
            for(RankingFornecedores r : ranking) {
                if(est.getIdFornecedor().equals(r.getIdFornecedor()) && mes == r.getMes() && ano == r.getAno()) {
                    existente = r;
                    break;
                }
            }
            if(existente != null) {
                existente.setEstoque(est.getQtdEstoque()); //Se encontrar na lista incrementa pontos
            }else{ // Se nao cria um novo
                ranking.add(new RankingFornecedores(id, nome, mes, ano, est.getQtdEstoque()));
            }

        }
        this.rankingFornecedores = ranking;
    }

    @Override
    public List<RankingFornecedores> getRanking() {
        rankingFornecedores.sort(Comparator.comparingInt(RankingFornecedores::getEstoque).reversed());
        return rankingFornecedores;
    }

    public void exibirRanking() throws SQLException {
        List<RankingFornecedores> ranking = getRanking();
        int posicao = 1;

        for (RankingFornecedores r : ranking) {
            System.out.println(posicao + "º - " + r);
            posicao++;
        }
    }
}
