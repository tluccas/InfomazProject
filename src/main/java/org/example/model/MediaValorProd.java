package org.example.model;

public class MediaValorProd {

    private String categoria;
    private int mes;
    private int ano;
    private int quantidade;
    private double valorTotal;
    private double media;
    public MediaValorProd(String categoria, int mes, int ano) {
        this.categoria = categoria;
        this.mes = mes;
        this.ano = ano;
        this.valorTotal = 0;
        this.quantidade = 0;
        this.media = 0;

    }

    public void addVenda(double valor, int quantidade) {
        this.quantidade += quantidade;
        this.valorTotal += valor * quantidade;
    }

    public void calcularMedia() {
        if (quantidade > 0) {
           this.media = valorTotal / quantidade;
        }else{
            this.media = 0;
        }
    }


    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return String.format(
                "\nCategoria: %s\nMês: %s\nAno: %d\nMédia de venda: R$ %.2f\n------------------",
                categoria, mes, ano, media
        );
    }
}
