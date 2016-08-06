package ua.goit.timonov.enterprise.module_6_2.dao;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.model.CookedDish;

import java.util.List;

/**
 * Created by Alex on 01.08.2016.
 */
public interface CookedDishDAO {

    void add(int orderId, String dishName, int cookId);

    List<CookedDish> getAll();
}
