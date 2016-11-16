package mangedbeans;

import instances.Brand;
import jpadao.JPADAOBrand;

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
@ManagedBean(name = "brandBean", eager = true )
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@ViewScoped
public class BrandBean{

    @EJB
    JPADAOBrand jpadaoBrand;
    List<Brand> brands;
    @PostConstruct
    public void init(){
        brands = jpadaoBrand.getAllObjects();
    }
    public List<Brand> getBrands() {
        return brands;

    }

    public String deleteBrand(Brand brand) {

        jpadaoBrand.deleteObject(brand);
        brands = jpadaoBrand.getAllObjects();
        return null;
    }

    public String editBrand(Brand brand) {


        brand.setEditable(true);
        return null;
    }

    public String saveBrands() {

        for (Brand brand :
                brands) {
            if (brand.isEditable()) {
                brand.setEditable(false);
                jpadaoBrand.updateObject(brand);
            }

        }
        brands = jpadaoBrand.getAllObjects();
        return null;
    }

    public String addNewBrand(){
        Brand brand = new Brand();
        brand.setEditable(true);
        jpadaoBrand.addObject(brand );
        brands = jpadaoBrand.getAllObjects();
        return null;
    }

}
