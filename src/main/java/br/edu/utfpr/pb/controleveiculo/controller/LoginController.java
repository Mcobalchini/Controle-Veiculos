package br.edu.utfpr.pb.controleveiculo.controller;

import br.edu.utfpr.pb.controleveiculo.model.Usuario;
import br.edu.utfpr.pb.controleveiculo.repository.UsuarioRepository;
import br.edu.utfpr.pb.controleveiculo.session.SessionUtil;
import lombok.Getter;
import lombok.Setter;
import net.bootsfaces.utils.FacesMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@Controller("loginController")
@ManagedBean
@SessionScoped
public class LoginController {

	private static final long serialVersionUID = 1L;
	public boolean loggedIn;
	@Getter
	@Setter
	private String username;
	@Getter
	@Setter
	private String password;
	@Getter
	@Setter
	@ManagedProperty(value = "#{user}")
	private Usuario usuario;
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	

	public String autentica() {
		if (usuarioRepository.findByUsernameIgnoreCase(username) == null) {
			FacesMessages.error("Usuário ou senha incorretos");
			return "";
		} else {
			usuario = usuarioRepository.findByUsernameIgnoreCase(username);
			if (!usuario.getPassword().equals(password)) {
				FacesMessages.error("Usuário ou senha incorretos");
				return "";
			} else {
				// ADD USUARIO NA SESSION								
				SessionUtil.setParam("USUARIOLogado", usuario);				
				FacesMessages.info("Login realizado com sucesso!");
				//Invalida o usuário para que não fique atrelado ao controller
				invalidaUsuario();
				return "/secured/index.jsf?faces-redirect=true";
			}

		}

	}

    private void invalidaUsuario() {
	    usuario = null;
	    password = null;
	    username = null;
    }

    public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}


	public String novoUser(){
		usuario = new Usuario();
		return "/cadastro.jsf";
	}
	

	public String doLogout() {
		// Set the paremeter indicating that user is logged in to false
		setLoggedIn(false);
		usuario = null;
		SessionUtil.setParam("USUARIOLogado", null);		
		return "../login.jsf";
	}

		
}