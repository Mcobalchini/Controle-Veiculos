package br.edu.utfpr.pb.controleveiculo.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Agendamentos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 250, nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "idDespesa")
    private Despesas despesa;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "idVeiculo")
    private Veiculo veiculo;

    @Column(length = 100, nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data;
}
