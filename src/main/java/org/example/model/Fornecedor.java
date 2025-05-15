package org.example.model;
import java.sql.*;


public class Fornecedor {

    private String idFornecedor;
    private String nomeFornecedor;
    private java.sql.Date dataCadastro;

    public Fornecedor(String idFornecedor, String nomeFornecedor, Date dtFornecedor) {
        this.idFornecedor = idFornecedor;
        this.nomeFornecedor = nomeFornecedor;
        this.dataCadastro = dtFornecedor;
    }

    public Fornecedor() {
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

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
