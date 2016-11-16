package mangedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * Created by Olexandr Finchuk on 15.11.2016.
 */
@ManagedBean
@SessionScoped
public class UserManager {

    public String logOut(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/hidden_pages/index.xhtml?faces-redirect=true";
    }
}
