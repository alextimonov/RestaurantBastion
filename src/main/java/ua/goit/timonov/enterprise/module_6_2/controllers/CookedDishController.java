package ua.goit.timonov.enterprise.module_6_2.controllers;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.model.CookedDish;
import ua.goit.timonov.enterprise.module_6_2.dao.CookedDishDAO;

import java.util.List;

/**
 * Created by Alex on 01.08.2016.
 */
public class CookedDishController {

    private CookedDishDAO cookedDishDAO;

    public void setCookedDishDAO(CookedDishDAO cookedDishDAO) {
        this.cookedDishDAO = cookedDishDAO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void add(int orderId, String dishName, int cookId) {
        cookedDishDAO.add(orderId, dishName, cookId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<CookedDish> getAll() {
        return cookedDishDAO.getAll();
    }
}
