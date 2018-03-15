package br.edu.utfpr.pb.controleveiculo.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.edu.utfpr.pb.controleveiculo.model.Despesas;
import br.edu.utfpr.pb.controleveiculo.model.Multas;
import br.edu.utfpr.pb.controleveiculo.model.Tipo;
import br.edu.utfpr.pb.controleveiculo.model.Usuario;
import br.edu.utfpr.pb.controleveiculo.model.Veiculo;
import br.edu.utfpr.pb.controleveiculo.repository.DespesasRepository;
import br.edu.utfpr.pb.controleveiculo.repository.MultasRepository;
import br.edu.utfpr.pb.controleveiculo.repository.TipoRepository;
import br.edu.utfpr.pb.controleveiculo.repository.UsuarioRepository;
import br.edu.utfpr.pb.controleveiculo.repository.VeiculoRepository;
import lombok.Getter;
import lombok.Setter;
import net.bootsfaces.utils.FacesMessages;

@Controller("multasController")
@Scope("view")
public class MultasController {

	@Getter @Setter
	private Multas multas;
	@Getter @Setter
	private Despesas despesa;
	@Getter @Setter
	private List<Multas> listMultas;
	@Autowired
	private MultasRepository multasRepository;
	@Autowired
	private DespesasRepository despesasRepository;
	@Autowired
	private TipoRepository tipoRepository;
	@Autowired
	private VeiculoRepository veiculosRepository;	
	@Getter @Setter
	private List<Veiculo> listVeiculos;
	@Autowired
	private UsuarioRepository usuarioRepository;	
	@Getter @Setter
	private List<Usuario> listUsuarios;
	Date date = new Date();
	@PostConstruct
	public void init() {
		date = new Date();
		multas = new Multas();
		multas.setDataMulta(date);
		multas.setDataVencimento(date);
		popularLista();
	}
	
	private void popularLista() {
		this.setListMultas(multasRepository.findAll());
		this.setListVeiculos(veiculosRepository.findAll());
		this.setListUsuarios(usuarioRepository.findAll());
	}
	
	public void salvar() {
		Tipo tipo = new Tipo();
		
		try{
			despesa = despesasRepository.findByMulta((long)multas.getId());
		}catch(Exception e){
			despesa = new Despesas();
			e.printStackTrace();
		}
		multasRepository.save(multas);
		despesa.setDescricao("Despesa Ref. a Multa: "+multas.getMotivo()+"Codigo: "+multas.getId());
		despesa.setStatus(false);
		despesa.setTipo(tipo = tipoRepository.findOne((long) 2));		
		despesa.setValor(multas.getValor());
		despesa.setVeiculo(multas.getVeiculo());
		despesa.setUsuario(multas.getUsuario());
		despesa.setMulta(multas);
		despesa.setAbastecimento(null);
		despesasRepository.save(despesa);
		FacesMessages.info("Registro salvo com sucesso!!!");
		popularLista();
		novo();
	}
	
	public void novo() {
		multas = new Multas();
	}
	
	public void editar(Multas multas) {
		this.multas = multas;
	}
		
	public List<Veiculo> getVeiculos(){
		return veiculosRepository.findAll();
	}
	
	public List<Usuario> getUsuarios(){
		return usuarioRepository.findAll();
	}
	
	public void excluir(Long id) {
		/*despesa = despesasRepository.findByMulta(id);
		despesasRepository.delete(despesa);*/
		multasRepository.delete(id);
		FacesMessages.info("Registro Removido");
		popularLista();
	}
	
	public void calculaPonto(){
		if(multas.getGravidade().equals("leve")){
			multas.setPontos(3);
		}
		else if (multas.getGravidade().equals("Media")){
			multas.setPontos(4);
		}
		else if (multas.getGravidade().equals("Grave")){
			multas.setPontos(5);
		}
		else if (multas.getGravidade().equals("Gravissima")){
			multas.setPontos(7);
		}
	}
}
