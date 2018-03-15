package br.edu.utfpr.pb.controleveiculo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.pb.controleveiculo.model.Multas;

public interface MultasRepository extends JpaRepository<Multas, Long>{

}
