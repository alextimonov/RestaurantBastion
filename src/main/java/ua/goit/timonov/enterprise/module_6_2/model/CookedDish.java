package ua.goit.timonov.enterprise.module_6_2.model;

/**
 * Created by Alex on 01.08.2016.
 */
public class CookedDish {
    private int id;
    private int orderedDishId;

    private Order order;
    private Dish dish;
    private Employee cook;

    public CookedDish() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderedDishId() {
        return orderedDishId;
    }

    public void setOrderedDishId(int orderedDishId) {
        this.orderedDishId = orderedDishId;
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

    @Override
    public String toString() {
        return "CookedDish{" +
                "id=" + id +
                ", orderedDishId=" + orderedDishId +
                '}';
    }
}
