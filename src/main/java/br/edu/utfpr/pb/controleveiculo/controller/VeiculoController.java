package br.edu.utfpr.pb.controleveiculo.controller;

import br.edu.utfpr.pb.controleveiculo.model.Abastecimentos;
import br.edu.utfpr.pb.controleveiculo.model.Modelo;
import br.edu.utfpr.pb.controleveiculo.model.Usuario;
import br.edu.utfpr.pb.controleveiculo.model.Veiculo;
import br.edu.utfpr.pb.controleveiculo.repository.AbastecimentosRepository;
import br.edu.utfpr.pb.controleveiculo.repository.ModeloRepository;
import br.edu.utfpr.pb.controleveiculo.repository.UsuarioRepository;
import br.edu.utfpr.pb.controleveiculo.repository.VeiculoRepository;
import br.edu.utfpr.pb.controleveiculo.session.SessionUtil;
import lombok.Getter;
import lombok.Setter;
import net.bootsfaces.utils.FacesMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

@Controller("veiculoController")
@Scope("view")
public class VeiculoController {

    @Getter
    @Setter
    private Veiculo veiculo;
    @Getter
    @Setter
    private Usuario usuario;
    @Getter
    @Setter
    private List<Modelo> listModelo;
    @Autowired
    private ModeloRepository modeloRepository;
    @Getter
    @Setter
    private List<Usuario> listUsuario;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AbastecimentosRepository abastecimentosRepository;
    @Getter
    @Setter
    private List<Veiculo> veiculos;
    @Getter
    @Setter
    private List<Veiculo> veiculosUsuario;
    @Autowired
    private VeiculoRepository veiculoRepository;

    @PostConstruct
    public void init() {
        usuario = (Usuario) SessionUtil.getParam("USUARIOLogado");
        veiculo = new Veiculo();
        popularLista();
    }

    private void popularLista() {
        this.setVeiculos(veiculoRepository.findAll());
        this.setListModelo(modeloRepository.findAll());
        this.setListUsuario(usuarioRepository.findAll());
        this.setVeiculosUsuario(veiculoRepository.findByUsuario(usuario.getId()));
    }

    public void salvar() {
        veiculo.setUsuario(usuario);
        veiculoRepository.save(veiculo);
        FacesMessages.info("Registro salvo com sucesso!!!");
        popularLista();
        novo();
    }

    public void novo() {
        veiculo = new Veiculo();
    }

    public void editar(Veiculo veiculo) {
        this.veiculo = veiculo;
    }


    public void excluir(Long id) {
        veiculoRepository.delete(id);
        popularLista();
    }

    public String calibrarPneus(Long id) {
        veiculo = veiculoRepository.findOne(id);
        veiculo.setUltimaCalibragem(new Date()); //Seta a data de hoje como ultmia calibragem
        salvar();
        return "veiculos.jsf";
    }

    public void trocarPneus(Long id) {
        veiculo = veiculoRepository.findOne(id);
        veiculo.setKmPneus(0);
        salvar();
    }

    public void recalcularKmLitro(Long id) {
        veiculo = veiculoRepository.findOne(id);
        Abastecimentos abastecimento = pegarUltimoAbastecimento(veiculo.getId());
        veiculo.setLitragem((veiculo.getHodometroAtual() - veiculo.getHodometroAnterior()) / abastecimento.getLitros());
        salvar();
    }

    public Abastecimentos pegarUltimoAbastecimento(Long id) {
        return abastecimentosRepository.findFirst1ByVeiculoIdOrderByIdDesc(id);
    }
}












