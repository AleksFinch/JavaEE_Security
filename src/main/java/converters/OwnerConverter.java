package converters;

import instances.Brand;
import instances.Owner;
import jpadao.JPADAOBrand;
import jpadao.JPADAOCar;
import jpadao.JPADAOOwner;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
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
public class OwnerConverter implements Converter {
    final static Logger LOGGER = Logger.getLogger(OwnerConverter.class);

    @EJB
    JPADAOOwner jpadaoOwner;


    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        return jpadaoOwner.getObjectById(Integer.parseInt(s));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return String.valueOf(((Owner)o).getOwner_id());
    }
}
