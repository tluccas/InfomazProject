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

    public void CalcularRanking() throws SQLException {
        List<Venda> vendas = vendaDAO.listarVenda();
        List<RankingCliente> ranking = new ArrayList<RankingCliente>();

        for (Venda venda : vendas) {
            LocalDate data = venda.getDataNota().toLocalDate();
            int mes = data.getMonthValue();
            int ano = data.getYear(); //Converto as datas para local date para fazer a comparaçao
            int idCliente = venda.getIdCliente();
            String nome = clienteDAO.buscarCliente(idCliente).getNomeCliente();
            RankingCliente existente = null;
            for (RankingCliente r : rankingClientes) {
                if (venda.getIdCliente() == r.getIdCliente() && mes == r.getMes() && ano == r.getAno()) {
                    existente = r;
                    break;
                }
            }
            if (existente != null) {
                existente.adicionarQuantidade(venda.getQtdItem());
            } else {
                ranking.add(new RankingCliente(idCliente, nome, mes, ano, venda.getQtdItem()));
            }
        }

        this.rankingClientes = ranking; //Atualiza a lista principal
    }


    @Override
    public List<RankingCliente> getRanking() {
        rankingClientes.sort(Comparator.comparingInt(RankingCliente::getTotalComprado).reversed()); //utilizando sort para ordenar  os obj da lista
        return rankingClientes;
    }

    public void exibirRanking() {
        List<RankingCliente> ranking = getRanking();
        int posicao = 1;

        for (RankingCliente r : ranking) {
            System.out.println(posicao + "º - " + r); //Printa a ordem de cada um (estética)
            posicao++;
        }
    }
}
