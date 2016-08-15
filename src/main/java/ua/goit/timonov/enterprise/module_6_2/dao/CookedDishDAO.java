package ua.goit.timonov.enterprise.module_6_2.dao;

import ua.goit.timonov.enterprise.module_6_2.model.CookedDish;

import java.util.List;

/**
 * DAO interface for CookedDish
 */
public interface CookedDishDAO {

    /**
     * finds list of all cooked dishes in DB
     * @return              list of cooked dishes
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    List<CookedDish> getAll();

    /**
     * adds new cooked dish to DB table
     * @param orderId       order's id, in which dish is cooked
     * @param dishName      name of dish
     * @param cookId        employee's employee that cooked the dish
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    void add(int orderId, String dishName, int cookId);

    void add(CookedDish cookedDish);
}
