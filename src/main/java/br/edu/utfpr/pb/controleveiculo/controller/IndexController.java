package br.edu.utfpr.pb.controleveiculo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.edu.utfpr.pb.controleveiculo.model.Usuario;
import br.edu.utfpr.pb.controleveiculo.model.Veiculo;
import br.edu.utfpr.pb.controleveiculo.repository.VeiculoRepository;
import br.edu.utfpr.pb.controleveiculo.session.SessionUtil;
import lombok.Getter;
import lombok.Setter;

@Controller("indexController")
@Scope("view")
public class IndexController {
	@Getter @Setter
	private ArrayList<String> messages;
	@Getter @Setter
	private String mensagem;
	@Getter @Setter
	private String mensagemTratada;
	@Getter @Setter
	private Veiculo veiculo;
	@Getter @Setter
	private List<Veiculo> veiculos;
	@Autowired
	private VeiculoRepository veiculoRepository;
	@Getter @Setter
	private Usuario usuario;
	
	@PostConstruct
	public void init() {
		usuario = (Usuario) SessionUtil.getParam("USUARIOLogado");
		this.setVeiculos(veiculoRepository.findByUsuario(usuario.getId()));
		messages = new ArrayList<>();	
		for (Veiculo v: veiculos){					
			calcCalibragem(v);			
		}			
		
	}
	
	public void calcCalibragem(Veiculo veiculo){
		System.out.println(veiculo.getKmPneus());
		if (veiculo.getKmPneus() >= 20000){			
			mensagem = "Troque os pneus do veículo "+veiculo.getNome();			
			messages.add(mensagem);	
		}
		retornaDias(veiculo);
						
	}
	
	public void retornaDias(Veiculo veiculo){
		DateTime data = new DateTime(veiculo.getUltimaCalibragem()); //Ultima calibrada
		DateTime hastoCalibrar = new DateTime(data.plusDays(20)); //Calibrar 20 depois da ultima
		DateTime hoje = new DateTime(); //hoje man
		Days diasRestantes = Days.daysBetween(hoje,hastoCalibrar);			
		if(diasRestantes.getDays() <= 0){			
			messages.add("Calibre os pneus do veículo "+veiculo.getNome());			
		}
				
	}
	
	
}
