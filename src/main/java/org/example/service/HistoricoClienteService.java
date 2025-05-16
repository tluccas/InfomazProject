package org.example.service;

import org.example.dao.ClienteDAO;
import org.example.dao.ProdutoDAO;
import org.example.dao.VendaDAO;
import org.example.model.Cliente;
import org.example.model.HistoricoCliente;
import org.example.model.Produto;
import org.example.model.Venda;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoricoClienteService {

    private ClienteDAO clienteDAO;
    private VendaDAO vendaDAO;
    private ProdutoDAO produtoDAO;
    private List<HistoricoCliente> historicoClientes;

    public HistoricoClienteService(ClienteDAO clienteDAO, VendaDAO vendaDAO, ProdutoDAO produtoDAO) {
        this.clienteDAO = clienteDAO;
        this.vendaDAO = vendaDAO;
        this.produtoDAO = produtoDAO;
        this.historicoClientes = new ArrayList<HistoricoCliente>();
    }

    public void gerarHistorico() throws SQLException {
        List<Venda> vendas = vendaDAO.listarVenda();
        List<HistoricoCliente> lista = new ArrayList<>();

        for (Venda v : vendas) {
            int idProduto = v.getIdProduto();
            int idCliente = v.getIdCliente();
            String nomeCliente = clienteDAO.buscarCliente(idCliente).getNomeCliente();
            String produto = produtoDAO.buscarProduto(idProduto).getNomeProduto();

            HistoricoCliente existente = null;
            for (HistoricoCliente h : lista) {
                if(h.getIdCliente() == idCliente){
                    existente = h;
                    break;
                }
            }
            if(existente != null){
                existente.addCompra(produto);
            }else {
                HistoricoCliente novo = new HistoricoCliente(idCliente, nomeCliente);
                novo.addCompra(produto);
                lista.add(novo);
            }
        }

        this.historicoClientes = lista; //Atualiza a lista principal

    }

    public List<HistoricoCliente> getHistoricoClientes() {
        return historicoClientes;
    }
}
