package ua.goit.timonov.enterprise.module_6_2.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.dao.DishDAO;
import ua.goit.timonov.enterprise.module_6_2.model.Dish;

import java.util.List;

/**
 * Hibernate implementation of DishDAO
 */
public class HDishDao implements DishDAO {

    private SessionFactory sessionFactory;
    private HDaoCriteriaQueries<Dish> hDaoCriteriaQueries = new HDaoCriteriaQueries();

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<Dish> getAll() {
        return hDaoCriteriaQueries.getAllEntityItems(sessionFactory, Dish.class);
    }

    /**
     * adds new dish to DB
     * @param dish      given dish
     */
    @Override
    @Transactional
    public void add(Dish dish) {
        Session session = sessionFactory.getCurrentSession();
        session.save(dish);
    }

    /**
     * searches dish in DB by its ID
     * @param id        dish's ID to find
     * @return          found dish
     * throws           EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional
    public Dish search(int id) {
        return hDaoCriteriaQueries.searchItemById(sessionFactory, Dish.class, id);
    }

    /**
     * searches dish in DB by name
     * @param name           name of dish to find
     * @return name          found dish
     * throws                EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional
    public Dish search(String name) {
        return hDaoCriteriaQueries.searchItemByName(sessionFactory, Dish.class, name);
    }

    /**
     * deletes dish from DB by its ID
     * @param id            dish's ID to delete
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional
    public void delete(int id) {
        Dish dish = search(id);
        sessionFactory.getCurrentSession().delete(dish);
    }

    /**
     * deletes dish from DB by its name
     * @param name           name of dish to delete
     * throws                EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional
    public void delete(String name) {
        Dish dish = search(name);
        sessionFactory.getCurrentSession().delete(dish);
    }
}
