package jpadao;


import instances.Car;
import instances.Document;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * Created by Olexandr Finchuk on 19.10.2016.
 */
@Stateless

public class JPADAOCar extends JPADataAccessObject<Car> {


    public JPADAOCar() {
    }
    @Override
    public ArrayList<Car> getObjectsByCondition(String condition){

        Query query =manager.createQuery("SELECT c from Car c WHERE "+ condition);
        return (ArrayList<Car>) query.getResultList();
    }


    @Override
    public Car getObjectById(int id){

        return manager.find(Car.class,id);
    }



    @Override
    public void deleteObjectById(int id){

        Query q = manager.createNamedQuery("Car.deleteById");
        q.setParameter("id",id);
        q.executeUpdate();

    }

    @Override
    public void deleteAll(){

        manager.createQuery("DELETE FROM Car c").executeUpdate();

    }
    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public ArrayList<Car> getAllObjects() {
        return (ArrayList<Car>) manager.createNamedQuery("Car.getAll").getResultList();
    }
}
