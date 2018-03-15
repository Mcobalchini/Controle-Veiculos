package br.edu.utfpr.pb.controleveiculo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.pb.controleveiculo.model.Modelo;

public interface ModeloRepository 
				extends JpaRepository<Modelo, Long>{

}
