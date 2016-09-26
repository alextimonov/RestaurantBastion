package ua.goit.timonov.enterprise.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Provides cooked dish's data
 */
@Entity
@Table(name = "cooked_dish")
public class CookedDish {

    /* unique ID in DB table */
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private int id;

    /* order to which cooked dish is associated */
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    /* cook who cooks this dish */
    @ManyToOne
    @JoinColumn(name = "cook_id")
    private Employee cook;

    /* dish type of cooked dish */
    @ManyToOne
    @JoinColumn(name = "menu_dish_id")
    private Dish dish;

    public CookedDish() {
    }

    public CookedDish(Order order, Dish dish, Employee cook) {
        this.order = order;
        this.dish = dish;
        this.cook = cook;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Employee getCook() {
        return cook;
    }

    public void setCook(Employee cook) {
        this.cook = cook;
    }

    public String getName() {
        return toString();
    }

    @Override
    public String toString() {
        return "CookedDish{" +
                "id=" + id +
                " order's id=" + order.getId() +
                " dish's name=" + dish.getName() +
                " cook's name=" + cook.getSurname() + " " + cook.getName() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CookedDish)) return false;

        CookedDish that = (CookedDish) o;

        if (order != null ? !order.equals(that.order) : that.order != null) return false;
        if (cook != null ? !cook.equals(that.cook) : that.cook != null) return false;
        return dish != null ? dish.equals(that.dish) : that.dish == null;

    }

    @Override
    public int hashCode() {
        int result = order != null ? order.hashCode() : 0;
        result = 31 * result + (cook != null ? cook.hashCode() : 0);
        result = 31 * result + (dish != null ? dish.hashCode() : 0);
        return result;
    }
}

