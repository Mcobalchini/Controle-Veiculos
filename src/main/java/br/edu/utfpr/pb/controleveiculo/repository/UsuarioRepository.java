package br.edu.utfpr.pb.controleveiculo.repository;

import br.edu.utfpr.pb.controleveiculo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsernameIgnoreCase(String username);
}
