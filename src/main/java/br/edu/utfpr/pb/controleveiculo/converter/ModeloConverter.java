package br.edu.utfpr.pb.controleveiculo.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.utfpr.pb.controleveiculo.model.Modelo;
import br.edu.utfpr.pb.controleveiculo.repository.ModeloRepository;

@Component("modeloConverter")
public class ModeloConverter 
	implements Converter{

	@Autowired
	private ModeloRepository modeloRepository;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, 
			String arg2) {
		if (arg2 != null) {
			return modeloRepository.findOne(Long.valueOf(arg2));
		}
		return null;
	}
	
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1,
			Object arg2) {
		if (arg2 != null && arg2 instanceof Modelo) {
			return ((Modelo) arg2).getId().toString();
		}
		return null;
	}

}
