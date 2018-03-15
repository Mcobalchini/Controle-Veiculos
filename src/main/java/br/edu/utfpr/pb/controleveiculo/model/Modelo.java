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
public class Modelo implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50, nullable = false)
	private String descricao;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="id", name="idMarca")
	private Marcas marca;
	
	@Column(length = 4, nullable = false)
	private String ano;
	
}



