package ua.goit.timonov.enterprise.module_6_2.model;

import java.util.List;

/**
 * Created by Alex on 31.07.2016.
 */
public interface EmployeeDAO {

    void add(Employee employee);

    void delete(Employee employee);

    Employee find(String surnameToSearch, String nameToSearch);

    List<Employee> getAll();

}