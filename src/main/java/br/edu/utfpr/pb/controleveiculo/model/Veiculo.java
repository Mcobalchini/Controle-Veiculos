package br.edu.utfpr.pb.controleveiculo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
public class Veiculo implements Serializable{

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

	@Column(length = 8, nullable = true)
	private double kmpercorrido;
	
	@Column(length = 8, nullable = true)
	private double kmapercorrer;
	
	@Column(length = 8, nullable = true)
	private double kmPneus;
	
	@Temporal(TemporalType.DATE)
	@Column(length = 12, nullable=true)
	private Date ultimaCalibragem;
			
	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name="idModelo")
	private Modelo modelo;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id", name="idUsuario")
	private Usuario usuario;
}