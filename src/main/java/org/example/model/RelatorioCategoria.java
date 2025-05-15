package org.example.model;

public class RelatorioCategoria { //Classe para poder gerar o relatorio de venda por categoria
    private String categoria;
    private double valor;

    public RelatorioCategoria(String categoria, double valor) {
        this.categoria = categoria;
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String idCategoria) {
        this.categoria = idCategoria;
    }

    public double getValor() {
        return valor;
    }

    public void addVenda(double valor) {
        this.valor += valor;
    }

    public String toString() {

        return "\n\nCategoria: " + categoria + " \nTotal Vendas:" + valor;
    }
}
