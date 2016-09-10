package ua.goit.timonov.enterprise.module_6_2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Provides waiter's data to additional storage orders list
 */

@Entity
@JsonIgnoreType
public class Waiter extends Employee {

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "waiter")
    @JsonIgnore
    private List<Order> orders;

    public Waiter() {
    }

    public Waiter(Employee employee) {
        id = employee.getId();
        surname = employee.getSurname();
        name = employee.getName();
        birthday = employee.getBirthday();
        salary = employee.getSalary();
        job = employee.getJob();
    }

    public Employee getEmployee() {
        Employee employee = new Employee();
        employee.append(this.birthday);
        employee.append(this.name, this.surname);
        employee.append(this.salary);
        employee.append(this.job);
        return employee;
    }

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
}
