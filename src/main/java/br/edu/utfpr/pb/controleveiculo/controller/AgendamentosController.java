package br.edu.utfpr.pb.controleveiculo.controller;

import br.edu.utfpr.pb.controleveiculo.model.Agendamentos;
import br.edu.utfpr.pb.controleveiculo.model.Despesas;
import br.edu.utfpr.pb.controleveiculo.model.Veiculo;
import br.edu.utfpr.pb.controleveiculo.repository.AgendamentosRepository;
import br.edu.utfpr.pb.controleveiculo.repository.DespesasRepository;
import br.edu.utfpr.pb.controleveiculo.repository.VeiculoRepository;
import lombok.Getter;
import lombok.Setter;
import net.bootsfaces.utils.FacesMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller("agendamentosController")
@Scope("view")
public class AgendamentosController {

    @Getter
    @Setter
    private Agendamentos agendamentos;
    @Getter
    @Setter
    private List<Despesas> listDespesas;
    @Autowired
    private VeiculoRepository veiculosRepository;
    @Getter
    @Setter
    private List<Veiculo> listVeiculos;
    @Autowired
    private DespesasRepository despesasRepository;
    @Getter
    @Setter
    private List<Agendamentos> listAgendamentos;
    @Autowired
    private AgendamentosRepository agendamentosRepository;

    @PostConstruct
    public void init() {
        agendamentos = new Agendamentos();
        popularLista();
    }

    private void popularLista() {
        this.setListAgendamentos(agendamentosRepository.findAll());
        this.setListDespesas(despesasRepository.findAll());
        this.setListVeiculos(veiculosRepository.findAll());
    }

    public void salvar() {
        agendamentosRepository.save(agendamentos);
        FacesMessages.info("Registro salvo com sucesso!!!");
        popularLista();
        novo();
    }

    public void novo() {
        agendamentos = new Agendamentos();
    }

    public void editar(Agendamentos agendamentos) {
        this.agendamentos = agendamentos;
    }

    public List<Despesas> getDespesas() {
        return despesasRepository.findAll();
    }

    public List<Veiculo> getVeiculos() {
        return veiculosRepository.findAll();
    }

    public void excluir(Long id) {
        agendamentosRepository.delete(id);
        FacesMessages.info("Registro Removido");
        popularLista();
    }
}