package org.example.controller;

import org.example.dao.ClienteDAO;
import org.example.dao.ProdutoDAO;
import org.example.dao.VendaDAO;
import org.example.service.HistoricoClienteService;
import org.example.service.RankingServiceCliente;

import java.sql.SQLException;

public class ClienteController {
    private final ClienteDAO clienteDAO;
    private final VendaDAO vendaDAO;
    private final ProdutoDAO produtoDAO;

    public ClienteController(ClienteDAO clienteDAO, VendaDAO vendaDAO, ProdutoDAO produtoDAO) {
        this.clienteDAO = clienteDAO;
        this.vendaDAO = vendaDAO;
        this.produtoDAO = produtoDAO;
    }

    public void rankingClientes() throws SQLException {
        System.out.println("\n---Ranking Clientes---\n");
        RankingServiceCliente service = new RankingServiceCliente(vendaDAO, clienteDAO);
        service.CalcularRanking();
        service.exibirRanking();
    }

    public void historicoCompras() throws SQLException {
        System.out.println("\n---Histórico de compras por cliente---\n");
        HistoricoClienteService service = new HistoricoClienteService(clienteDAO, vendaDAO, produtoDAO);
        service.gerarHistorico();
        service.exibirHistoricoClientes();
    }
}
