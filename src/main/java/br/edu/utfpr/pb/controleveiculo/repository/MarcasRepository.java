package br.edu.utfpr.pb.controleveiculo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.pb.controleveiculo.model.Marcas;

public interface MarcasRepository 
			extends JpaRepository<Marcas, Long>{

}
