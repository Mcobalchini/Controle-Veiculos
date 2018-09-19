package br.edu.utfpr.pb.controleveiculo.repository;

import br.edu.utfpr.pb.controleveiculo.model.Abastecimentos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbastecimentosRepository extends JpaRepository<Abastecimentos, Long>{

    Abastecimentos findFirst1ByVeiculoIdOrderByIdDesc(Long id);

}
