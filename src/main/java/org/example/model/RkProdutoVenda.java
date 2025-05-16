package org.example.model;

public class RkProdutoVenda {
    private String nome;
    private int idProduto;
    private int mes;
    private int ano;
    private double valorVendido;

    public RkProdutoVenda(String nome, int idProduto, int mes, int ano, double valorVendido) {
        this.nome = nome;
        this.idProduto = idProduto;
        this.mes = mes;
        this.ano = ano;
        this.valorVendido = valorVendido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getValorVendido() {
        return valorVendido;
    }

    public void setValorVendido(double valorVendido) {
        this.valorVendido += valorVendido;
    }

    @Override
    public String toString() {
        return String.format("Produto: %s\nMês: %d\nAno: %d\nValor de Vendas: %.2f\n\n===========",
                nome, mes, ano, valorVendido

        );
    }
}
