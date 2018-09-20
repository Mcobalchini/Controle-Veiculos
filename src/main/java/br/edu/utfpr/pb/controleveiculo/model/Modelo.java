package br.edu.utfpr.pb.controleveiculo.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Modelo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "idMarca")
    private Marcas marca;

    @Column(length = 4, nullable = false)
    private String ano;

}



