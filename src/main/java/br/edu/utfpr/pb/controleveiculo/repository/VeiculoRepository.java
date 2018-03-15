package br.edu.utfpr.pb.controleveiculo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.utfpr.pb.controleveiculo.model.Veiculo;

public interface VeiculoRepository 
				extends JpaRepository<Veiculo, Long>{

	@Query(value = "select * from veiculo where id_usuario = ?", nativeQuery = true)	
	public List< Veiculo >findByUsuario(Long id);
	
}
