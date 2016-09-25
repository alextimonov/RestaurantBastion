package ua.goit.timonov.enterprise.module_6_2.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides dish menu's data
 */
@Entity
@Table(name = "menu")
public class Menu {
    /* unique id in DB table */
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private int id;

    /* dish menu's name */
    @Column(name = "name")
    private String name;

    /* dishes in menu */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "dish_to_menu",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    private List<Dish> dishes;

    public Menu() {
    }

    public Menu(String name) {
        this.name = name;
        dishes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Menu)) return false;

        Menu menu = (Menu) o;

        if (name != null ? !name.equals(menu.name) : menu.name != null) return false;
        return dishes != null ?
                ( dishes.size() != 0 ? customDishEquals(dishes, menu.dishes) : menu.dishes.size() == 0) :
                menu.dishes == null;

    }

    public boolean customDishEquals(List<Dish> dishes, List<Dish> menuDishes) {
        if (dishes.size() != menuDishes.size())
            return false;
        List<Dish> dishesCopy = new ArrayList<>(menuDishes);
        for (Dish dish : dishes) {
            if (dishesCopy.contains(dish))
                dishesCopy.remove(dish);
        }
        return dishesCopy.size() == 0;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (dishes != null ? dishes.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
