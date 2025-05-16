package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class HistoricoCliente {
    private int idCliente;
    private String nomeCliente;
    private List<String> comprados;

    public HistoricoCliente(int idCliente, String nomeCliente) {
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.comprados = new ArrayList<String>();
    }

    public void addCompra(String produto) {
        comprados.add(produto);
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

    public List<String> getComprados() {
        return comprados;
    }

    public void setComprados(List<String> comprados) {
        this.comprados = comprados;
    }


    @Override
    public String toString() {
        return String.format(
                "\nNome: %s \nItens Comprados: %s\n===========\n",
                nomeCliente, String.join("\n >>>", comprados));
    }
}
