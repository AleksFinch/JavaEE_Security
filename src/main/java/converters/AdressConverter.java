package converters;

import instances.Adress;
import jpadao.JPADAOAdress;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * Created by Olexandr Finchuk on 01.11.2016.
 */
@ManagedBean
@SessionScoped
public class AdressConverter implements Converter {
    final static Logger LOGGER = Logger.getLogger(AdressConverter.class);
    @EJB
    JPADAOAdress jpadaoAdress;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        return jpadaoAdress.getObjectById(Integer.parseInt(s));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return String.valueOf(((Adress)o).getAdress_id());
    }
}
