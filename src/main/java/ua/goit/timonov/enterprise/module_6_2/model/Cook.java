package ua.goit.timonov.enterprise.module_6_2.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by Alex on 18.08.2016.
 */
@Entity
public class Cook extends Employee {

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cook")
    @Fetch(FetchMode.SELECT)
    private List<CookedDish> cookedDishes;

    public List<CookedDish> getCookedDishes() {
        return cookedDishes;
    }

    public void setCookedDishes(List<CookedDish> cookedDishes) {
        this.cookedDishes = cookedDishes;
    }

    @Override
    public String toString() {
        return "Cook{" + super.toString() +
                " cookedDishes=" + cookedDishes +
                "} " ;
    }
}
