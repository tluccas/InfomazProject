package org.example.service;

import org.example.dao.EstoqueDAO;
import org.example.dao.FornecedorDAO;
import org.example.model.Estoque;
import org.example.model.Fornecedor;
import org.example.model.RankingFornecedores;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RankingFornecedorService implements RankingService{

    private EstoqueDAO estoqueDAO;
    private FornecedorDAO fornecedorDAO;
    private List<RankingFornecedores> rankingFornecedores;

    public RankingFornecedorService(EstoqueDAO estoqueDAO, FornecedorDAO fornecedorDAO) {
        this.estoqueDAO = estoqueDAO;
        this.fornecedorDAO = fornecedorDAO;
        this.rankingFornecedores = new ArrayList<>();
    }

    //Método do DESAFIO 4
    @Override
    public void CalcularRanking() throws SQLException {
        // Obtém todos os registros de estoque do banco de dados
        List<Estoque> estoque = estoqueDAO.listarEstoque();
        // Lista temporária para armazenar o ranking durante o cálculo
        List<RankingFornecedores> ranking = new ArrayList<>();

        // Para cada item no estoque...
        for (Estoque est : estoque) {
            // Extrai informações de data (mês e ano) do registro de estoque
            LocalDate data = est.getDataEstoque().toLocalDate();
            int mes = data.getMonthValue();
            int ano = data.getYear();

            // Obtém informações do fornecedor
            String id = est.getIdFornecedor();
            String nome = fornecedorDAO.buscarFornecedor(id).getNomeFornecedor();

            // Verifica se já existe um registro para este fornecedor no mesmo mês/ano
            RankingFornecedores existente = null;
            for(RankingFornecedores r : ranking) {
                if(est.getIdFornecedor().equals(r.getIdFornecedor())
                        && mes == r.getMes()
                        && ano == r.getAno()) {
                    existente = r;
                    break;  // Fornecedor encontrado no ranking
                }
            }

            // Se o fornecedor já está no ranking para este período...
            if(existente != null) {
                // Atualiza a quantidade em estoque (acumula)
                existente.setEstoque(est.getQtdEstoque());
            } else {
                // Se não existe, cria um novo registro no ranking
                ranking.add(new RankingFornecedores(id, nome, mes, ano, est.getQtdEstoque()));
            }
        }

        // Atualiza o ranking principal com os dados calculados
        this.rankingFornecedores = ranking;
    }

    @Override
    public List<RankingFornecedores> getRanking() {
        rankingFornecedores.sort(
                Comparator.comparingInt(RankingFornecedores::getAno)
                        .thenComparingInt(RankingFornecedores::getMes)
                        .thenComparing(Comparator.comparingInt(RankingFornecedores::getEstoque).reversed())
        );;
        return rankingFornecedores;
    }

    public void exibirRanking() {
        List<RankingFornecedores> ranking = getRanking();

        System.out.println("====================================================================================");
        System.out.printf("%-5s | %-25s | %-10s | %-10s | %-10s\n",
                "Pos", "Fornecedor", "Mês", "Ano", "Estoque");
        System.out.println("------------------------------------------------------------------------------------");

        int posicao = 1;
        for (RankingFornecedores r : ranking) {
            System.out.printf("%-5d | %-25s | %-10d | %-10d | %-10d\n",
                    posicao,
                    r.getNomeFornecedor(),
                    r.getAno(),
                    r.getMes(),
                    r.getEstoque());
            posicao++;
        }

        System.out.println("====================================================================================");
    }

}
