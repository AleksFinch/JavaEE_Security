package mangedbeans;

import instances.Car;
import instances.Owner;
import jpadao.JPADAOCar;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.event.ValueChangeEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Olexandr Finchuk on 01.11.2016.
 */
@ManagedBean(name = "carBean", eager = true )
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@ViewScoped
public class CarBean {
    final static Logger LOGGER = Logger.getLogger(CarBean.class);

    List<Car> cars;
    @EJB
    JPADAOCar jpadaoCar;
    @PostConstruct
    public void Init(){
        cars = jpadaoCar.getAllObjects();
    }

    public List<Car> getCars() {
        return cars;

    }

    public String deleteCar(Car car) {

        jpadaoCar.deleteObject(car);
        cars = jpadaoCar.getAllObjects();
        return "/hidden_pages/index.xhtml?faces-redirect=true";
    }
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public String editCar(Car car) {

        car.setEditable(true);
        return null;
    }

    public String saveCars() {
        LOGGER.info(1);

        for (Car car :
                cars) {
            if (car.isEditable()) {
                LOGGER.info(3);
                car.setEditable(false);
                jpadaoCar.updateObject(car);
            }

        }
        LOGGER.info(3);
        cars = jpadaoCar.getAllObjects();
        return "/hidden_pages/index.xhtml?faces-redirect=true";
    }

//    public void chooseOwnersChanged(ValueChangeEvent e) {
//        try{
//            ArrayList<Owner> owner = (ArrayList<Owner>) (e.getNewValue());
//
//
//
//        Car currElem = null;
//
//        UIComponent tmpComponent = e.getComponent();
//        LOGGER.info(tmpComponent);
//        while (null != tmpComponent && !(tmpComponent instanceof UIData)) {
//            tmpComponent = tmpComponent.getParent();
//        }
//        LOGGER.info(tmpComponent);
//
//        if (tmpComponent != null && (tmpComponent instanceof UIData)) {
//            Object tmpRowData = ((UIData) tmpComponent).getRowData();
//            LOGGER.info(tmpRowData);
//            if (tmpRowData instanceof Car) {
//                currElem = (Car) tmpRowData;
//                LOGGER.info(currElem);
//                currElem.setManyOwners(owner);
//            }
//        }}
//        catch (Exception ex){
//            LOGGER.error("Wrong", ex);
//        }
//    }

    public String addNewCar(){
        Car car = new Car();
        car.setEditable(true);
        jpadaoCar.addObject(car );
        cars = jpadaoCar.getAllObjects();

        return "/hidden_pages/index.xhtml?faces-redirect=true";
    }
}
