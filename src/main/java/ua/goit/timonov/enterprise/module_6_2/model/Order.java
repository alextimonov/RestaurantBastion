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

//    private int nDishes;

    public Order() {
    }

    public Order(Employee waiter, int tableNumber, Date date) {
        this.waiter = waiter;
        this.tableNumber = tableNumber;
        this.date = date;
        closed = false;
        dishes = new ArrayList<>();
    }

    public Order(Employee waiter, int tableNumber, Date date, Boolean closed) {
        this.waiter = waiter;
        this.tableNumber = tableNumber;
        this.date = date;
        this.closed = closed;
        dishes = new ArrayList<>();
    }

    public Order(Employee waiter, int tableNumber, Date date, Boolean closed, List<Dish> dishes) {
        this.waiter = waiter;
        this.tableNumber = tableNumber;
        this.date = date;
        this.closed = closed;
        this.dishes = dishes;
    }

    public Order append(Employee waiter) {
        setWaiter(waiter);
        return this;
    }

    public Order append(int tableNumber) {
        setTableNumber(tableNumber);
        return this;
    }

    public Order append(Date date) {
        setDate(date);
        return this;
    }

    public Order append(boolean closed) {
        setClosed(closed);
        return this;
    }

    public Order append(List<Dish> dishes) {
        setDishes(dishes);
        return this;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (dishes != null ?
                ( dishes.size() != 0 ? !customDishEquals(dishes, order.dishes) : order.dishes.size() != 0) :
                order.dishes != null) return false;
        if (tableNumber != order.tableNumber) return false;
        if (waiter != null ? !waiter.equals(order.waiter) : order.waiter != null) return false;
        if (date != null ? !date.equals(order.date) : order.date != null) return false;
        return (closed != null ? closed.equals(order.closed) : order.closed == null);
    }

    public boolean customDishEquals(List<Dish> dishes, List<Dish> menuDishes) {
        if (dishes.size() != menuDishes.size())
            return false;
        List<Dish> dishesCopy = new ArrayList<>(menuDishes);
        for (Dish dish : dishes) {
            if (dishesCopy.contains(dish))
                dishesCopy.remove(dish);
        }
        return dishesCopy.size() == 0;
    }

    private List<Dish> copyList(List<Dish> dishes) {
        List<Dish> result = new ArrayList<>();
        for (Dish dish : dishes) {
            result.add(new Dish());
        }
        return result;
    }

    @Override
    public int hashCode() {
        int result = waiter != null ? waiter.hashCode() : 0;
        result = 31 * result + tableNumber;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (closed != null ? closed.hashCode() : 0);
        result = 31 * result + (dishes != null ? dishes.hashCode() : 0);
        return result;
    }

    public String getName() {
        return toString();
    }
}

