package br.edu.utfpr.pb.controleveiculo.repository;

import br.edu.utfpr.pb.controleveiculo.model.Agendamentos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentosRepository
        extends JpaRepository<Agendamentos, Long> {

}
