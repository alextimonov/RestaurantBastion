package ua.goit.timonov.enterprise.module_6_2.model;

/**
 * Provides dish's data
 */

public class Dish extends DbItem {
    /* dish' description */
    private String description;

    /* dish's cost */
    private float cost;

    /* dish's weight */
    private int weight;

    public Dish() {
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

    public Dish append(String name, String description) {
        setName(name);
        setDescription(description);
        return this;
    }

    public Dish append(float cost) {
        setCost(cost);
        return this;
    }

    public Dish append(int weight) {
        setCost(weight);
        return this;
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


