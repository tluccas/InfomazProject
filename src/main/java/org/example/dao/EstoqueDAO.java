package org.example.dao;
import org.example.model.*;

import java.sql.*;
import java.util.*;

public class EstoqueDAO {
    private Connection conn;

    public EstoqueDAO(Connection conn) {
        this.conn = conn;
    }

    public void inserir(Estoque estoque) throws SQLException {
        String sql = "INSERT INTO cadastro_estoque (idEstoque, valorEstoque, qtdEstoque, dataEstoque, idFornecedor) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, estoque.getIdEstoque());
            stmt.setDouble(2, estoque.getValorEstoque());
            stmt.setInt(3, estoque.getQtdEstoque());
            stmt.setDate(4, estoque.getDataEstoque());
            stmt.setString(5, estoque.getIdFornecedor());
            stmt.executeUpdate();
        }
    }

    public List<Estoque> listarEstoque() throws SQLException {
        List<Estoque> lista = new ArrayList<>();
        String sql = "SELECT * FROM cadastro_estoque";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Estoque e = new Estoque();
                e.setIdEstoque(rs.getInt("idEstoque"));
                e.setValorEstoque(rs.getDouble("valorEstoque"));
                e.setQtdEstoque(rs.getInt("qtdEstoque"));
                e.setDataEstoque(rs.getDate("dataEstoque"));
                e.setIdFornecedor(rs.getString("idFornecedor"));
                lista.add(e);
            }
        }
        return lista;
    }
}