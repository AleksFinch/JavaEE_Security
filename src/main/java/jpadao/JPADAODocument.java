package jpadao;




import instances.Document;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.print.Doc;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Olexandr Finchuk on 19.10.2016.
 */
@Stateless
public class JPADAODocument extends JPADataAccessObject<Document> {


    public JPADAODocument() {

    }
    @Override
    public ArrayList<Document> getObjectsByCondition(String condition){

        Query query =manager.createQuery("SELECT d from Document d WHERE "+ condition);
        return (ArrayList<Document>) query.getResultList();
    }

    @Override
    public ArrayList<Document> getAllObjects() {
        return (ArrayList<Document>) manager.createNamedQuery("Document.getAll").getResultList();
    }


    @Override
    public Document getObjectById(int id){

        return manager.find(Document.class,id);
    }


    @Override
    public void deleteObjectById(int id){

        Query q = manager.createNamedQuery("Document.deleteById");
        q.setParameter("id",id);
        q.executeUpdate();

    }

    @Override
    public void deleteAll(){

        manager.createQuery("DELETE FROM Document m").executeUpdate();

    }
}
