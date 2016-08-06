package ua.goit.timonov.enterprise.module_6_2.model;

/**
 * Provides dish's data
 */

public class Dish {
    /* unique id in the DB table */
    private int id;

    /* dish's name */
    private String name;

    /* dish' description */
    private String description;

    /* dish's cost */
    private float cost;

    /* dish's weight */
    private int weight;

    public Dish() {
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


