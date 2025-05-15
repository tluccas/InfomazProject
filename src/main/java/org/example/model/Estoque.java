package org.example.model;
import java.sql.*;
public class Estoque {
    private int idEstoque;
    private double valorEstoque;
    private int qtdEstoque;
    private java.sql.Date dataEstoque;
    private String idFornecedor;

    public Estoque(int idEstoque, double valorEstoque, int qtdEstoque, Date dataEstoque, String idFornecedor) {
        this.idEstoque = idEstoque;
        this.valorEstoque = valorEstoque;
        this.qtdEstoque = qtdEstoque;
        this.dataEstoque = dataEstoque;
        this.idFornecedor = idFornecedor;
    }

    public Estoque() {
    }

    public int getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(int idEstoque) {
        this.idEstoque = idEstoque;
    }

    public double getValorEstoque() {
        return valorEstoque;
    }

    public void setValorEstoque(double valorEstoque) {
        this.valorEstoque = valorEstoque;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public Date getDataEstoque() {
        return dataEstoque;
    }

    public void setDataEstoque(Date dataEstoque) {
        this.dataEstoque = dataEstoque;
    }

    public String getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(String idFornecedor) {
        this.idFornecedor = idFornecedor;
    }
}
