package br.edu.utfpr.pb.controleveiculo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Despesas implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 250, nullable = false)
	private String descricao;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name="idTipo")
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
	@JoinColumn(referencedColumnName = "id", name="idAbastecimento", nullable = true)
	private Abastecimentos abastecimento;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name="idMulta" ,nullable = true)
	private Multas multa;
	
	
	

	
		
}
