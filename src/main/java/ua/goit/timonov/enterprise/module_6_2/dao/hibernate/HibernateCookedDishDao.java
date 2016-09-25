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
    private JpaCriteriaQueries<CookedDish> hDaoCriteriaQueries = new JpaCriteriaQueries();
    private HibernateOrderDao hOrderDao;
    private HibernateDishDao hDishDao;
    private HibernateEmployeeDao hEmployeeDao;
    private HibernateStorageDao hStorageDao;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void sethOrderDao(HibernateOrderDao hOrderDao) {
        this.hOrderDao = hOrderDao;
    }

    public void sethDishDao(HibernateDishDao hDishDao) {
        this.hDishDao = hDishDao;
    }

    public void sethEmployeeDao(HibernateEmployeeDao hEmployeeDao) {
        this.hEmployeeDao = hEmployeeDao;
    }

    public void sethStorageDao(HibernateStorageDao hStorageDao) {
        this.hStorageDao = hStorageDao;
    }

    /**
     * finds list of all cooked dishes in DB
     * @return              list of cooked dishes
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional
    public List<CookedDish> getAll() {
        return hDaoCriteriaQueries.getAllEntityItems(sessionFactory, CookedDish.class);
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
        Dish dish = hDishDao.search(dishName);
        Employee cook = hEmployeeDao.search(cookId);
        Order order = hOrderDao.search(orderId);
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
            hStorageDao.changeAmount(ingredientInDish.getIngredient(), -weightForDish);
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
