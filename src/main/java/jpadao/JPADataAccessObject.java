package jpadao;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.ArrayList;

/**
 * Class to present data access
 * @author Finchuk Olexander
 * @version 1.0
 * @since 10.06.16.
 */

public abstract class JPADataAccessObject<T> {

    @PersistenceContext(unitName = "Lab3")
    protected EntityManager manager ;
    /**
     * Method which returns List of Objects by condition
     * @param condition-condition
     * @return List of object from database
     */
    public abstract ArrayList<T> getObjectsByCondition(String condition);

    /**
     * Method which returns full list of Objects
     * @return List of object from database
     */
    public abstract ArrayList<T> getAllObjects();

    /**
     * Method which returns object by id
     * @param id - id in database
     * @return object from database
     */
    public abstract T getObjectById(int id);

    /**
     * Method which adds Object in database
     * @param obj - object for adding
     */
    public  void addObject(T obj){

        manager.persist(obj);

    }

    /**
     * Method which update data in database

     * @param obj new data
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateObject( T obj){
        manager.merge(obj);
    }

    /**
     * Method which delete obj
     * @param o - elenent
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteObject(T o) {

        manager.remove(manager.contains(o)?o:manager.merge(o));

    }

    /**
     * Method which delete obj by id
     * @param id - elenent id
     */
    public abstract void deleteObjectById(int id);

    /**
     * Method which delete all data from table
     */
    public abstract void deleteAll();
}
