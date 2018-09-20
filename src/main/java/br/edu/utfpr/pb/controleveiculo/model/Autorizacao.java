package br.edu.utfpr.pb.controleveiculo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class Autorizacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String name;

    public Autorizacao() {

    }
}
