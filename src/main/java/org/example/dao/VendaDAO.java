package org.example.dao;
import org.example.model.Venda;

import java.sql.*;
import java.util.*;

public class VendaDAO {
    private Connection conn;

    public VendaDAO(Connection conn) {
        this.conn = conn;
    }

    public void inserir(Venda venda) throws SQLException {
        String sql = "INSERT INTO transacoes_vendas (idNota, dataNota, valorNota, valorItem, qtdItem, idProduto, idCliente) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, venda.getIdNota());
            stmt.setDate(2, venda.getDataNota());
            stmt.setDouble(3, venda.getValorNota());
            stmt.setDouble(4, venda.getValorItem());
            stmt.setInt(5, venda.getQtdItem());
            stmt.setInt(6, venda.getIdProduto());
            stmt.setInt(7, venda.getIdCliente());
            stmt.executeUpdate();
        }
    }

    public List<Venda> listarVenda() throws SQLException {
        List<Venda> lista = new ArrayList<>();
        String sql = "SELECT * FROM transacoes_vendas";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Venda v = new Venda();
                v.setIdNota(rs.getInt("idNota"));
                v.setDataNota(rs.getDate("dataNota"));
                v.setValorNota(rs.getDouble("valorNota"));
                v.setValorItem(rs.getDouble("valorItem"));
                v.setQtdItem(rs.getInt("qtdItem"));
                v.setIdProduto(rs.getInt("idProduto"));
                v.setIdCliente(rs.getInt("idCliente"));
                lista.add(v);
            }
        }
        return lista;
    }
}