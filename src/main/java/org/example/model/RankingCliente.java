package org.example.model;

public class RankingCliente{

    private int idCliente;
    private String nomeCliente;
    private int mes;
    private int ano;
    private int totalComprado;

    public RankingCliente(int idCliente, String nomeCliente, int mes, int ano, int totalComprado) {
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.mes = mes;
        this.ano = ano;
        this.totalComprado = totalComprado;
    }

    public void adicionarQuantidade(int qtd) {
        this.totalComprado += qtd;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    public int getTotalComprado() {
        return totalComprado;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    @Override
    public String toString() {
        return "Cliente: " + nomeCliente + "\nMês: " + mes + "\nAno: " + ano + "\nCompras: " + totalComprado + "\n";
    }
}

