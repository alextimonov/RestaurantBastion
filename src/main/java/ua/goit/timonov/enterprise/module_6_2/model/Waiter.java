package ua.goit.timonov.enterprise.module_6_2.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Provides waiter's data to additional storage orders list
 */

@Entity
public class Waiter extends Employee {

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "waiter")
    @Fetch(FetchMode.SELECT)
    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Waiter{" + super.toString() +
                " \n     orders=" + orders + "} ";
    }

    public String toStringWithoutOrders() {
        return "Waiter{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", job=" + job +
                '}';
    }
}
