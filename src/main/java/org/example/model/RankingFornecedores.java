package org.example.model;

public class RankingFornecedores {
    private String idFornecedor;
    private String nomeFornecedor;
    private int ano;
    private int mes;
    private int estoque;

    public RankingFornecedores(String idFornecedor, String nomeFornecedor, int ano, int mes, int estoque) {
        this.idFornecedor = idFornecedor;
        this.nomeFornecedor = nomeFornecedor;
        this.ano = ano;
        this.mes = mes;
        this.estoque = estoque;
    }

    public String getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(String idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque += estoque;
    }

    public String toString() {
        return "Fornecedor: " + nomeFornecedor + "\nMes: " + mes + "\nAno: " + ano + "\nEstoque: " + estoque + "\n===============\n";
    }
}
