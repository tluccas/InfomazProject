package org.example.model;

public class RkEstoque {
    private int idEstoque;
    private String nomeProduto;
    private int quantidade;

    public RkEstoque(int idEstoque, String nomeProduto) {
        this.idEstoque = idEstoque;
        this.nomeProduto = nomeProduto;
        this.quantidade = 0;
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "ID Estoque: " + idEstoque + "\nNome Produto: " + nomeProduto +
                "\nQuantidade: " + quantidade + "\n=============";
    }
}
