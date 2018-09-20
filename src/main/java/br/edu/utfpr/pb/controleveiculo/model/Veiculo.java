package br.edu.utfpr.pb.controleveiculo.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Veiculo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 250, nullable = false)
    private String nome;

    @Column(length = 8, nullable = false)
    private String placa;

    @Column(length = 8, nullable = false)
    private double litragem;

    @Column(length = 8)
    private double kmpercorrido;

    @Column(length = 8)
    private double kmapercorrer;

    @Column(length = 8)
    private double kmPneus;

    @Column(length = 10)
    private double hodometroAtual;

    @Column(length = 10)
    private double hodometroAnterior;

    @Temporal(TemporalType.DATE)
    @Column(length = 12, nullable = true)
    private Date ultimaCalibragem;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "idModelo")
    private Modelo modelo;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "idUsuario")
    private Usuario usuario;
}