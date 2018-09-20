package br.edu.utfpr.pb.controleveiculo.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Usuario implements /*UserDetails*/ Serializable {
    private static final long serialVersionUID = 1L;
/*	private static final BCryptPasswordEncoder bCrypt = 
			new BCryptPasswordEncoder(10);*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 100, nullable = false)
    private String username;

    @Column(length = 512, nullable = false)
    private String password;

    @Column(length = 150, nullable = false)
    private String email;

    @Column(length = 40, nullable = true)
    private String telefone;
}
