package ua.goit.timonov.enterprise.module_6_2.model;

import java.util.List;

/**
 * Created by Alex on 01.08.2016.
 */
public interface OrderDAO {

    void add(Order order);

    void delete(int orderId);

    void addDish(int orderId, String dishName);

    void deleteDish(int orderId, String dishName);

    void setClosed(int orderId);

    List<Order> getOpenOrders();

    List<Order> getClosedOrders();

    Order search(Integer id);
}

