package br.edu.utfpr.pb.controleveiculo.util;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class FormatUtils {

    public static Double formatDouble(Double valor) {
        if (valor != null) {
            BigDecimal bd = new BigDecimal(valor);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            return bd.doubleValue();
        }
        return 0D;
    }

    public static Date formatDate(Date date) {
        return date;
    }

}
