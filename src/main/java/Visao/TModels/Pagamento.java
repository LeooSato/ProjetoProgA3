package Visao.TModels;

import java.util.Date;

public class Pagamento {
    private Date dataPagamento;
    private double valor;
    private String status;

    public Pagamento(Date dataPagamento, double valor, String status) {
        this.dataPagamento = dataPagamento;
        this.valor = valor;
        this.status = status;
    }

    // Getters e Setters
    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
