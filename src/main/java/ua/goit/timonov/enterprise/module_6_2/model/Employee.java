package ua.goit.timonov.enterprise.module_6_2.model;

import java.util.Date;

/**
 * Created by Alex on 30.07.2016.
 */
public class Employee {
    private int id;
    private String surname;
    private String name;
    private Date birthday;
    private int position_id;
    private float salary;
    private String position;

    public Employee() {
    }

    public Employee(int id, String surname, String name, Date birthday, int position_id, float salary) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.birthday = birthday;
        this.position_id = position_id;
        this.salary = salary;
    }

    public Employee(int id, String surname, String name, Date birthday, int position_id, float salary, String position) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.birthday = birthday;
        this.position_id = position_id;
        this.salary = salary;
        this.position = position;
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

    public int getPosition_id() {
        return position_id;
    }

    public void setPosition_id(int position_id) {
        this.position_id = position_id;
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
}
