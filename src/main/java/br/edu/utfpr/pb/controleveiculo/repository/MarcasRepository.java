package br.edu.utfpr.pb.controleveiculo.repository;

import br.edu.utfpr.pb.controleveiculo.model.Marcas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcasRepository
        extends JpaRepository<Marcas, Long> {

}
