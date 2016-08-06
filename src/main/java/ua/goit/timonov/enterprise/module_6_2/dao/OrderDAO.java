package ua.goit.timonov.enterprise.module_6_2.dao;

import ua.goit.timonov.enterprise.module_6_2.model.Dish;
import ua.goit.timonov.enterprise.module_6_2.model.Order;

import java.util.List;

/**
 * Created by Alex on 01.08.2016.
 */
public interface OrderDAO {

    void add(Order order);

    void delete(int orderId);

    void addDish(int orderId, Dish dish);

    void deleteDish(int orderId, Dish dish);

    void setClosed(int orderId);

    List<Order> getOpenOrders();

    List<Order> getClosedOrders();

    Order search(Integer id);
}

