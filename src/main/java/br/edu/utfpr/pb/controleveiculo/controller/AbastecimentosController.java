package br.edu.utfpr.pb.controleveiculo.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.edu.utfpr.pb.controleveiculo.model.Abastecimentos;
import br.edu.utfpr.pb.controleveiculo.model.Despesas;
import br.edu.utfpr.pb.controleveiculo.model.Tipo;
import br.edu.utfpr.pb.controleveiculo.model.Usuario;
import br.edu.utfpr.pb.controleveiculo.model.Veiculo;
import br.edu.utfpr.pb.controleveiculo.repository.AbastecimentosRepository	;
import br.edu.utfpr.pb.controleveiculo.repository.DespesasRepository;
import br.edu.utfpr.pb.controleveiculo.repository.TipoRepository;
import br.edu.utfpr.pb.controleveiculo.repository.UsuarioRepository;
import br.edu.utfpr.pb.controleveiculo.repository.VeiculoRepository;
import br.edu.utfpr.pb.controleveiculo.session.SessionUtil;
import lombok.Getter;
import lombok.Setter;
import net.bootsfaces.utils.FacesMessages;

@Controller("abastecimentosController")
@Scope("view")
public class AbastecimentosController {
	Date date = new Date();
	@Getter @Setter
	private Abastecimentos abastecimentos;	
	@Getter @Setter
	private Veiculo veiculo;	
	@Getter @Setter
	private Despesas despesa;
	@Autowired
	private UsuarioRepository usuariosRepository;	
	@Autowired
	private VeiculoRepository veiculosRepository;	
	@Autowired
	private DespesasRepository despesasRepository;	
	@Autowired
	private TipoRepository tipoRepository;	
	@Getter @Setter
	private List<Veiculo> listVeiculos;
	@Getter @Setter
	private List<Usuario> listUsuarios;
	@Getter @Setter
	private List<Abastecimentos> listAbastecimentos;
	@Autowired
	private AbastecimentosRepository abastecimentosRepository;
	

	@PostConstruct
	public void init() {
		date = new Date();		
		abastecimentos = new Abastecimentos();
		veiculo = abastecimentos.getVeiculo();
		abastecimentos.setData(date);
		popularLista();
	}
	
	private void popularLista() {
		this.setListAbastecimentos(abastecimentosRepository.findAll());
		this.setListVeiculos(veiculosRepository.findAll());
		this.setListUsuarios(usuariosRepository.findAll());
	}

	public void salvar() {		
		Tipo tipo = new Tipo();		
		try{
			despesa = despesasRepository.findByAbastecimento((long)abastecimentos.getId());
		}
		catch(NullPointerException e){
			despesa = new Despesas();
		}
		abastecimentos.setUsuario((Usuario) SessionUtil.getParam("USUARIOLogado")); 

		veiculosRepository.save(veiculo);
		abastecimentosRepository.save(abastecimentos);
		despesa.setDescricao("Despesa ref. abastecimento: "+abastecimentos.getPosto());
		despesa.setStatus(true);
		despesa.setTipo(tipo = tipoRepository.findOne((long) 3));		
		despesa.setValor(abastecimentos.getValorPago());
		despesa.setVeiculo(abastecimentos.getVeiculo());
		despesa.setUsuario(abastecimentos.getUsuario());
		despesa.setAbastecimento(abastecimentos); //Informar que é referente a esse abastecimento
		despesa.setMulta(null);
		despesasRepository.save(despesa);		
		FacesMessages.info("Registro salvo com sucesso!!!");
		popularLista();
		novo();
	}
	
	public void novo() {
		abastecimentos = new Abastecimentos();
	}
	
	public void editar(Abastecimentos abastecimentos) {
		this.abastecimentos = abastecimentos;
	}
	
	
	public List<Veiculo> getVeiculos(){
		return veiculosRepository.findAll();
	}
	
	public void excluir(Long id) {
		despesa = despesasRepository.findByAbastecimento(id);
		despesasRepository.delete(despesa);
		abastecimentosRepository.delete(id);
		
		FacesMessages.info("Registro Removido");
		popularLista();
	}
	
	public void calcular(){	
		double  vlrPago,vlrLitro,qtdLitros,km_carro,percorrer,percorrido,km_Pneus = 0; //inicializa variaveis
		veiculo = abastecimentos.getVeiculo();
		km_carro = veiculo.getLitragem(); //km_carro é uma variavel que pega a litragem do veiculo cadastrada
		km_Pneus = veiculo.getKmPneus(); //Recupera a ultima kilometragem do pneu
		percorrer = veiculo.getKmapercorrer(); 
		percorrido = veiculo.getKmpercorrido();	
		vlrLitro = abastecimentos.getValorLitro();		 // valor do litro de combustivo em tela
		vlrPago = abastecimentos.getValorPago(); // mesma coisa		
		qtdLitros = vlrPago/vlrLitro; 
		percorrido += percorrer;
		km_Pneus += percorrido;
		percorrer = (qtdLitros * km_carro);		
		veiculo.setKmPneus(km_Pneus);		
		veiculo.setKmapercorrer(percorrer);
		veiculo.setKmpercorrido(percorrido);	
		System.out.println(veiculo.getId() + " objeto");					
		abastecimentos.setLitros(qtdLitros);  						
	}
}
