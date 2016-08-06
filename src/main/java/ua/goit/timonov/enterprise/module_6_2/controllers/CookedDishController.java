package ua.goit.timonov.enterprise.module_6_2.controllers;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.model.CookedDish;
import ua.goit.timonov.enterprise.module_6_2.dao.CookedDishDAO;

import java.util.List;

/**
 * Controller for CookedDishDAO
 */
public class CookedDishController {

    private CookedDishDAO cookedDishDAO;

    public void setCookedDishDAO(CookedDishDAO cookedDishDAO) {
        this.cookedDishDAO = cookedDishDAO;
    }

    /**
     * finds list of all cooked dishes in DB
     * @return              list of cooked dishes
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<CookedDish> getAll() {
        return cookedDishDAO.getAll();
    }

    /**
     * adds new cooked dish to DB table
     * @param orderId       order's id, in which dish is cooked
     * @param dishName      name of dish
     * @param cookId        employee's employee that cooked the dish
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void add(int orderId, String dishName, int cookId) {
        cookedDishDAO.add(orderId, dishName, cookId);
    }
}
