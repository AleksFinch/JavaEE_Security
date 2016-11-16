package converters;

import instances.Brand;
import jpadao.JPADAOBrand;
import jpadao.JPADAOCar;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Created by Olexandr Finchuk on 01.11.2016.
 */
@ManagedBean
@SessionScoped
public class BrandConverter implements Converter {
    final static Logger LOGGER = Logger.getLogger(BrandConverter.class);
    @EJB
    JPADAOBrand jpadaoBrand;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        return jpadaoBrand.getObjectById(Integer.parseInt(s));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return String.valueOf(((Brand)o).getBrand_id());
    }
}
