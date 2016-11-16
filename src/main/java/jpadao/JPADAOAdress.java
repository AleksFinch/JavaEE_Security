package jpadao;

import instances.Adress;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;

/**
 * Created by Olexandr Finchuk on 19.10.2016.
 */
@Stateless
public class JPADAOAdress extends JPADataAccessObject<Adress> {



    public JPADAOAdress() {

    }
    @Override
    public ArrayList<Adress> getObjectsByCondition(String condition){

        Query query =manager.createQuery("SELECT b from Adress b WHERE "+ condition);
        return (ArrayList<Adress>) query.getResultList();
    }


    @Override
    public Adress getObjectById(int id){

        return manager.find(Adress.class,id);
    }


    @Override
    public void deleteObjectById(int id){

        Query q = manager.createNamedQuery("Adress.deleteById");
        q.setParameter("id",id);
        q.executeUpdate();

    }

    @Override
    public void deleteAll(){
        if(!manager.getTransaction().isActive()){
            manager.getTransaction().begin();
        }
        manager.createQuery("DELETE FROM Adress b").executeUpdate();
        manager.getTransaction().commit();
    }
    @Override
    public ArrayList<Adress> getAllObjects() {
        return (ArrayList<Adress>) manager.createNamedQuery("Adress.getAll").getResultList();
    }
}
