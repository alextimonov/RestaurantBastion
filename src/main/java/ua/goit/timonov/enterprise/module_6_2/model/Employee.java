package ua.goit.timonov.enterprise.module_6_2.model;

import java.util.Date;

/**
 * Provides employee's data
 */
public class Employee extends DbItem {

    /* employee's surname */
    private String surname;

    /* employee's date of birthday */
    private Date birthday;

    /* employee's salary */
    private float salary;

    /* employee's position */
    private String position;

    public Employee() {
    }

    public Employee append(String name, String surname) {
        setName(name);
        setSurname(surname);
        return this;
    }

    public Employee append(Date birthday) {
        setBirthday(birthday);
        return this;
    }

    public Employee append(float salary) {
        setSalary(salary);
        return this;
    }

    public Employee append(String position) {
        setPosition(position);
        return this;
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
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", salary=" + salary +
                ", position='" + position + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        if (!super.equals(o)) return false;

        Employee employee = (Employee) o;

        if (Float.compare(employee.salary, salary) != 0) return false;
        if (surname != null ? !surname.equals(employee.surname) : employee.surname != null) return false;
        if (birthday != null ? !birthday.equals(employee.birthday) : employee.birthday != null) return false;
        return position != null ? position.equals(employee.position) : employee.position == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (salary != +0.0f ? Float.floatToIntBits(salary) : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }

}
