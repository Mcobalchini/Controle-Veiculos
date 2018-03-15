package br.edu.utfpr.pb.controleveiculo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Autorizacao implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private String name;
	
	public Autorizacao(){
		
	}
}
