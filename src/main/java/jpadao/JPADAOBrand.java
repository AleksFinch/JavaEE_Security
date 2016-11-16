package jpadao;

import instances.Brand;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.ArrayList;

/**
 * Created by Olexandr Finchuk on 19.10.2016.
 */
@Stateless
public class JPADAOBrand extends JPADataAccessObject<Brand> {

    public JPADAOBrand() {
    }
    @Override
    public ArrayList<Brand> getObjectsByCondition(String condition){

        Query query =manager.createQuery("SELECT b from Brand b WHERE "+ condition);
        return (ArrayList<Brand>) query.getResultList();
    }


    @Override
    public Brand getObjectById(int id){

        return manager.find(Brand.class,id);
    }

    @Override
    public void deleteObjectById(int id){

        Query q = manager.createNamedQuery("Brand.deleteById");
        q.setParameter("id",id);
        q.executeUpdate();

    }

    @Override
    public void deleteAll(){
        if(!manager.getTransaction().isActive()){
            manager.getTransaction().begin();
        }
        manager.createQuery("DELETE FROM Brand b").executeUpdate();
        manager.getTransaction().commit();
    }
    @Override
    public ArrayList<Brand> getAllObjects() {
        return (ArrayList<Brand>) manager.createNamedQuery("Brand.getAll").getResultList();
    }
}
