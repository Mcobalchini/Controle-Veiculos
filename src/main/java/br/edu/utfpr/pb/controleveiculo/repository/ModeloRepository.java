package br.edu.utfpr.pb.controleveiculo.repository;

import br.edu.utfpr.pb.controleveiculo.model.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeloRepository
        extends JpaRepository<Modelo, Long> {

}
