package ua.goit.timonov.enterprise.module_6_2.model;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.GenericGenerator;
import ua.goit.timonov.enterprise.module_9.view.JsonMenuViews;

import javax.persistence.*;

/**
 * Provides dish's data
 */
@Entity
@Table(name = "dish")
public class Dish {

    /* unique id in the DB table */
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    @JsonView(JsonMenuViews.OnlyNames.class)
    private int id;

    /* dish's name */
    @Column(name = "name")
    @JsonView(JsonMenuViews.OnlyNames.class)
    private String name;

    /* dish' description */
    @Column(name = "description")
    private String description;

    /* dish's weight */
    @Column(name = "weight")
    private int weight;

    /* dish's cost */
    @Column(name = "cost")
    private float cost;

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
                ", weight=" + weight +
                ", cost=" + cost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dish)) return false;

        Dish dish = (Dish) o;

        if (id != dish.id) return false;
        if (Float.compare(dish.cost, cost) != 0) return false;
        if (weight != dish.weight) return false;
        if (name != null ? !name.equals(dish.name) : dish.name != null) return false;
        return description != null ? description.equals(dish.description) : dish.description == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (cost != +0.0f ? Float.floatToIntBits(cost) : 0);
        result = 31 * result + weight;
        return result;
    }
}


