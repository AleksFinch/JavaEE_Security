package instances;

import javax.persistence.*;
import java.sql.Date;

/**
 * Class for present Document
 * @author Finchuk Olexander
 * @version 1.0
 * @since 10.06.16.
 */
@Entity
@Table(name = "document")
@NamedQueries({
        @NamedQuery(name = "Document.getAll", query = "SELECT d from Document d"),
        @NamedQuery(name = "Document.deleteById", query = "DELETE from Document d WHERE d.document_id = :id"),
        @NamedQuery(name = "Document.deleteAll", query = "DELETE from Document d")
})
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int document_id;
    @Column(name = "owner")
    private String owner;
    @Column(name = "car_number")
    private String car_number;
    @Column(name = "register_date")
    private Date register_date;
    @Transient
    private boolean editable;


    public Document() {
        editable = false;
    }

    public Document(int document_id, String owner, String car_number, Date register_date) {

        this.document_id = document_id;
        this.owner = owner;
        this.car_number = car_number;
        this.register_date = register_date;
        editable = false;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public int getDocument_id() {

        return document_id;
    }

    public void setDocument_id(int document_id) {
        this.document_id = document_id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCar_number() {
        return car_number;
    }

    public void setCar_number(String car_number) {
        this.car_number = car_number;
    }

    public Date getRegister_date() {
        return register_date;
    }

    public void setRegister_date(Date register_date) {
        this.register_date = register_date;
    }

    @Override
    public boolean equals(Object o) {
        if(o==null)
            return false;
        if(o.getClass()!= this.getClass())
            return false;
        Document doc = (Document) o;

        if(doc.document_id!=this.document_id)
            return false;
        if(doc.car_number.compareTo(this.car_number)!=0)
            return false;

        if(doc.owner.compareTo(this.owner)!=0)
            return false;
        if(doc.register_date.compareTo(this.register_date)!=0)
            return false;
        return true;
    }

}
