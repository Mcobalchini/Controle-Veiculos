package br.edu.utfpr.pb.controleveiculo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Tipo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=250, nullable=false)
	private String descricao;

	@Override
	public String toString() {
		return "Tipo [id=" + id + ", descricao=" + descricao + "]";
	}
	
	

}
