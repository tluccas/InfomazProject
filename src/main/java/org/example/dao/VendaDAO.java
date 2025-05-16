package org.example.dao;
import org.example.model.Venda;

import java.sql.*;
import java.util.*;

public class VendaDAO {
    private Connection conn;

    public VendaDAO(Connection conn) {
        this.conn = conn;
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