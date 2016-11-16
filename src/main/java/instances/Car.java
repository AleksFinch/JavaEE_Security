package instances;

import org.apache.log4j.Logger;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Class for present Car
 * @author Finchuk Olexander
 * @version 1.0
 * @since 10.04.16.
 */

@Entity
@Table(name = "cars")

@NamedQueries({
        @NamedQuery(name = "Car.getAll", query = "SELECT c from Car c"),
        @NamedQuery(name = "Car.deleteById", query = "DELETE from Car c WHERE c.car_id = :id"),
        @NamedQuery(name = "Car.deleteAll", query = "DELETE from Car c")
})
public class Car{
    final static Logger LOGGER = Logger.getLogger(Car.class);


    private int car_id;


    private Brand brand;

    private String model;

    private Document document;

    private int release_date;

    private int pass_way;

    private float engine_capacity;

    private String color;

    @Transient
    public boolean isEditable() {
        return editable;
    }
    @Transient
    public void setEditable(boolean editable) {
        this.editable = editable;
    }
    @Transient
    private boolean editable;
    private Collection<Owner> owners ;







    public Car() {
        editable = false;
        owners = new ArrayList<Owner>();
    }

    public Car(int car_id, Brand brand, String model, Document document, int release_date, int pass_way, float engine_capacity, String color, Collection<Owner> owners) {
        this.car_id = car_id;
        this.brand = brand;
        this.model = model;
        this.document = document;
        this.release_date = release_date;
        this.pass_way = pass_way;
        this.engine_capacity = engine_capacity;
        this.color = color;
        this.owners = owners;
        editable = false;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name ="car_id")
    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
    @Column(name ="model")
    public String getModel() {

        return model;
    }

    public void setModel(String model) {

        this.model = model;
    }
    @OneToOne
    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
    @Column(name ="release_date")
    public int getRelease_date() {
        return release_date;
    }

    public void setRelease_date(int release_date) {
        this.release_date = release_date;
    }
    @Column(name ="pass_way")
    public int getPass_way() {
        return pass_way;
    }

    public void setPass_way(int pass_way) {
        this.pass_way = pass_way;
    }
    @Column(name ="engine_capacity")
    public float getEngine_capacity() {
        return engine_capacity;
    }

    public void setEngine_capacity(float engine_capacity) {
        this.engine_capacity = engine_capacity;
    }
    @Column(name ="color")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL )
    @JoinTable(name="car_owner",
            joinColumns=@JoinColumn(name="car_id"),
            inverseJoinColumns=@JoinColumn(name="owner_id"))
    public Collection<Owner> getOwners() {

        return owners;
    }
    public void setOwners(Collection<Owner> owners) {

        this.owners = owners;
    }

    @Override
    public boolean equals(Object o) {

        if(o==null) return false;
        if(this == o) return true;
        if(o.getClass()!=this.getClass()) return false;
        Car car = (Car) o;
        if(car_id!=car.getCar_id())  return false;
        else if(!brand.equals(car.getBrand()))  return false;
        else if(model.compareTo(car.getModel())!=0)  return false;
        else if(!document.equals(car.getDocument()))  return false;
        else if(release_date!=car.getRelease_date())  return false;
        else if(pass_way!=car.getPass_way())  return false;
        else if(engine_capacity!=car.getEngine_capacity()) return false;
        else if(color.compareTo(car.getColor())!=0)  return false;
        else return true;


    }

}
