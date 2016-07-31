package ua.goit.timonov.enterprise.module_6_2.model;

/**
 * Created by Alex on 31.07.2016.
 */

public class Dish {
    private int id;
    private String name;
    private String description;
    private float cost;
    private int weight;

    public Dish() {
    }

    public Dish(int id, String name, String description, float cost, int weight) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.weight = weight;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", weight=" + weight +
                '}';
    }
}


