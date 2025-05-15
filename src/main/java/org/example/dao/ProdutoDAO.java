package org.example.dao;
import org.example.model.Produto;

import java.sql.*;
import java.util.*;

public class ProdutoDAO {
    private Connection conn;

    public ProdutoDAO(Connection conn) {
        this.conn = conn;
    }

    public void inserir(Produto produto) throws SQLException {
        String sql = "INSERT INTO cadastro_produtos (idProduto, idEstoque, nomeProduto, categoria) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, produto.getIdProduto());
            stmt.setInt(2, produto.getIdEstoque());
            stmt.setString(3, produto.getNomeProduto());
            stmt.setString(4, produto.getCategoria());
            stmt.executeUpdate();
        }
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
}
