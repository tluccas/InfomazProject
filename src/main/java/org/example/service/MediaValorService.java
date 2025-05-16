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

    // Método do DESAFIO 7
    public void calculaMediaVendas() throws SQLException {
        // Obtém todas as vendas registradas no sistema
        List<Venda> vendas = vendaDAO.listarVenda();
        // Lista temporária para armazenar os dados durante o processamento
        List<MediaValorProd> lista = new ArrayList<>();

        // Para cada venda no sistema...
        for (Venda v : vendas) {
            // Extrai informações de data (mês e ano) da venda
            LocalDate data = v.getDataNota().toLocalDate();
            int mes = data.getMonthValue();
            int ano = data.getYear();

            // Obtém informações do produto vendido
            int idPrd = v.getIdProduto();
            String categoria = produtoDAO.buscarProduto(idPrd).getCategoria();
            double valor = v.getValorItem();  // Valor unitário do produto
            int qnt = v.getQtdItem();       // Quantidade vendida

            // Verifica se já existe registro para esta categoria no mesmo período
            MediaValorProd existente = null;
            for (MediaValorProd m : lista) {
                if (m.getCategoria().equals(categoria)
                        && mes == m.getMes()
                        && ano == m.getAno()) {
                    existente = m;
                    break;  // Categoria/periodo encontrado
                }
            }

            // Se já existe registro para esta categoria/periodo...
            if (existente != null) {
                // Acumula valores e quantidades para cálculo posterior
                existente.addVenda(valor, qnt);
            } else {
                // Se não existe, cria novo registro
                MediaValorProd novo = new MediaValorProd(categoria, mes, ano);
                novo.addVenda(valor, qnt);  // Adiciona os dados da primeira venda
                lista.add(novo);
            }
        }

        // Dps de processar todas as vendas, calcula as médias finais
        for (MediaValorProd m : lista) {
            m.calcularMedia();
        }

        // Atualiza a lista principal com os dados calculados
        this.mediaVendas = lista;
    }

    public List<MediaValorProd> exibirMediaVendas() {;
        return mediaVendas;
    }
}
