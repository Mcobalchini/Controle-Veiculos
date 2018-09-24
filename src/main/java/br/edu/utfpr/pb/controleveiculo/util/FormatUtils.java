package br.edu.utfpr.pb.controleveiculo.util;


import java.util.Date;

public class FormatUtils {

    public static Double formatDouble(Double valor) {
        if (valor != null) {
            return (double) Math.round(valor);
        } else {
            return 0D;
        }
    }

    public static Date formatDate(Date date) {
        return date;
    }

}
