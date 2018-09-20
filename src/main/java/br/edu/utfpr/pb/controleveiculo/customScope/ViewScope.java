package br.edu.utfpr.pb.controleveiculo.customScope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.web.context.request.FacesRequestAttributes;

import javax.faces.context.FacesContext;
import java.util.HashMap;
import java.util.Map;


public class ViewScope implements Scope {

    public static final String VIEW_SCOPE_CALLBACKS = "viewScope.callbacks";

    public synchronized Object get(String name, ObjectFactory<?> objectFactory) {
        Object instance = getViewMap().get(name);
        if (instance == null) {
            instance = objectFactory.getObject();
            getViewMap().put(name, instance);
        }
        return instance;
    }

    public Object remove(String name) {
        Object instance = getViewMap().remove(name);
        if (instance != null) {
            @SuppressWarnings("unchecked")
            Map<String, Runnable> callbacks = (Map<String, Runnable>) getViewMap().get(VIEW_SCOPE_CALLBACKS);
            if (callbacks != null) {
                callbacks.remove(name);
            }
        }
        return instance;
    }

    public void registerDestructionCallback(String name, Runnable runnable) {
        @SuppressWarnings("unchecked")
        Map<String, Runnable> callbacks = (Map<String, Runnable>) getViewMap().get(VIEW_SCOPE_CALLBACKS);
        if (callbacks != null) {
            callbacks.put(name, runnable);
        }
    }

    public Object resolveContextualObject(String name) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesRequestAttributes facesRequestAttributes = new FacesRequestAttributes(facesContext);

        return facesRequestAttributes.resolveReference(name);
    }

    public String getConversationId() {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesRequestAttributes facesRequestAttributes = new FacesRequestAttributes(facesContext);

        return facesRequestAttributes.getSessionId() + "-" + facesContext.getViewRoot().getViewId();
    }

    private Map<String, Object> getViewMap() {
        if (FacesContext.getCurrentInstance() != null
                && FacesContext.getCurrentInstance().getViewRoot() != null
                && FacesContext.getCurrentInstance().getViewRoot().getViewMap() != null) {

            return FacesContext.getCurrentInstance().getViewRoot().getViewMap();
        } else {

            return new HashMap<String, Object>();
        }
    }
}
