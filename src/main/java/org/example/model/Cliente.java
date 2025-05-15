package org.example.model;

import java.sql.Date;

public class Cliente {
        private int idCliente;
        private String nomeCliente;
        private java.sql.Date dataCadastro;

    public Cliente(int idCliente, String nomeCliente, Date dataCadastro) {
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.dataCadastro = dataCadastro;
    }

    public Cliente() {
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
