package org.example.dao;
import org.example.model.Fornecedor;

import java.sql.*;
import java.util.*;

public class FornecedorDAO {
    private Connection conn;

    public FornecedorDAO(Connection conn) {
        this.conn = conn;
    }

    public void inserir(Fornecedor fornecedor) throws SQLException {
        String sql = "INSERT INTO cadastro_fornecedores (idFornecedor, nomeFornecedor, dataCadastro) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, fornecedor.getIdFornecedor());
            stmt.setString(2, fornecedor.getNomeFornecedor());
            stmt.setDate(3, fornecedor.getDataCadastro());
            stmt.executeUpdate();
        }
    }

    public List<Fornecedor> listarFornecedor() throws SQLException {
        List<Fornecedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM cadastro_fornecedores";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Fornecedor f = new Fornecedor();
                f.setIdFornecedor(rs.getString("idFornecedor"));
                f.setNomeFornecedor(rs.getString("nomeFornecedor"));
                f.setDataCadastro(rs.getDate("dataCadastro"));
                lista.add(f);
            }
        }
        return lista;
    }

    public Fornecedor buscarFornecedor(String id) throws SQLException {
        List<Fornecedor> lista = listarFornecedor();
        for (Fornecedor f : lista) {
            if(id.equals(f.getIdFornecedor())) {
                return f;
            }
        }
        return null;
    }
}
