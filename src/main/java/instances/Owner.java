package instances;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Olexandr Finchuk on 19.10.2016.
 */

@Entity
@NamedQueries({
        @NamedQuery(name = "Owner.getAll", query = "SELECT o from Owner o"),
        @NamedQuery(name = "Owner.deleteById", query = "DELETE from Owner o WHERE o.owner_id = :id"),
        @NamedQuery(name = "Owner.deleteAll", query = "DELETE from Owner o")
})
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int owner_id;

    @Column
    private String last_name;

    @Column
    private String first_name;

    @OneToOne(fetch = FetchType.EAGER)
    private Adress adress;
    @ManyToMany(mappedBy = "owners", fetch = FetchType.EAGER)
    private Collection<Car> cars;

    @Transient
    private boolean editable;
    @Transient
    private Collection<Car> forgettedCars;
    public Owner() {
        cars = new ArrayList<>();
        forgettedCars = new ArrayList<>();
    }

    public Owner(int owner_id, String first_name, String last_name, Adress adress, Collection<Car> cars) {
        this.owner_id = owner_id;
        this.last_name = last_name;
        this.first_name = first_name;
        this.adress = adress;
        this.cars = cars;
        forgettedCars = new ArrayList<>();
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public Collection<Car> getCars() {
        return cars;
    }

    public void setCars(Collection<Car> cars) {
        this.forgettedCars = this.cars;
        forgettedCars.removeAll(cars);
        for (Car car:
             this.cars) {
            car.getOwners().remove(this);
        }

        for (Car car:
                cars) {
            car.getOwners().remove(this);
        }
        this.cars = cars;
        for (Car car:
                this.cars) {
            car.getOwners().add(this);
        }
    }

    public Collection<Car> getForgettedCars() {
        return forgettedCars;
    }

    public void setForgettedCars(Collection<Car> forgettedCars) {
        this.forgettedCars = forgettedCars;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o.getClass() != this.getClass())
            return false;
        Owner ow = (Owner) o;

        if (ow.owner_id != this.owner_id)
            return false;
        if (ow.last_name.compareTo(this.last_name) != 0)
            return false;

        if (ow.first_name.compareTo(this.first_name) != 0)
            return false;
        if (!ow.adress.equals(this.adress))
            return false;
        return true;
    }
}
