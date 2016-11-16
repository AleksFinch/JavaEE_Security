package mangedbeans;

import instances.Document;
import jpadao.JPADAODocument;

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
@ManagedBean(name = "documentBean", eager = true )
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@ViewScoped
public class DocumentBean {

    @EJB
    JPADAODocument jpadaoDocument;
    List<Document> documents;
    @PostConstruct
    public void init(){
        documents = jpadaoDocument.getAllObjects();
    }
    public List<Document> getDocuments() {
        return documents;

    }


    public String deleteDocument(Document document) {

        jpadaoDocument.deleteObject(document);
        documents = jpadaoDocument.getAllObjects();
        return null;
    }

    public String editDocument(Document document) {

        document.setEditable(true);
        return null;
    }

    public String saveDocuments() {

        List<Document> documents = jpadaoDocument.getAllObjects();

        for (Document document :
                documents) {
            if (document.isEditable()) {

                document.setEditable(false);
                jpadaoDocument.updateObject(document);
            }
        }
        this.documents = jpadaoDocument.getAllObjects();
        return null;
    }



    public String addNewDocument(){
        Document document = new Document();
        document.setEditable(true);
        jpadaoDocument.addObject(document );
        documents = jpadaoDocument.getAllObjects();
        return null;
    }
}
