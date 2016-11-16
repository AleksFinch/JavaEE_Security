package mangedbeans;

import instances.Adress;
import instances.Car;
import jpadao.JPADAOAdress;
import jpadao.JPADAOBrand;
import jpadao.JPADAOCar;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.util.List;

/**
 * Created by Olexandr Finchuk on 01.11.2016.
 */
@ManagedBean(name = "adressBean", eager = true )
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@ViewScoped

public class AdressBean {

    List<Adress> adresses ;
    @EJB
    JPADAOAdress jpadaoAdress;
    @PostConstruct
    public void init(){
        adresses = jpadaoAdress.getAllObjects();
    }
    public List<Adress> getAdresss() {
        return adresses;

    }

    public String deleteAdress(Adress adress) {

        jpadaoAdress.deleteObject(adress);
        adresses = jpadaoAdress.getAllObjects();
        return null;
    }

    public String editAdress(Adress adress) {

        adress.setEditable(true);
        return null;
    }

    public String saveAdresss() {

        List<Adress> adresss = jpadaoAdress.getAllObjects();

        for (Adress adress :
                adresss) {
            if (adress.isEditable()) {
                adress.setEditable(false);
                jpadaoAdress.updateObject(adress);
            }

        }
        adresses = jpadaoAdress.getAllObjects();
        return null;
    }



    public String addNewAdress(){
        Adress adress = new Adress();
        adress.setEditable(true);
        jpadaoAdress.addObject(adress );
        adresses = jpadaoAdress.getAllObjects();
        return null;
    }
}
