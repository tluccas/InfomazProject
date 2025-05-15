package org.example.model;

public class Produto {
    private int idProduto;
    private int idEstoque;
    private String nomeProduto;
    private String categoria;

    public Produto(int idProduto, int idEstoque, String nomeProduto, String categoria) {
        this.idProduto = idProduto;
        this.idEstoque = idEstoque;
        this.nomeProduto = nomeProduto;
        this.categoria = categoria;
    }

    public Produto() {
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(int idEstoque) {
        this.idEstoque = idEstoque;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}