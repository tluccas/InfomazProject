package org.example.service;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.example.dao.*;
import org.example.model.*;
import org.example.model.RelatorioCategoria;

public class RelatorioCategoriaService {

    private VendaDAO vendaDAO;
    private ProdutoDAO produtoDAO;
    private List<RelatorioCategoria> relatorios;


    public RelatorioCategoriaService(VendaDAO vendaDAO, ProdutoDAO produtoDAO) {
        this.vendaDAO = vendaDAO;
        this.produtoDAO = produtoDAO;
        this.relatorios = new ArrayList<>();
    }

    // Método para resolução do Desafio 1
    public void gerarRelatorio() throws SQLException {
        // Obtém todas as vendas e produtos do banco de dados
        List<Venda> vendas = vendaDAO.listarVenda();
        List<Produto> produtos = produtoDAO.listarProduto();

        // Lista temporária para armazenar o relatório enquanto é gerado
        List<RelatorioCategoria> relatorio = new ArrayList<RelatorioCategoria>();

        // Para cada venda no sistema...
        for (Venda venda : vendas) {
            Produto p = null;

            // Procura o produto correspondente na lista de produtos
            for (Produto produto : produtos) {
                if (produto.getIdProduto() == venda.getIdProduto()) {
                    p = produto;
                    break;  // Produto encontrado, pode parar de procurar
                }
            }

            // Se encontrou o produto...
            if (p != null) {
                String categoria = p.getCategoria(); // Pega a categoria do produto

                // Calcula o valor total da venda (preço unitário × quantidade)
                double totalVenda = venda.getValorItem() * venda.getQtdItem();

                // Verifica se já existe um registro para esta categoria no relatório
                RelatorioCategoria existente = null;
                for (RelatorioCategoria r : relatorio) {
                    if (r.getCategoria().equals(categoria)) {
                        existente = r;
                        break;  // Categoria encontrada, pode parar de procurar
                    }
                }

                // Se a categoria já existe no relatório...
                if (existente != null) {
                    existente.addVenda(totalVenda); // Adiciona o valor ao total existente
                } else {
                    // Se não existe, cria um novo registro para esta categoria
                    relatorio.add(new RelatorioCategoria(categoria, totalVenda));
                }
            }
        }

        // Atualiza a lista principal de relatórios com os dados calculados
        this.relatorios = relatorio;
    }

    // Método que retorna a lista de relatórios por categoria
    public List<RelatorioCategoria> getRelatorios() {
        return relatorios; // Retorna a lista como está (sem ordenação específica)
    }
}
