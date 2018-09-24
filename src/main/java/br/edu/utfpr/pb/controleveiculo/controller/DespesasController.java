package br.edu.utfpr.pb.controleveiculo.controller;

import br.edu.utfpr.pb.controleveiculo.model.*;
import br.edu.utfpr.pb.controleveiculo.repository.DespesasRepository;
import br.edu.utfpr.pb.controleveiculo.repository.TipoRepository;
import br.edu.utfpr.pb.controleveiculo.repository.VeiculoRepository;
import br.edu.utfpr.pb.controleveiculo.session.SessionUtil;
import br.edu.utfpr.pb.controleveiculo.util.FormatUtils;
import lombok.Getter;
import lombok.Setter;
import net.bootsfaces.utils.FacesMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller("despesasController")
@Scope("view")
public class DespesasController {

    @Getter
    @Setter
    private Despesas despesas;
    @Getter
    @Setter
    private Veiculo veiculo;
    @Getter
    @Setter
    private Usuario usuario;
    @Getter
    @Setter
    private List<Despesas> listDespesas;
    private List<GraficoDesp> listDespesasTipo;
    @Setter
    private List<Despesas> listDespesasUser;
    @Autowired
    private DespesasRepository despesasRepository;
    @Autowired
    private VeiculoRepository veiculosRepository;
    @Getter
    @Setter
    private List<Veiculo> listVeiculos;
    @Autowired
    private TipoRepository tipoRepository;
    @Getter
    @Setter
    private List<Tipo> listTipos;

    @PostConstruct
    public void init() {
        usuario = (Usuario) SessionUtil.getParam("USUARIOLogado");
        despesas = new Despesas();
        veiculo = new Veiculo();
        veiculo.setId((long) 1);
        popularLista();
    }

    private void popularLista() {
        this.setListDespesasTipo(despesasRepository.findAllByTipo(veiculo.getId()));
        this.setListDespesas(despesasRepository.findAll());
        this.setListVeiculos(veiculosRepository.findByUsuario(usuario.getId()));
        this.setListTipos(tipoRepository.findAll());
    }

    public void salvar() {
        despesas.setUsuario(usuario);
        despesasRepository.save(despesas);
        FacesMessages.info("Registro salvo com sucesso!!!");
        popularLista();
        novo();
    }

    public void novo() {
        despesas = new Despesas();
    }

    public void editar(Despesas despesas) {
        this.despesas = despesas;
    }


    public void excluir(Long id) {
        despesasRepository.delete(id);
        FacesMessages.info("Registro Removido");
        popularLista();
    }

    public List<GraficoDesp> getListDespesasTipo() {
        return despesasRepository.findAllByTipo(veiculo.getId());
    }

    public void setListDespesasTipo(List<GraficoDesp> listDespesasTipo) {
        this.listDespesasTipo = listDespesasTipo;
    }

    public List<Despesas> getListDespesasUser() {
        return despesasRepository.findByUsuario(usuario.getId());
    }

    public Double getValorTotal(Long id) {
        if (id != null) {
            veiculo = veiculosRepository.findOne(id);
            return FormatUtils.formatDouble(despesasRepository.findValorTotal(veiculo.getId()));
        } else {
            return (double) 0;
        }
    }

}
