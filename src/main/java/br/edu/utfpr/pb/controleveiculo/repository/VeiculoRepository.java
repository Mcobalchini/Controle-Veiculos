package br.edu.utfpr.pb.controleveiculo.repository;

import br.edu.utfpr.pb.controleveiculo.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VeiculoRepository
        extends JpaRepository<Veiculo, Long> {

    @Query(value = "select * from veiculo where id_usuario = ?", nativeQuery = true)
    List<Veiculo> findByUsuario(Long id);

}
