package br.edu.utfpr.pb.controleveiculo.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.utfpr.pb.controleveiculo.model.Veiculo;
import br.edu.utfpr.pb.controleveiculo.repository.VeiculoRepository;

@Component("veiculoConverter")
public class VeiculoConverter 
	implements Converter{

	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, 
			String arg2) {
		if (arg2 != null) {
			return veiculoRepository.findOne(Long.valueOf(arg2));
		}
		return null; 
	}
	
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1,
			Object arg2) {
		if (arg2 != null && arg2 instanceof Veiculo) {
			return ((Veiculo) arg2).getId().toString();
		}
		return null;
	}

}
