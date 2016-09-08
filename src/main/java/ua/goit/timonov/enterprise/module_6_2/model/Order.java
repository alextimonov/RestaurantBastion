package ua.goit.timonov.enterprise.module_6_2.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Provides order's data
 */
@Entity
@Table(name = "orders")
public class Order {

    /* unique id in DB table */
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private int id;

    /* waiter's id who takes this order */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "employee_id")
    private Employee waiter;

    /* order's table number */
    @Column(name = "table_number")
    private int tableNumber;

    /* order's date */
    @Column(name = "date")
    private Date date;

    /* true if order is closed, false if one is open */
    @Column(name = "closed")
    private Boolean closed;

    /* dishes in this order */
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = "dish_to_orders",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id"))
    private List<Dish> dishes;

    public Order() {
    }

    public Order(Employee waiter, int tableNumber, Date date) {
        this.waiter = waiter;
        this.tableNumber = tableNumber;
        this.date = date;
        closed = false;
        dishes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getWaiter() {
        return waiter;
    }

    public void setWaiter(Employee waiter) {
        this.waiter = waiter;
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

    public Employee getEmployee() {
        Employee employee = new Employee();
        employee.append(waiter.getBirthday());
        employee.append(waiter.getName(), waiter.getSurname());
        employee.append(waiter.getSalary());
        employee.append(waiter.getJob());
        return employee;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", waiter=" + waiter.getSurname() + " " + waiter.getName() +
                ", tableNumber=" + tableNumber +
                ", date=" + date +
                ", closed=" + closed +
                '}';
    }

    public String getName() {
        return toString();
    }


}

