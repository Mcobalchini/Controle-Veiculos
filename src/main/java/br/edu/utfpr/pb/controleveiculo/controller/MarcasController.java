package br.edu.utfpr.pb.controleveiculo.controller;

import br.edu.utfpr.pb.controleveiculo.model.Marcas;
import br.edu.utfpr.pb.controleveiculo.repository.MarcasRepository;
import lombok.Getter;
import lombok.Setter;
import net.bootsfaces.utils.FacesMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller("marcasController")
@Scope("view")
public class MarcasController {

    @Getter
    @Setter
    private Marcas marcas;
    @Getter
    @Setter
    private List<Marcas> listMarcas;
    @Autowired
    private MarcasRepository marcasRepository;

    @PostConstruct
    public void init() {
        marcas = new Marcas();
        popularLista();
    }

    private void popularLista() {
        this.setListMarcas(marcasRepository.findAll());
    }

    public void salvar() {
        marcasRepository.save(marcas);

        FacesMessages.info("Registro salvo com sucesso!");

        popularLista();
    }

    public void novo() {
        marcas = new Marcas();
    }

    public void editar(Marcas marcas) {
        this.marcas = marcas;
    }

    public void excluir(Long id) {
        marcasRepository.delete(id);

        FacesMessages.info("Registro Removido com sucesso!!!");

        popularLista();
    }
}












