package br.edu.utfpr.pb.controleveiculo.model;

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

}
