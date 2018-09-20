package br.edu.utfpr.pb.controleveiculo.controller;

import br.edu.utfpr.pb.controleveiculo.model.Usuario;
import br.edu.utfpr.pb.controleveiculo.model.Veiculo;
import br.edu.utfpr.pb.controleveiculo.repository.UsuarioRepository;
import lombok.Getter;
import lombok.Setter;
import net.bootsfaces.utils.FacesMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller("usuarioController")
@Scope("view")
public class UsuarioController {

    @Getter
    @Setter
    private Usuario usuario;
    @Getter
    @Setter
    private List<Veiculo> listVeiculo;
    @Getter
    @Setter
    private List<Usuario> listUsuario;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostConstruct
    public void init() {
        usuario = new Usuario();
        popularLista();
    }

    private void popularLista() {
        this.setListUsuario(usuarioRepository.findAll());
    }

    public String salvar() {

        if (usuarioRepository.findByUsernameIgnoreCase(usuario.getUsername()) != null) {
            FacesMessages.error("Já existe um usuário cadastrado com esse nome");
            return "";
        } else {
            usuarioRepository.save(usuario);
            FacesMessages.info("Usuario criado com sucesso!!!");
            return "/login.jsf?usuario=#{usuario.username}";
        }
    }

    public void novo() {
        usuario = new Usuario();
    }

    public void editar(Usuario usuario) {
        this.usuario = usuario;
    }

    public void excluir(Long id) {
        usuarioRepository.delete(id);
        popularLista();
    }

}
