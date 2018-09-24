package br.edu.utfpr.pb.controleveiculo.model;

import br.edu.utfpr.pb.controleveiculo.util.FormatUtils;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class Multas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 250, nullable = false)
    private String Motivo;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "idUsuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "idVeiculo")
    private Veiculo veiculo;

    @Column(length = 2, nullable = false)
    private int pontos;

    @Temporal(TemporalType.DATE)
    @Column(length = 12, nullable = false)
    private Date dataMulta;

    @Temporal(TemporalType.DATE)
    @Column(length = 12, nullable = false)
    private Date dataVencimento;

    @Column(length = 20, nullable = false)
    private String gravidade;

    @Column(length = 8, nullable = false)
    private double valor;

    public Date getDataVencimento() {
        return FormatUtils.formatDate(dataVencimento);
    }

    public Date getDataMulta() {
        return FormatUtils.formatDate(dataMulta);
    }

    public double getValor() {
        return FormatUtils.formatDouble(valor);
    }
}
