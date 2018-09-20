package br.edu.utfpr.pb.controleveiculo.converter;

import br.edu.utfpr.pb.controleveiculo.model.Modelo;
import br.edu.utfpr.pb.controleveiculo.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@Component("modeloConverter")
public class ModeloConverter
        implements Converter {

    @Autowired
    private ModeloRepository modeloRepository;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
                              String id) {
        if (id != null) {
            return modeloRepository.findOne(Long.valueOf((id)));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
                              Object object) {
        return ((Modelo) object).getId().toString();
    }

}
