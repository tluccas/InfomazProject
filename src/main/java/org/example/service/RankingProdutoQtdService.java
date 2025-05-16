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
    //Método do DESAFIO 5
    @Override
    public void CalcularRanking() throws SQLException {
        // Obtém todas as vendas registradas no sistema
        List<Venda> vendas = vendaDAO.listarVenda();
        // Lista temporária para armazenar o ranking durante o cálculo
        List<RankingProdutoQtd> ranking = new ArrayList<>();

        // Para cada venda no sistema...
        for (Venda venda : vendas) {
            // Extrai informações de data (mês e ano) da venda
            LocalDate data = venda.getDataNota().toLocalDate();
            int mes = data.getMonthValue();
            int ano = data.getYear();

            // Obtém informações do produto vendido
            int idPrd = venda.getIdProduto();
            String nome = produtoDAO.buscarProduto(idPrd).getNomeProduto();

            // Verifica se já existe um registro para este produto no mesmo período
            RankingProdutoQtd existente = null;
            for (RankingProdutoQtd p : ranking) {
                if (p.getIdProduto() == venda.getIdProduto()
                        && p.getMes() == mes
                        && p.getAno() == ano) {
                    existente = p;
                    break;  // Produto encontrado no ranking
                }
            }

            // Se o produto já está no ranking para este período...
            if (existente != null) {
                // Atualiza a quantidade total vendida (acumula)
                existente.setTotalVendido(venda.getQtdItem());
            } else {
                // Se não existe, cria um novo registro no ranking
                ranking.add(new RankingProdutoQtd(nome, idPrd, mes, ano, venda.getQtdItem()));
            }
        }

        // Atualiza o ranking principal com os dados calculados
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
