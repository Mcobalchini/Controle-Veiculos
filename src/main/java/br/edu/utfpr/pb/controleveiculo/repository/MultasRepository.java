package br.edu.utfpr.pb.controleveiculo.repository;

import br.edu.utfpr.pb.controleveiculo.model.Multas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MultasRepository extends JpaRepository<Multas, Long> {

}
