package ua.goit.timonov.enterprise.module_6_2.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.dao.CookedDishDAO;
import ua.goit.timonov.enterprise.module_6_2.exceptions.ForbidToAddException;
import ua.goit.timonov.enterprise.module_6_2.model.*;

import java.util.List;

/**
 * Hibernate implementation of CookedDishDAO
 */
public class HibernateCookedDishDao implements CookedDishDAO {

    private SessionFactory sessionFactory;
    private HibernateOrderDao hibernateOrderDao;
    private HibernateDishDao hibernateDishDao;
    private HibernateEmployeeDao hibernateEmployeeDao;
    private HibernateStorageDao hibernateStorageDao;
    private JpaCriteriaQueries<CookedDish> jpaCriteriaQueries = new JpaCriteriaQueries();

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setHibernateOrderDao(HibernateOrderDao hibernateOrderDao) {
        this.hibernateOrderDao = hibernateOrderDao;
    }

    public void setHibernateDishDao(HibernateDishDao hibernateDishDao) {
        this.hibernateDishDao = hibernateDishDao;
    }

    public void setHibernateEmployeeDao(HibernateEmployeeDao hibernateEmployeeDao) {
        this.hibernateEmployeeDao = hibernateEmployeeDao;
    }

    public void setHibernateStorageDao(HibernateStorageDao hibernateStorageDao) {
        this.hibernateStorageDao = hibernateStorageDao;
    }

    /**
     * finds list of all cooked dishes in DB
     * @return              list of cooked dishes
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional
    public List<CookedDish> getAll() {
        return jpaCriteriaQueries.getAllEntityItems(sessionFactory, CookedDish.class);
    }

    /**
     * adds new cooked dish to DB table
     * @param orderId       order's id, in which dish is cooked
     * @param dishName      name of dish
     * @param cookId        employee's employee that cooked the dish
     * throws               EmptyResultDataAccessException, DataAccessException, IllegalArgumentException
     */
    @Override
    @Transactional
    public void add(int orderId, String dishName, int cookId) {
        Employee cook = hibernateEmployeeDao.search(cookId);
        Dish dish = hibernateDishDao.search(dishName);
        Order order = hibernateOrderDao.search(orderId);
        CookedDish cookedDish = new CookedDish(order, dish, cook);
        checkAbilityToAdd(cookedDish);
        takeIntoAccountIngredientAmounts(dish);
        Session session = sessionFactory.getCurrentSession();
        session.save(cookedDish);
        }

    @Override
    @Transactional
    public void add(CookedDish cookedDish) {
        checkAbilityToAdd(cookedDish);
        takeIntoAccountIngredientAmounts(cookedDish.getDish());
        Session session = sessionFactory.getCurrentSession();
        session.save(cookedDish);
    }

    // checks if there is enough amount of ingredients in the storage for chosen dish
    @Transactional
        private void takeIntoAccountIngredientAmounts(Dish dish) {
        List<IngredientsInDish> ingredientsInDish = searchIngredientsInDishes(dish);
        validateIngredientAmounts(dish, ingredientsInDish);
        reduceIngredientAmount(ingredientsInDish);
    }

    // finds list of Ingredients in dish
    @Transactional
    private List<IngredientsInDish> searchIngredientsInDishes(Dish dish) {
        JpaCriteriaQueries<IngredientsInDish> hDaoCriteriaQueries = new JpaCriteriaQueries();
        return hDaoCriteriaQueries.searchItemsByValue(sessionFactory, IngredientsInDish.class, "dish", dish);
    }

    // checks if there is enough amount of ingredients in the storage for chosen dish
    @Transactional
    private void validateIngredientAmounts(Dish dish, List<IngredientsInDish> ingredientsInDish) {
        for (IngredientsInDish ingredientInDish : ingredientsInDish) {
            int weightForDish = ingredientInDish.getIngredientWeight();
            int amountInStorage = ingredientInDish.getIngredient().getAmount();
            if (amountInStorage < weightForDish) {
                throw new IllegalArgumentException("There's not enough ingredient " + ingredientInDish.getIngredient().getName() +
                        " for dish " + dish.getName());
            }
        }
    }

    // reduces amounts of ingredients in the storage for cooking chosen dish
    @Transactional
    private void reduceIngredientAmount(List<IngredientsInDish> ingredientsInDish) {
        for (IngredientsInDish ingredientInDish : ingredientsInDish) {
            int weightForDish = ingredientInDish.getIngredientWeight();
            hibernateStorageDao.changeAmount(ingredientInDish.getIngredient(), -weightForDish);
        }
    }

    // checks if it's possible to add given dish to list of cooked dishes
    @Transactional
    private void checkAbilityToAdd(CookedDish cookedDish) {
        Order order = cookedDish.getOrder();
        Dish dish = cookedDish.getDish();
        if (!order.getClosed()) {
            throw new ForbidToAddException("Order is not closed.");
        }
        if (!order.getDishes().contains(dish)) {
            throw new ForbidToAddException("Order don't contain given dish");
        }
        List<CookedDish> cookedDishes = getAll();
        if (cookedDishes.contains(cookedDish)) {
            throw new ForbidToAddException("That cooked dish is already in list");
        }
    }
}
