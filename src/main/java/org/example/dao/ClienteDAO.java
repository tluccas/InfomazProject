package org.example.dao;

import org.example.model.Cliente;

import java.sql.*;
import java.util.*;

public class ClienteDAO {
    private Connection conn;

    public ClienteDAO(Connection conn) {
        this.conn = conn;
    }

    public void inserir(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO cadastro_clientes (idCliente, nomeCliente, dataCadastro) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cliente.getIdCliente());
            stmt.setString(2, cliente.getNomeCliente());
            stmt.setDate(3, cliente.getDataCadastro());
            stmt.executeUpdate();
        }
    }

    public List<Cliente> listarCliente() throws SQLException {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cadastro_clientes";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setIdCliente(rs.getInt("idCliente"));
                c.setNomeCliente(rs.getString("nomeCliente"));
                c.setDataCadastro(rs.getDate("dataCadastro"));
                lista.add(c);
            }
        }
        return lista;
    }

    public Cliente buscarCliente(int id) throws SQLException {
        List<Cliente> lista = listarCliente();
        for (Cliente c : lista) {
            if (c.getIdCliente() == id) {
                return c;
            }
        }
        return null;
    }
}
