package ua.goit.timonov.enterprise.web;

import org.springframework.format.annotation.DateTimeFormat;
import ua.goit.timonov.enterprise.model.Employee;
import ua.goit.timonov.enterprise.model.Job;
import ua.goit.timonov.enterprise.model.Position;

import java.util.Date;

/**
 * DTO class for Employee
 */
public class EmployeeView {

    /* unique id in the DB table */
    private int id;

    /* employee's surname */
    private String surname;

    /* employee's name */
    private String name;

    /* employee's date of birthday */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date birthday;

    /* employee's salary */
    private float salary;

    /* employee's position */
    private String position;

    public EmployeeView() {
    }

    public EmployeeView(Employee employee) {
        id = employee.getId();
        name = employee.getName();
        surname = employee.getSurname();
        birthday = employee.getBirthday();
        salary = employee.getSalary();
        position = employee.getJob().getPosition().toString().toLowerCase();
    }

    public Employee getEmployeeFromView() {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setSurname(surname);
        employee.setBirthday(birthday);
        employee.setSalary(salary);
        employee.setJob(new Job(Position.byName(position.toUpperCase())));
        return employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeView)) return false;

        EmployeeView that = (EmployeeView) o;

        if (id != that.id) return false;
        if (Float.compare(that.salary, salary) != 0) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
        return position != null ? position.equals(that.position) : that.position == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (salary != +0.0f ? Float.floatToIntBits(salary) : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }
}
