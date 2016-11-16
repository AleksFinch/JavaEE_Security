package instances;

import javax.persistence.*;

/**
 * Class for present Brand
 * @author Finchuk Olexander
 * @version 1.0
 * @since 10.06.16.
 */
@Entity
@Table(name = "brand")
@NamedQueries({
        @NamedQuery(name = "Brand.getAll", query = "SELECT b from Brand b"),
        @NamedQuery(name = "Brand.deleteById", query = "DELETE from Brand b WHERE b.brand_id = :id"),
        @NamedQuery(name = "Brand.deleteAll", query = "DELETE from Brand b")
})
public class Brand {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    int brand_id;
    @Column(name = "brand_name")
    String brand_name;
    @Column(name = "foundation_year")
    int foundation_year;
    @Column(name = "country")
    String country;

    @Transient
    private boolean editable;

    public Brand() {
        editable =false;
    }

    public Brand(int brand_id, String brand_name, int foundation_year, String country) {

        this.brand_id = brand_id;
        this.brand_name = brand_name;
        this.foundation_year = foundation_year;
        this.country = country;
        editable = false;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }


    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public int getFoundation_year() {
        return foundation_year;
    }

    public void setFoundation_year(int foundation_year) {
        this.foundation_year = foundation_year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if(o==null)
            return false;
        if(o.getClass()!= this.getClass())
            return false;
        Brand br = (Brand) o;

        if(br.brand_id!=this.brand_id)
            return false;
        if(br.brand_name.compareTo(this.brand_name)!=0)
            return false;

        if(br.foundation_year!=this.foundation_year)
            return false;
        if(br.country.compareTo(this.country)!=0)
            return false;
        return true;
    }
}
