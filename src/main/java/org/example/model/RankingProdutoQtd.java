package org.example.model;

public class RankingProdutoQtd {

    private String nome;
    private int idProduto;
    private int mes;
    private int ano;
    private int totalVendido;

    public RankingProdutoQtd(String nome, int idProduto, int mes, int ano, int totalVendido) {
        this.nome = nome;
        this.idProduto = idProduto;
        this.mes = mes;
        this.ano = ano;
        this.totalVendido = totalVendido;
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

    public int getTotalVendido() {
        return totalVendido;
    }

    public void setTotalVendido(int totalVendido) {
        this.totalVendido += totalVendido;
    }

    @Override
    public String toString() {
        return
                "Produto: '" + nome + '\'' +
                "\nMes: " + mes +
                "\nAno: " + ano +
                "\nTotal de Vendas: " + totalVendido +
                "\n============";
    }
}
