package org.example.service;

import org.example.dao.ProdutoDAO;
import org.example.dao.VendaDAO;
import org.example.model.MediaValorProd;
import org.example.model.Produto;
import org.example.model.Venda;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MediaValorService {

    private ProdutoDAO produtoDAO;
    private VendaDAO vendaDAO;
    private List<MediaValorProd> mediaVendas;

    public MediaValorService(ProdutoDAO produtoDAO, VendaDAO vendaDAO) {
        this.produtoDAO = produtoDAO;
        this.vendaDAO = vendaDAO;
        this.mediaVendas = new ArrayList<MediaValorProd>();
    }

    public void calculaMediaVendas() throws SQLException {
        List<Produto> produtos = produtoDAO.listarProduto();
        List<Venda> vendas = vendaDAO.listarVenda();
        List<MediaValorProd> lista = new ArrayList<>();

        for (Venda v : vendas) {
            LocalDate data = v.getDataNota().toLocalDate();
            int mes = data.getMonthValue();
            int ano = data.getYear();
            int idPrd = v.getIdProduto();
            String categoria = produtoDAO.buscarProduto(idPrd).getCategoria();
            double valor = v.getValorItem();
            int qnt = v.getQtdItem();

            MediaValorProd existente = null;
            for (MediaValorProd m : lista) {
                if (m.getCategoria().equals(categoria) && mes == m.getMes() && ano == m.getAno()) {
                    existente = m;
                    break;
                }
            }

            if (existente != null) {
                existente.addVenda(valor, qnt);
            } else {
                MediaValorProd novo = new MediaValorProd(categoria, mes, ano);
                novo.addVenda(valor, qnt); // Adiciona a venda inicial
                lista.add(novo);
            }
        }

        // Calcula a média final para todos os produtos apos acumular todas as vendas
        for (MediaValorProd m : lista) {
            m.calcularMedia();
        }

        this.mediaVendas = lista;
    }

    public List<MediaValorProd> exibirMediaVendas() {;
        return mediaVendas;
    }
}
