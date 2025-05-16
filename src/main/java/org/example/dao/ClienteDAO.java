package org.example.dao;

import org.example.model.Cliente;

import java.sql.*;
import java.util.*;

public class ClienteDAO {
    private Connection conn;

    public ClienteDAO(Connection conn) {
        this.conn = conn;
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
