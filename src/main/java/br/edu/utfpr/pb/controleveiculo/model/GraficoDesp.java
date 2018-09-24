package br.edu.utfpr.pb.controleveiculo.model;

import br.edu.utfpr.pb.controleveiculo.util.FormatUtils;
import lombok.Data;

@Data
public class GraficoDesp {

    private String descricao;
    private double valor;
    private double vlrTotal;

    public GraficoDesp(String descricao, double valor, double vlrTotal) {
        super();
        this.descricao = descricao;
        this.valor = valor;
        this.vlrTotal = vlrTotal;
    }

    public double getVlrTotal() {
        return FormatUtils.formatDouble(vlrTotal);
    }

    public double getValor() {
        return FormatUtils.formatDouble(valor);
    }
}
