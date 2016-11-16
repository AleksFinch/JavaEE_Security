package converters;

import instances.Document;
import jpadao.JPADAODocument;
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
public class DocumentConverter implements Converter {
    final static Logger LOGGER = Logger.getLogger(DocumentConverter.class);
    @EJB
    JPADAODocument jpadaoDocument;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        return jpadaoDocument.getObjectById(Integer.parseInt(s));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return String.valueOf(((Document)o).getDocument_id());
    }
}
