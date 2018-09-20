package br.edu.utfpr.pb.controleveiculo.converter;

import br.edu.utfpr.pb.controleveiculo.model.Veiculo;
import br.edu.utfpr.pb.controleveiculo.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@Component("veiculoConverter")
public class VeiculoConverter
        implements Converter {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
                              String id) {
        if (id != null) {
            return veiculoRepository.findOne(Long.valueOf((id)));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
                              Object object) {
        return ((Veiculo) object).getId().toString();
    }

}
