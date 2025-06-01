package org.example.service;

import org.example.dao.ClienteDAO;
import org.example.dao.ProdutoDAO;
import org.example.dao.VendaDAO;
import org.example.model.HistoricoCliente;
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
    //Método do DESAFIO 9
    public void gerarHistorico() throws SQLException {
        // Obtém todas as vendas registradas no sistema
        List<Venda> vendas = vendaDAO.listarVenda();
        // Lista temporária para armazenar o histórico durante o processamento
        List<HistoricoCliente> lista = new ArrayList<>();

        // Para cada venda no sistema...
        for (Venda v : vendas) {
            // Obtém informações do produto e cliente
            int idProduto = v.getIdProduto();
            int idCliente = v.getIdCliente();
            String nomeCliente = clienteDAO.buscarCliente(idCliente).getNomeCliente();
            String produto = produtoDAO.buscarProduto(idProduto).getNomeProduto();

            // Verifica se o cliente já está no histórico
            HistoricoCliente existente = null;
            for (HistoricoCliente h : lista) {
                if(h.getIdCliente() == idCliente) {
                    existente = h;
                    break;  // Cliente encontrado no histórico
                }
            }

            // Se o cliente já tem histórico...
            if(existente != null) {
                // Adiciona o produto à lista de compras do cliente
                existente.addCompra(produto);
            } else {
                // Se não tem histórico, cria um novo registro
                HistoricoCliente novo = new HistoricoCliente(idCliente, nomeCliente);
                novo.addCompra(produto);
                lista.add(novo);
            }
        }

        // Atualiza o histórico principal com os dados processados
        this.historicoClientes = lista;
    }

    public List<HistoricoCliente> getHistorico() {
        return historicoClientes;
    }
    public void exibirHistoricoClientes() {
        List<HistoricoCliente> lista = getHistorico();

        // Exibição da tabela
        System.out.println("=========================================================");
        System.out.printf("%-5s | %-25s | %-15s\n",
                "Pos", "Nome", "Itens Comprados");
        System.out.println("---------------------------------------------------------");

        int posicao = 1;
        for (HistoricoCliente h : lista) {
            System.out.printf("%-5d | %-25s | %-15s\n",
                    posicao,
                    h.getNomeCliente(),
                    h.getComprados());
            posicao++;
        }

        System.out.println("=========================================================");
    }

}
