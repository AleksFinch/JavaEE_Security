package instances;

import javax.persistence.*;

/**
 * Created by Olexandr Finchuk on 19.10.2016.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Adress.getAll", query = "SELECT a from Adress a"),
        @NamedQuery(name = "Adress.deleteById", query = "DELETE from Adress a WHERE a.adress_id = :id"),
        @NamedQuery(name = "Adress.deleteAll", query = "DELETE from Adress a")
})
public class Adress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int adress_id;
    @Column
    private String country;
    @Column
    private String town;
    @Column
    private String street;

    @Transient
    private boolean editable;


    public Adress(){

    }
    public Adress(int adress_id,String country, String town, String street, int number) {
        this.adress_id = adress_id;
        this.country = country;
        this.town = town;
        this.street = street;
        this.number = number;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public int getAdress_id() {
        return adress_id;
    }

    public void setAdress_id(int adress_Id) {
        this.adress_id = adress_Id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Column
    private int number;

    @Override
    public boolean equals(Object o) {
        if(o==null)
            return false;
        if(o.getClass()!= this.getClass())
            return false;
        Adress adr = (Adress) o;

        if(adr.adress_id!=this.adress_id)
            return false;
        if(adr.street.compareTo(this.street)!=0)
            return false;

        if(adr.town.compareTo(this.town)!=0)
            return false;
        if(adr.number!=this.number)
            return false;
        if(adr.country.compareTo(this.country)!=0)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return this.country + ", " + this.town + ", " + this.street + ", " + this.number;
    }
}
