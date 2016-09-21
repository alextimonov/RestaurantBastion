package ua.goit.timonov.enterprise.module_6_2.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.dao.DishDAO;
import ua.goit.timonov.enterprise.module_6_2.model.Dish;
import ua.goit.timonov.enterprise.module_6_2.model.Ingredient;
import ua.goit.timonov.enterprise.module_6_2.model.IngredientsInDish;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Hibernate implementation of DishDAO
 */
public class HibernateDishDao implements DishDAO {

    private SessionFactory sessionFactory;
    private JpaCriteriaQueries<Dish> hDaoCriteriaQueries = new JpaCriteriaQueries();

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

    @Transactional
    public List<Ingredient> defineDishIngredients(Dish dish) {
        JpaCriteriaQueries<IngredientsInDish> hDaoCriteriaQueries = new JpaCriteriaQueries();
        List<IngredientsInDish> ingredientsInDishes =
                hDaoCriteriaQueries.searchItemsByValue(sessionFactory, IngredientsInDish.class, "dish", dish);
        return ingredientsInDishes.stream().map(IngredientsInDish::getIngredient).collect(Collectors.toList());
    }
}
