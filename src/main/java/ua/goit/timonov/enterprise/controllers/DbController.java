package ua.goit.timonov.enterprise.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.dao.DishDAO;
import ua.goit.timonov.enterprise.dao.EmployeeDAO;
import ua.goit.timonov.enterprise.dao.StorageDAO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for manage database (create, restore, delete data)
 */
public class DbController {

    public static final String PATH_TO_DROP_ALL_TABLES      = "src/main/resources/sql/drop_all_tables.sql";
    public static final String PATH_TO_DELETE_ALL_DATA      = "src/main/resources/sql/delete_all_data.sql";
    public static final String PATH_TO_CREATE_ALL_TABLES    = "src/main/resources/sql/create_all_tables.sql";
    public static final String PATH_TO_DATA_POPULATION      = "src/main/resources/sql/fill_all_tables.sql";
    public static final String PATH_TO_FILL_TABLE_JOBS      = "src/main/resources/sql/fill_table_jobs.sql";
    public static final String SEMICOLON = ";";
    public static final String EMPTY_STRING = "";

    private JdbcTemplate jdbcTemplate;
    private EmployeeDAO employeeDAO;
    private DishDAO dishDAO;
    private StorageDAO storageDAO;
    private static final Logger LOGGER = LoggerFactory.getLogger(DbController.class);

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public EmployeeDAO getEmployeeDAO() {
        return employeeDAO;
    }

    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public DishDAO getDishDAO() {
        return dishDAO;
    }

    public void setDishDAO(DishDAO dishDAO) {
        this.dishDAO = dishDAO;
    }

    public StorageDAO getStorageDAO() {
        return storageDAO;
    }

    public void setStorageDAO(StorageDAO storageDAO) {
        this.storageDAO = storageDAO;
    }

    @Transactional
    public void deleteAllData() throws FileNotFoundException {
        String[] queries = readQueriesFromFile(PATH_TO_DELETE_ALL_DATA);
        jdbcTemplate.batchUpdate(queries);
    }

    @Transactional
    public void dropAllTables() throws FileNotFoundException {
        String[] queries = readQueriesFromFile(PATH_TO_DROP_ALL_TABLES);
        jdbcTemplate.batchUpdate(queries);
    }

    @Transactional
    public void createAllTables() throws FileNotFoundException {
        String[] createDbQueries = readQueriesFromFile(PATH_TO_CREATE_ALL_TABLES);
        jdbcTemplate.batchUpdate(createDbQueries);
    }

    @Transactional
    public void restoreAllData() throws FileNotFoundException {
        String[] populateDataQueries = readQueriesFromFile(PATH_TO_DATA_POPULATION);
        jdbcTemplate.batchUpdate(populateDataQueries);
    }

    private String[] readQueriesFromFile(String pathToQueriesFile) {
        List<String> queries = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToQueriesFile))) {
            readQueries(queries, br);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return queries.toArray(new String[queries.size()]);
    }

    private void readQueries(List<String> queries, BufferedReader br) throws IOException {
        String query = EMPTY_STRING;
        String currentLine;
        while ((currentLine = br.readLine()) != null) {
            query = query + currentLine;
            if (currentLine.endsWith(SEMICOLON)) {
                queries.add(query);
                query = EMPTY_STRING;
            }
        }
    }

    @Transactional
    public void fillTableJobs() {
        String[] insertQueries = readQueriesFromFile(PATH_TO_FILL_TABLE_JOBS);
        jdbcTemplate.batchUpdate(insertQueries);
    }
}
