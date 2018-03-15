package br.edu.utfpr.pb.controleveiculo.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.utfpr.pb.controleveiculo.model.Tipo;
import br.edu.utfpr.pb.controleveiculo.repository.TipoRepository;

@Component("tipoConverter")
public class TipoConverter 
	implements Converter{

	@Autowired
	private TipoRepository tipoRepository;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, 
			String arg2) {
		if (arg2 != null) {
			return tipoRepository.findOne(Long.valueOf(arg2));
		}
		return null;
	}
	
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1,
			Object arg2) {
		if (arg2 != null && arg2 instanceof Tipo) {
			return ((Tipo) arg2).getId().toString();
		}
		return null;
	}

}
