package br.edu.utfpr.pb.controleveiculo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.pb.controleveiculo.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	Usuario findByUsernameIgnoreCase(String username);
}
