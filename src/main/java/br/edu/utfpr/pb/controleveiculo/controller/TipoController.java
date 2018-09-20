package br.edu.utfpr.pb.controleveiculo.controller;

import br.edu.utfpr.pb.controleveiculo.model.Tipo;
import br.edu.utfpr.pb.controleveiculo.repository.TipoRepository;
import lombok.Getter;
import lombok.Setter;
import net.bootsfaces.utils.FacesMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller("tipoController")
@Scope("view")
public class TipoController {

    @Getter
    @Setter
    private Tipo tipo;

    @Getter
    @Setter
    private List<Tipo> listTipo;

    @Autowired
    private TipoRepository tiposRepository;

    @PostConstruct
    public void init() {
        tipo = new Tipo();
        popularLista();
    }

    private void popularLista() {
        this.setListTipo(tiposRepository.findAll());
    }

    public void salvar() {
        tiposRepository.save(tipo);
        FacesMessages.info("Registro salvo com sucesso!");
        popularLista();
        novo();
    }

    public void novo() {
        tipo = new Tipo();
    }

    public void editar(Tipo tipos) {
        this.tipo = tipos;
    }

    public void excluir(Long id) {
        tiposRepository.delete(id);
        FacesMessages.info("Registro Removido com sucesso!");

        popularLista();
    }
}

