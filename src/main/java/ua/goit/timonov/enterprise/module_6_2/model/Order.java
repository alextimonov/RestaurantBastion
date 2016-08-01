package ua.goit.timonov.enterprise.module_6_2.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Alex on 01.08.2016.
 */
public class Order {
    private int id;
    private int waiterId;
    private int tableNumber;
    private Date date;
    private Boolean  closed;
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


