package br.edu.utfpr.pb.controleveiculo.converter;

import br.edu.utfpr.pb.controleveiculo.model.Agendamentos;
import br.edu.utfpr.pb.controleveiculo.repository.AgendamentosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@Component("agendamentosConverter")
public class AgendamentosConverter
        implements Converter {

    @Autowired
    private AgendamentosRepository agendamentosRepository;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
                              String id) {
        if (id != null) {
            return agendamentosRepository.findOne(Long.valueOf(id));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
                              Object object) {
        return ((Agendamentos) object).getId().toString();
    }

}
