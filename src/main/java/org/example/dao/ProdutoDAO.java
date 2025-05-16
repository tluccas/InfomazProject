package org.example.dao;
import org.example.model.Fornecedor;
import org.example.model.Produto;

import java.sql.*;
import java.util.*;

public class ProdutoDAO {
    private Connection conn;

    public ProdutoDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Produto> listarProduto() throws SQLException {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM cadastro_produtos";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Produto p = new Produto();
                p.setIdProduto(rs.getInt("idProduto"));
                p.setIdEstoque(rs.getInt("idEstoque"));
                p.setNomeProduto(rs.getString("nomeProduto"));
                p.setCategoria(rs.getString("categoria"));
                lista.add(p);
            }
        }
        return lista;
    }

    public Produto buscarProduto(int id) throws SQLException { // Busca o produto pelo id padrao
        List<Produto> lista = listarProduto();
        for (Produto p : lista) {
            if(id == p.getIdProduto()) {
                return p;
            }
        }
        return null;
    }

    public Produto buscarProdutoPorIdEstoque(int idEstoque) throws SQLException { //Busca o produto pelo id de estoque
        List<Produto> lista = listarProduto();
        for (Produto p : lista) {
            if(idEstoque == p.getIdProduto()) {
                return p;
            }
        }
        return null;
    }
}
