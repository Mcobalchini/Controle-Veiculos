package br.edu.utfpr.pb.controleveiculo.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.edu.utfpr.pb.controleveiculo.model.Marcas;
import br.edu.utfpr.pb.controleveiculo.model.Modelo;
import br.edu.utfpr.pb.controleveiculo.repository.MarcasRepository;
import br.edu.utfpr.pb.controleveiculo.repository.ModeloRepository;
import lombok.Getter;
import lombok.Setter;
import net.bootsfaces.utils.FacesMessages;

@Controller("modeloController")
@Scope("view")
public class ModeloController {

	@Getter @Setter
	private Modelo modelo;
	@Getter @Setter
	private List<Marcas> listMarcas;
	@Autowired
	private MarcasRepository marcasRepository;
	@Getter @Setter
	private List<Modelo> modelos;
	@Autowired
	private ModeloRepository modeloRepository;
	
	@PostConstruct
	public void init() {
		modelo = new Modelo();
		popularLista();
	}
	
	private void popularLista() {
		this.setModelos(modeloRepository.findAll());
		this.setListMarcas(marcasRepository.findAll());
	}
	
	public void salvar() {
		modeloRepository.save(modelo);		
		FacesMessages.info("Registro salvo com sucesso!!!");		
		popularLista();
		novo();
	}
	
	public void novo() {
		modelo = new Modelo();
	}
	
	public void editar(Modelo modelo) {
		this.modelo = modelo;
	}
	
	public List<Marcas> getMarcas(){
		return marcasRepository.findAll();
	}
	
	public void excluir(Long id) {
		modeloRepository.delete(id);
		popularLista();
	}
}












