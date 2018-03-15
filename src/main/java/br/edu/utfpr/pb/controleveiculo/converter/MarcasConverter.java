package br.edu.utfpr.pb.controleveiculo.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.utfpr.pb.controleveiculo.model.Marcas;
import br.edu.utfpr.pb.controleveiculo.repository.MarcasRepository;

@Component("marcasConverter")
public class MarcasConverter 
	implements Converter{

	@Autowired
	private MarcasRepository marcasRepository;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, 
			String arg2) {
		if (arg2 != null) {
			return marcasRepository.findOne(Long.valueOf(arg2));
		}
		return null;
	}
	
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1,
			Object arg2) {
		if (arg2 != null && arg2 instanceof Marcas) {
			return ((Marcas) arg2).getId().toString();
		}
		return null;
	}

}
