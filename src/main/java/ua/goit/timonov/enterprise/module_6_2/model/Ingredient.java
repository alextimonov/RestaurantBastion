package ua.goit.timonov.enterprise.module_6_2.model;

/**
 * Provides ingredient's data
 */
public class Ingredient extends DbItem {
    /* ingredient's amount */
    private int amount;

    public Ingredient() {
    }

    public Ingredient(String name, int amount) {
        super(name);
        this.amount = amount;
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
