package ua.goit.timonov.enterprise.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Provides employee's data
 */
@Entity
@Table(name = "employee")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Employee {

    /* unique id in the DB table */
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    protected int id;

    /* employee's surname */
    @Column(name = "surname")
    protected String surname;

    /* employee's name */
    @Column(name = "name")
    protected String name;

    /* employee's date of birthday */
    @Column(name = "birthday")
//    @Temporal(TemporalType.TIMESTAMP)
    protected Date birthday;

    /* employee's salary */
    @Column(name = "salary")
    protected float salary;

    /* employee's position */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id")
    protected Job job;

    public Employee() {
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

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
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

    public Employee append(Job job) {
        setJob(job);
        return this;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", salary=" + salary +
                ", job=" + job +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        if (Float.compare(employee.salary, salary) != 0) return false;
        if (surname != null ? !surname.equals(employee.surname) : employee.surname != null) return false;
        if (name != null ? !name.equals(employee.name) : employee.name != null) return false;
        if (birthday != null ? !birthday.equals(employee.birthday) : employee.birthday != null) return false;
        return job != null ? job.equals(employee.job) : employee.job == null;

    }

    @Override
    public int hashCode() {
        int result = surname != null ? surname.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (salary != +0.0f ? Float.floatToIntBits(salary) : 0);
        result = 31 * result + (job != null ? job.hashCode() : 0);
        return result;
    }
}

