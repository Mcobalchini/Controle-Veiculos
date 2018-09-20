package br.edu.utfpr.pb.controleveiculo.converter;

import br.edu.utfpr.pb.controleveiculo.model.Despesas;
import br.edu.utfpr.pb.controleveiculo.repository.DespesasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@Component("despesasConverter")
public class DespesasConverter
        implements Converter {

    @Autowired
    private DespesasRepository despesasRepository;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
                              String id) {
        if (id != null) {
            return despesasRepository.findOne(Long.valueOf(id));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
                              Object object) {
        return ((Despesas) object).getId().toString();
    }

}
