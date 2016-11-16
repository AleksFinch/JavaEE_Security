package mangedbeans;

import instances.Car;
import instances.Owner;
import jpadao.JPADAOCar;
import jpadao.JPADAOOwner;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.transaction.*;
import java.util.List;


/**
 * Created by Olexandr Finchuk on 01.11.2016.
 */
@ManagedBean(name = "ownerBean", eager = true)
@TransactionManagement(value = TransactionManagementType.BEAN)
@ViewScoped
public class OwnerBean {

    final static Logger LOGGER = Logger.getLogger(CarBean.class);

    @EJB
    JPADAOOwner jpadaoOwner;
    @EJB
    JPADAOCar jpadaoCar;

    @Resource
    private UserTransaction userTransaction;
    List<Owner> owners;

    @PostConstruct
    public void init() {
        owners = jpadaoOwner.getAllObjects();
    }


    public List<Owner> getOwners() {

        return owners;

    }


    public String deleteOwner(Owner owner) {
        try {
            userTransaction.begin();
            for (Car car :
                    owner.getCars()) {
                car.getOwners().remove(owner);
                jpadaoCar.updateObject(car);
            }
            jpadaoOwner.deleteObjectById(owner.getOwner_id());
            userTransaction.commit();
            owners = jpadaoOwner.getAllObjects();
        } catch (NotSupportedException | SystemException | HeuristicMixedException |
                HeuristicRollbackException | RollbackException e) {
            try {
                userTransaction.rollback();
            } catch (SystemException e1) {
                LOGGER.error("Error occurred during rollback! " + e1.getMessage());
            }
        }
        return "/hidden_pages/index.xhtml?faces-redirect=true";
    }
    @TransactionAttribute(TransactionAttributeType.NEVER)
    public String editOwner(Owner owner) {

        owner.setEditable(true);
        return null;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String saveOwners() {
        try {
            userTransaction.begin();
            for (Owner owner :
                    owners) {
                jpadaoOwner.updateObject(owner);
                owner.setEditable(false);
                for (Car car :
                        owner.getForgettedCars()) {
                    jpadaoCar.updateObject(car);
                }
                for (Car car :
                        owner.getCars()) {
                    jpadaoCar.updateObject(car);
                }


            }

            userTransaction.commit();
            owners = jpadaoOwner.getAllObjects();
        } catch (NotSupportedException | SystemException | HeuristicMixedException |
                HeuristicRollbackException | RollbackException e) {
            try {
                userTransaction.rollback();
            } catch (SystemException e1) {
                LOGGER.error("Error occurred during rollback! " + e1.getMessage());
            }
        }
        return "/hidden_pages/index.xhtml?faces-redirect=true";
    }


    public String addNewOwner() {
        Owner owner = new Owner();
        owner.setEditable(true);
        jpadaoOwner.addObject(owner);
        owners = jpadaoOwner.getAllObjects();
        return "/hidden_pages/index.xhtml?faces-redirect=true";
    }

//    public void chooseOwnersChanged(ValueChangeEvent e) {
//        try{
//            ArrayList<Car> currCars = (ArrayList<Car>) (e.getNewValue());
//
//
//
//            Owner currElem = null;
//
//            UIComponent tmpComponent = e.getComponent();
//
//            while (null != tmpComponent && !(tmpComponent instanceof UIData)) {
//                tmpComponent = tmpComponent.getParent();
//            }
//
//            if (tmpComponent != null && (tmpComponent instanceof UIData)) {
//                Object tmpRowData = ((UIData) tmpComponent).getRowData();
//
//                if (tmpRowData instanceof Owner) {
//                    currElem = (Owner) tmpRowData;
//
//                    currElem.setManyCars(currCars);
//                }
//            }}
//        catch (Exception ex){
//            LOGGER.error("Wrong", ex);
//        }
//    }

}
