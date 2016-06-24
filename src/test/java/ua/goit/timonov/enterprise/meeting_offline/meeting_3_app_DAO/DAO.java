package ua.goit.timonov.enterprise.meeting_offline.meeting_3_app_DAO;

import java.util.List;

/**
 * Created by Alex on 21.06.2016.
 */
public interface DAO {
    public void createEmployee(String name);

    public Employee getEmployee(long id);

    public List<Employee> getEmployeeByName(String name);

    public void updateEmployee(long id, Employee employee);

    public void deleteEmployee(long id);

    public void readMetaData();

    public void bulkAction();

}
