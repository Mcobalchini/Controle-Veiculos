package br.edu.utfpr.pb.controleveiculo.converter;

import br.edu.utfpr.pb.controleveiculo.model.Usuario;
import br.edu.utfpr.pb.controleveiculo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@Component("usuarioConverter")
public class UsuarioConverter
        implements Converter {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
                              String id) {
        if (id != null) {
            return usuarioRepository.findOne(Long.valueOf((id)));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
                              Object object) {
        return ((Usuario) object).getId().toString();
    }

}
