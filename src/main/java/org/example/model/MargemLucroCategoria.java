package org.example.model;

public class MargemLucroCategoria {

    private String categoria;
    private double valorTotalVendas;
    private double valorTotalCusto;
    private double lucroTotal;
    private double margemLucro;

    public MargemLucroCategoria(String categoria) {
        this.categoria = categoria;
        this.valorTotalVendas = 0;
        this.valorTotalCusto = 0;
        this.lucroTotal = 0;
        this.margemLucro = 0;
    }

    public void addVenda(double valorVenda, int quantidade) {
        this.valorTotalVendas += valorVenda * quantidade;
    }
    public void addCusto(double valorCusto){
        this.valorTotalCusto += valorCusto;
    }
    public void calcularLucro() {
        /* Calcula o lucro total (vendas - custo) e a margem de lucro percentual (inicialmente iria calcular
        apenas o lucro total mas ao pesquisar sobre margem de lucro vi que são coisas ""diferentes""*/
        if (valorTotalVendas > 0) {
            this.lucroTotal = valorTotalVendas - valorTotalCusto;
            this.margemLucro = lucroTotal / valorTotalVendas;  // margem percentual 0 a 1
        } else {
            this.lucroTotal = 0;
            this.margemLucro = 0;
        }
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getValorTotalVendas() {
        return valorTotalVendas;
    }

    public void setValorTotalVendas(double valorTotalVendas) {
        this.valorTotalVendas = valorTotalVendas;
    }

    public double getValorTotalCusto() {
        return valorTotalCusto;
    }

    public void setValorTotalCusto(double valorTotalCusto) {
        this.valorTotalCusto = valorTotalCusto;
    }

    public double getLucroTotal() {
        return lucroTotal;
    }

    public void setLucroTotal(double lucroTotal) {
        this.lucroTotal = lucroTotal;
    }

    @Override
    public String toString() {
        return String.format(
                "Categoria: %s\nLucro Total: R$ %.2f\nMargem de Lucro: %.2f%%\n----------",
                categoria, lucroTotal, margemLucro * 100 //converte em porcentagem (na variavel é armazenado como 0.10 por ex)
        );
    }
}
