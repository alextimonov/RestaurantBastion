package ua.goit.timonov.enterprise.module_6_2.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Provides order's data
 */
public class Order {

    /* unique id in DB table */
    private int id;

    /* waiter's id who takes this order */
    private int waiterId;

    /* order's table number */
    private int tableNumber;

    /* order's date */
    private Date date;

    /* true if order is closed, false if one is open */
    private Boolean  closed;

    /* dishes in this order */
    private List<Dish> dishes;

    public Order() {
    }

    public Order(int waiterId, int tableNumber, Date date) {
        this.waiterId = waiterId;
        this.tableNumber = tableNumber;
        this.date = date;
        dishes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(int waiterId) {
        this.waiterId = waiterId;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", waiterId=" + waiterId +
                ", tableNumber=" + tableNumber +
                ", date=" + date +
                ", closed=" + closed +
                '}';
    }
}


