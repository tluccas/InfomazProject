package org.example.service;

import org.example.dao.ClienteDAO;
import org.example.dao.VendaDAO;
import org.example.model.RankingCliente;
import org.example.model.Venda;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RankingServiceCliente implements RankingService {
    private ClienteDAO clienteDAO;
    private VendaDAO vendaDAO;
    private List<RankingCliente> rankingClientes;

    public RankingServiceCliente(VendaDAO vendaDAO, ClienteDAO clienteDAO) {
        this.vendaDAO = vendaDAO;
        this.clienteDAO = clienteDAO;
        this.rankingClientes = new ArrayList<RankingCliente>();
    }

    // Método do DESAFIO 3
    public void CalcularRanking() throws SQLException {
        // Obtém todas as vendas registradas no sistema
        List<Venda> vendas = vendaDAO.listarVenda();
        // Lista temporária para armazenar o ranking durante o cálculo
        List<RankingCliente> ranking = new ArrayList<RankingCliente>();

        // Para cada venda no sistema...
        for (Venda venda : vendas) {
            // Converte a data da nota fiscal para LocalDate
            LocalDate data = venda.getDataNota().toLocalDate();
            int mes = data.getMonthValue();  // Extrai o mês (1-12)
            int ano = data.getYear();       // Extrai o ano

            // Obtém informações do cliente
            int idCliente = venda.getIdCliente();
            String nome = clienteDAO.buscarCliente(idCliente).getNomeCliente();

            // Verifica se o cliente já está no ranking para o mesmo período
            RankingCliente existente = null;
            for (RankingCliente r : rankingClientes) {
                if (venda.getIdCliente() == r.getIdCliente()
                        && mes == r.getMes()
                        && ano == r.getAno()) {
                    existente = r;
                    break;  // Cliente encontrado no ranking
                }
            }

            // Se o cliente já está no ranking para este período...
            if (existente != null) {
                // Acumula a quantidade comprada
                existente.adicionarQuantidade(venda.getQtdItem());
            } else {
                // Se não existe, cria um novo registro no ranking
                ranking.add(new RankingCliente(idCliente, nome, mes, ano, venda.getQtdItem()));
            }
        }

        // Atualiza o ranking principal com os dados calculados
        this.rankingClientes = ranking;
    }


    @Override
    public List<RankingCliente> getRanking() {
        rankingClientes.sort(Comparator.comparingInt(RankingCliente::getTotalComprado).reversed()); //utilizando sort para ordenar  os obj da lista
        return rankingClientes;
    }

    public void exibirRanking() {
        List<RankingCliente> rankingC = getRanking();

        System.out.println("====================================================================================");
        System.out.printf("%-5s | %-25s | %-10s | %-10s | %-15s\n",
                "Pos", "Nome", "Mês", "Ano", "Compras");
        System.out.println("------------------------------------------------------------------------------------");

        int posicao = 1;
        for (RankingCliente c : rankingC) {
            System.out.printf("%-5d | %-25s | %-10d | %-10d | %-15d\n",
                    posicao,
                    c.getNomeCliente(),
                    c.getMes(),
                    c.getAno(),
                    c.getTotalComprado());
            posicao++;
        }

        System.out.println("====================================================================================");
    }



}

