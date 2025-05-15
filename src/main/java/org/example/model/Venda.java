package org.example.model;

import java.sql.Date;

public class Venda {
        private int idNota;
        private java.sql.Date dataNota;
        private double valorNota;
        private double valorItem;
        private int qtdItem;
        private int idProduto;
        private int idCliente;

    public Venda(int idNota, Date dataNota, double valorNota, double valorItem, int qtdItem, int idProduto, int idCliente) {
        this.idNota = idNota;
        this.dataNota = dataNota;
        this.valorNota = valorNota;
        this.valorItem = valorItem;
        this.qtdItem = qtdItem;
        this.idProduto = idProduto;
        this.idCliente = idCliente;
    }

    public Venda() {
    }

    public int getIdNota() {
        return idNota;
    }

    public void setIdNota(int idNota) {
        this.idNota = idNota;
    }

    public Date getDataNota() {
        return dataNota;
    }

    public void setDataNota(Date dataNota) {
        this.dataNota = dataNota;
    }

    public double getValorNota() {
        return valorNota;
    }

    public void setValorNota(double valorNota) {
        this.valorNota = valorNota;
    }

    public double getValorItem() {
        return valorItem;
    }

    public void setValorItem(double valorItem) {
        this.valorItem = valorItem;
    }

    public int getQtdItem() {
        return qtdItem;
    }

    public void setQtdItem(int qtdItem) {
        this.qtdItem = qtdItem;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
