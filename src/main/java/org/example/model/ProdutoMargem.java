package org.example.model;

public class ProdutoMargem {
    int idProduto;
    String nomeProduto;
    double margem;

    public ProdutoMargem(int idProduto, String nomeProduto, double margem) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.margem = margem;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getMargem() {
        return margem;
    }

    public void calcMargem(double margem) {
        this.margem += margem;
    }

    public String toString() {
        return "\n------\nNome: \n" + nomeProduto + "\nId: " + idProduto + "\nMargem: " + margem + "\n--------\n";
    }
}
