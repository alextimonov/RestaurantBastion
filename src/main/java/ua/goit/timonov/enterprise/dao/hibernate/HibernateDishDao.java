package ua.goit.timonov.enterprise.dao.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.dao.DishDAO;
import ua.goit.timonov.enterprise.exceptions.ForbidToDeleteException;
import ua.goit.timonov.enterprise.exceptions.NoItemInDbException;
import ua.goit.timonov.enterprise.model.Dish;
import ua.goit.timonov.enterprise.model.Ingredient;
import ua.goit.timonov.enterprise.model.IngredientsInDish;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hibernate implementation of DishDAO
 */
public class HibernateDishDao implements DishDAO {

    private SessionFactory sessionFactory;
    private JpaCriteriaQueries<Dish> jpaCriteriaQueries = new JpaCriteriaQueries();

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<Dish> getAll() {
        return jpaCriteriaQueries.getAllEntityItems(sessionFactory, Dish.class);
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
        try {
            return jpaCriteriaQueries.searchItemById(sessionFactory, Dish.class, id);
        }
        catch (IndexOutOfBoundsException | NoResultException e) {
            throw new NoItemInDbException("There's no dish with id=" + id + " in database!");
        }
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
        try {
            return jpaCriteriaQueries.searchItemByName(sessionFactory, Dish.class, name);
        }
        catch (IndexOutOfBoundsException | NoResultException e) {
            throw new NoItemInDbException("There's no dish with name \"" + name + "\" in database!");
        }
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
        try {
            Dish dish = search(name);
            sessionFactory.getCurrentSession().delete(dish);
        } catch (HibernateException e) {
            throw new ForbidToDeleteException("Dish with name \"" + name + "\" can not be deleted due to using in another tables", e.getMessage());
        }
    }

    @Transactional
    public List<Ingredient> defineDishIngredients(Dish dish) {
        JpaCriteriaQueries<IngredientsInDish> jpaCriteriaQueries = new JpaCriteriaQueries();
        List<IngredientsInDish> ingredientsInDishes =
                jpaCriteriaQueries.searchItemsByValue(sessionFactory, IngredientsInDish.class, "dish", dish);
        return ingredientsInDishes.stream().map(IngredientsInDish::getIngredient).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void update(Dish dish) {
        Session session = sessionFactory.getCurrentSession();
        session.update("Dish", dish);
    }

    @Override
    @Transactional
    public List<IngredientsInDish> getIngredientsInDish(Dish dish) {
        JpaCriteriaQueries<IngredientsInDish> jpaCriteriaQueries = new JpaCriteriaQueries();
        return jpaCriteriaQueries.searchItemsByValue(sessionFactory, IngredientsInDish.class, "dish", dish);
    }

    @Override
    @Transactional
    public void addItemToDish(IngredientsInDish item) {
        Session session = sessionFactory.getCurrentSession();
        session.save("IngredientInDish", item);
    }

    @Override
    @Transactional
    public IngredientsInDish searchItemInDish(Integer id) {
        JpaCriteriaQueries<IngredientsInDish> jpaCriteriaQueries = new JpaCriteriaQueries();
        try {
            return jpaCriteriaQueries.searchItemById(sessionFactory, IngredientsInDish.class, id);
        }
        catch (IndexOutOfBoundsException | NoResultException e) {
            throw new NoItemInDbException("There's no such item in database!");
        }
    }

    @Override
    @Transactional
    public void updateItemInDish(IngredientsInDish item) {
        Session session = sessionFactory.getCurrentSession();
        session.update("IngredientInDish", item);
    }

    @Override
    public void deleteItemFromDish(Dish dish, IngredientsInDish item) {
        sessionFactory.getCurrentSession().delete(item);
    }
}
