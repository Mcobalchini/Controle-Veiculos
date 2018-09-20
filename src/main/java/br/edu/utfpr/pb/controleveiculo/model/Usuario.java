package br.edu.utfpr.pb.controleveiculo.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Usuario implements /*UserDetails*/ Serializable {
    private static final long serialVersionUID = 1L;
	private static final BCryptPasswordEncoder bCrypt =
			new BCryptPasswordEncoder(10);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 100, nullable = false)
    private String username;

    @Getter
    @Column(length = 512, nullable = false)
    private String password;

    @Column(length = 150, nullable = false)
    private String email;

    @Column(length = 40)
    private String telefone;

    public void setPassword(String password){
        if(!password.isEmpty()) {
            this.password = bCrypt.encode(password);
        }
    }

}
