package br.edu.utfpr.pb.controleveiculo.model;

import br.edu.utfpr.pb.controleveiculo.util.FormatUtils;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Despesas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 250, nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "idTipo")
    private Tipo tipo;

    @Column(length = 1, nullable = false)
    private boolean status;

    @Column(length = 10, nullable = false)
    private Double valor;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "idVeiculo")
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "idUsuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "idAbastecimento")
    private Abastecimentos abastecimento;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "idMulta")
    private Multas multa;

    public double getValor() {
        return FormatUtils.formatDouble(valor);
    }
}
