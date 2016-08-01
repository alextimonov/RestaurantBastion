package ua.goit.timonov.enterprise.module_6_2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 31.07.2016.
 */
public class Menu {
    private int id;
    private String name;
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
