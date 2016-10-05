package ua.goit.timonov.enterprise.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.dao.CookedDishDAO;
import ua.goit.timonov.enterprise.model.CookedDish;

import java.util.List;

/**
 * Web service for CookedDishDAO
 */
public class CookedDishService {

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
    @Transactional
    public void add(int orderId, String dishName, int cookId) {
        cookedDishDAO.add(orderId, dishName, cookId);
    }

    /**
     * adds cooked dish to database
     * @param cookedDish        cooked dish to add
     */
    @Transactional
    public void add(CookedDish cookedDish) {
        cookedDishDAO.add(cookedDish);
    }
}
