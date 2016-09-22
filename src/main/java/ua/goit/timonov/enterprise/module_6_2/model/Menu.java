package ua.goit.timonov.enterprise.module_6_2.model;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.GenericGenerator;
import ua.goit.timonov.enterprise.module_9.view.JsonMenuViews;

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
    @JsonView(JsonMenuViews.OnlyNames.class)
    private int id;

    /* dish menu's name */
    @Column(name = "name")
    @JsonView(JsonMenuViews.OnlyNames.class)
    private String name;

    /* dishes in menu */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "dish_to_menu",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    @JsonView(JsonMenuViews.NamesWithDishes.class)
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
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
