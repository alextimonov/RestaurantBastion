package ua.goit.timonov.enterprise.module_6_2.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.dao.CookedDishDAO;
import ua.goit.timonov.enterprise.module_6_2.model.*;

import java.util.List;

/**
 * Hibernate implementation of CookedDishDAO
 */
public class HCookedDishDao implements CookedDishDAO {

    private SessionFactory sessionFactory;
    private HDaoCriteriaQueries<CookedDish> hDaoCriteriaQueries = new HDaoCriteriaQueries();
    private HOrderDao hOrderDao;
    private HDishDao hDishDao;
    private HEmployeeDao hEmployeeDao;
    private HStorageDao hStorageDao;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void sethOrderDao(HOrderDao hOrderDao) {
        this.hOrderDao = hOrderDao;
    }

    public void sethDishDao(HDishDao hDishDao) {
        this.hDishDao = hDishDao;
    }

    public void sethEmployeeDao(HEmployeeDao hEmployeeDao) {
        this.hEmployeeDao = hEmployeeDao;
    }

    public void sethStorageDao(HStorageDao hStorageDao) {
        this.hStorageDao = hStorageDao;
    }

    /**
     * finds list of all cooked dishes in DB
     * @return              list of cooked dishes
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
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
    @Transactional(propagation = Propagation.MANDATORY)
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
    public void add(CookedDish cookedDish) {
        checkAbilityToAdd(cookedDish);
        takeIntoAccountIngredientAmounts(cookedDish.getDish());
        Session session = sessionFactory.getCurrentSession();
        session.save(cookedDish);
    }

    // checks if there is enough amount of ingredients in the storage for chosen dish
    @Transactional(propagation = Propagation.MANDATORY)
        private void takeIntoAccountIngredientAmounts(Dish dish) {
        List<IngredientsInDish> ingredientsInDish = searchIngredientsInDishes(dish);
        validateIngredientAmounts(dish, ingredientsInDish);
        reduceIngredientAmount(ingredientsInDish);
    }

    // finds list of Ingredients in dish
    @Transactional(propagation = Propagation.MANDATORY)
    private List<IngredientsInDish> searchIngredientsInDishes(Dish dish) {
        HDaoCriteriaQueries<IngredientsInDish> hDaoCriteriaQueries = new HDaoCriteriaQueries();
        return hDaoCriteriaQueries.searchItemsByValue(sessionFactory, IngredientsInDish.class, "dish", dish);
    }

    // checks if there is enough amount of ingredients in the storage for chosen dish
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
    @Transactional(propagation = Propagation.MANDATORY)
    private void reduceIngredientAmount(List<IngredientsInDish> ingredientsInDish) {
        for (IngredientsInDish ingredientInDish : ingredientsInDish) {
            int weightForDish = ingredientInDish.getIngredientWeight();
            hStorageDao.changeAmount(ingredientInDish.getIngredient(), -weightForDish);
        }
    }

    // checks if it's possible to add given dish to list of cooked dishes
    private void checkAbilityToAdd(CookedDish cookedDish) {
        Order order = cookedDish.getOrder();
        Dish dish = cookedDish.getDish();
        if (!order.getClosed()) {
            throw new IllegalArgumentException("Order is not closed.");
        }
        if (!order.getDishes().contains(dish)) {
            throw new IllegalArgumentException("Order don't contain that dish");
        }
        List<CookedDish> cookedDishes = getAll();
        if (cookedDishes.contains(cookedDish)) {
            throw new IllegalArgumentException("That cooked dish is already in list");
        }
    }
}
