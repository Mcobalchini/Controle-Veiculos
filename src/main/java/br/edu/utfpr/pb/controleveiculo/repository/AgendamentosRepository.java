package br.edu.utfpr.pb.controleveiculo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.pb.controleveiculo.model.Agendamentos;

public interface AgendamentosRepository 
			extends JpaRepository<Agendamentos, Long>{

}
