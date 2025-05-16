package org.example.dao;
import org.example.model.*;

import java.sql.*;
import java.util.*;

public class EstoqueDAO {
    private Connection conn;

    public EstoqueDAO(Connection conn) {
        this.conn = conn;
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

    public Estoque buscarEstoque(int id) throws SQLException {
        List<Estoque> lista = listarEstoque();
        for (Estoque est : lista) {
            if (est.getIdEstoque() == id) {
                return est;
            }
        }
        return null;
    }
}