package br.edu.utfpr.pb.controleveiculo.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Tipo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 250, nullable = false)
    private String descricao;

    @Override
    public String toString() {
        return "Tipo [id=" + id + ", descricao=" + descricao + "]";
    }


}
