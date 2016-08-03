package ua.goit.timonov.enterprise.module_6_2.model;

import java.util.List;

/**
 * Created by Alex on 31.07.2016.
 */
public interface EmployeeDAO {

    void add(Employee employee);

    Employee search(int id);

    Employee search(String surnameToSearch, String nameToSearch);

    void delete(int id);

    void delete(String surname, String name);

    List<Employee> getAll();

}