package br.edu.utfpr.pb.controleveiculo.converter;

import br.edu.utfpr.pb.controleveiculo.model.Marcas;
import br.edu.utfpr.pb.controleveiculo.repository.MarcasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@Component("marcasConverter")
public class MarcasConverter
        implements Converter {

    @Autowired
    private MarcasRepository marcasRepository;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
                              String id) {
        if (id != null) {
            return marcasRepository.findOne(Long.valueOf((id)));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
                              Object object) {
        return ((Marcas) object).getId().toString();
    }

}
