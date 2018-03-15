package br.edu.utfpr.pb.controleveiculo.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.utfpr.pb.controleveiculo.model.Usuario;
import br.edu.utfpr.pb.controleveiculo.repository.UsuarioRepository;

@Component("usuarioConverter")
public class UsuarioConverter 
	implements Converter{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, 
			String arg2) {
		if (arg2 != null) {
			return usuarioRepository.findOne(Long.valueOf(arg2));
		}
		return null;
	}
	
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1,
			Object arg2) {
		if (arg2 != null && arg2 instanceof Usuario) {
			return ((Usuario) arg2).getNome().toString();
		}
		return null;
	}

}
