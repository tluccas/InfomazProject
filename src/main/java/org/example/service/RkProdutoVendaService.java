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

    //Método do DESAFIO 6
    @Override
    public void CalcularRanking() throws SQLException {
        // Pega todas as vendas do banco de dados
        List<Venda> vendas = vendaDAO.listarVenda();
        // Lista temporária para armazenar o ranking enquanto é calculado
        List<RkProdutoVenda> ranking = new ArrayList<>();

        // Para cada venda registrada...
        for (Venda venda : vendas) {
            // Extrai a data da venda e separa mês e ano
            LocalDate data = venda.getDataNota().toLocalDate();
            int mes = data.getMonthValue();
            int ano = data.getYear();

            // Pega o ID e nome do produto vendido
            int idPrd = venda.getIdProduto();
            String nome = produtoDAO.buscarProduto(idPrd).getNomeProduto();

            // Verifica se já existe um registro deste produto para este mês/ano no ranking
            RkProdutoVenda existente = null;
            for (RkProdutoVenda p : ranking) {
                if (p.getIdProduto() == venda.getIdProduto() && p.getMes() == mes && p.getAno() == ano) {
                    existente = p;
                    break;  // Se encontrou, para de procurar
                }
            }

            // Se já existe um registro para este produto no mês/ano...
            if (existente != null) {
                // Atualiza o valor vendido (soma ao valor existente)
                existente.setValorVendido(venda.getValorItem() * venda.getQtdItem());
            } else {
                // Se não existe, cria um novo registro no ranking
                double totalvenda = venda.getValorItem() * venda.getQtdItem();
                ranking.add(new RkProdutoVenda(nome, idPrd, mes, ano, totalvenda));
            }
        }
        // Atualiza o ranking da classe com os dados calculados
        this.rankingProdutos = ranking;
    }

    // Retorna o ranking ordenado por valor vendido (do maior para o menor)
    @Override
    public List<RkProdutoVenda> getRanking() {
        // Ordena a lista pelo valor vendido em ordem decrescente
        rankingProdutos.sort(
                Comparator.comparingInt(RkProdutoVenda::getAno).reversed()
                        .thenComparingInt(RkProdutoVenda::getMes).reversed()
                        .thenComparing(Comparator.comparingDouble(RkProdutoVenda::getValorVendido).reversed())
        );

        return rankingProdutos;
    }

    // Exibe o ranking formatado no console
    @Override
    public void exibirRanking() {
        List<RkProdutoVenda> ranking = getRanking();
        int posicao = 1;

        System.out.println("=============================================================================");
        System.out.printf("%-5s | %-25s | %-10s | %-10s | %-15s\n",
                "Pos", "Produto", "Mês", "Ano", "Valor Vendido");
        System.out.println("-----------------------------------------------------------------------------");

        for (RkProdutoVenda r : ranking) {
            System.out.printf("%-5d | %-25s | %-10s | %-10d | %-15.2f\n",
                    posicao,
                    r.getNome(),
                    r.getMes(),
                    r.getAno(),
                    r.getValorVendido());
            posicao++;
        }

        System.out.println("=============================================================================");
    }


}
