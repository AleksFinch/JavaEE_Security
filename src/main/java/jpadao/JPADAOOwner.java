package jpadao;

import instances.Owner;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;

/**
 * Created by Olexandr Finchuk on 19.10.2016.
 */
@Stateless
public class JPADAOOwner extends JPADataAccessObject<Owner> {

    public JPADAOOwner() {

    }
    @Override
    public ArrayList<Owner> getObjectsByCondition(String condition){

        Query query =manager.createQuery("SELECT b from Owner b WHERE "+ condition);
        return (ArrayList<Owner>) query.getResultList();
    }


    @Override
    public Owner getObjectById(int id){

        return manager.find(Owner.class,id);
    }



    @Override
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void deleteObjectById(int id){

        Query q = manager.createNamedQuery("Owner.deleteById");
        q.setParameter("id",id);
        q.executeUpdate();

    }

    @Override
    public void deleteAll(){

        manager.createQuery("DELETE FROM Owner b").executeUpdate();

    }
    @Override
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public ArrayList<Owner> getAllObjects() {
        return (ArrayList<Owner>) manager.createNamedQuery("Owner.getAll").getResultList();
    }
}
