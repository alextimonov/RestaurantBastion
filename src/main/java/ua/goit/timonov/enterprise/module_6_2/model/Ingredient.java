package ua.goit.timonov.enterprise.module_6_2.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Provides ingredient's data
 */
@Entity
@Table(name = "ingredient")
public class Ingredient {

    /* unique id in DB table */
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private int id;

    /* ingredient's name */
    @Column(name = "name")
    private String name;

    /* ingredient's amount */
    @Column(name = "amount")
    private int amount;

    public Ingredient() {
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }
}
