package ua.goit.timonov.enterprise.module_6_2.model;

/**
 * Provides ingredient's data
 */
public class Ingredient extends DbItem {

    /* unique id in DB table */
    private int id;

    /* ingredient's name */
    private String name;

    /* ingredient's amount */
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
