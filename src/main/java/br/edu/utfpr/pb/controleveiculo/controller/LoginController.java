package br.edu.utfpr.pb.controleveiculo.controller;

import br.edu.utfpr.pb.controleveiculo.model.Usuario;
import br.edu.utfpr.pb.controleveiculo.repository.UsuarioRepository;
import br.edu.utfpr.pb.controleveiculo.session.SessionUtil;
import lombok.Getter;
import lombok.Setter;
import net.bootsfaces.utils.FacesMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

import javax.annotation.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;

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
    private static final BCryptPasswordEncoder bCrypt =
            new BCryptPasswordEncoder(10);


    public String autentica() {
        if (usuarioRepository.findByUsernameIgnoreCase(username) == null) {
            FacesMessages.error("Usuário ou senha incorretos");
            return "";
        } else {
            if(password != null) {
                usuario = usuarioRepository.findByUsernameIgnoreCase(username);
                if (!bCrypt.matches(password, usuario.getPassword())) {
                    FacesMessages.error("Usuário ou senha incorretos");
                    return "";
                } else {
                    // ADD USUARIO NA SESSION
                    SessionUtil.setParam("USUARIOLogado", usuario);
                    FacesMessages.info("Login realizado com sucesso!");
                    //Invalida o usuário para que não fique atrelado ao controller
                    invalidaLogin();
                    return "/secured/index.jsf?faces-redirect=true";
                }
            }else{
                FacesMessages.error("Preencha a senha novamente");
                return "";
            }

        }

    }

    private void invalidaLogin() {
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


    public String novoUser() {
        usuario = new Usuario();
        return "/cadastro.jsf";
    }


    public void doLogout() throws IOException {
        // Set the parameter indicating that user is logged in to false
        setLoggedIn(false);
        invalidaLogin();
        SessionUtil.setParam("USUARIOLogado", null);
        FacesContext fContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = fContext.getExternalContext();
        extContext.redirect(extContext.getRequestContextPath() + "/login.jsf");
    }


}