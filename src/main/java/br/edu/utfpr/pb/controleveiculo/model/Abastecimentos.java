package br.edu.utfpr.pb.controleveiculo.model;

import br.edu.utfpr.pb.controleveiculo.util.FormatUtils;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Abastecimentos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 250, nullable = false)
    private String posto;

    @Column(length = 50, nullable = false)
    private Double valorLitro;


    @Column(length = 50, nullable = false)
    private Double valorPago;

    @Getter
    @Column(length = 50, nullable = false)
    private Double litros;

    @Column(length = 10)
    private Double hodometroInformado;

    @Temporal(TemporalType.DATE)
    private Date data;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "idVeiculo")
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "idUsuario")
    private Usuario usuario;


    public Double getLitros() {
        return FormatUtils.formatDouble(this.litros);
    }


    public void setValorLitro(Double valorLitro) {
        this.valorLitro = valorLitro;
    }

    public Date getData() {
        return FormatUtils.formatDate(data);
    }

}
