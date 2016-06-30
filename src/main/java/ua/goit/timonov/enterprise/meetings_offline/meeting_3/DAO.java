package ua.goit.timonov.enterprise.meetings_offline.meeting_3;

import java.util.List;

/**
 * Created by Alex on 28.06.2016.
 */
public interface DAO {
    public void createEmployee(String name);

    public Employee getEmployye(long id);

    public List<Employee> getEmployeeByName(String name);

    public void readMetadata();

    public void bulkAction();

    public void updateEmployee(long id, Employee employee);

    public void deleteEmployee(long id);

}
