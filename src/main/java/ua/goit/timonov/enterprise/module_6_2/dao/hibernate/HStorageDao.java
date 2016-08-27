package ua.goit.timonov.enterprise.module_6_2.dao.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.dao.StorageDAO;
import ua.goit.timonov.enterprise.module_6_2.model.Ingredient;

import java.util.List;

/**
 * Hibernate implementation of OrderDAO
 */
public class HStorageDao implements StorageDAO {

    public static final String FIELD_AMOUNT = "amount";
    private SessionFactory sessionFactory;
    private HDaoCriteriaQueries<Ingredient> hDaoCriteriaQueries = new HDaoCriteriaQueries();

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * finds list of all ingredients in DB
     * @return              list of ingredients
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Ingredient> getAll() {
        return hDaoCriteriaQueries.getAllEntityItems(sessionFactory, Ingredient.class);
    }

    /**
     * adds new ingredient to DB
     * @param ingredient      given ingredient
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void add(Ingredient ingredient) {
        sessionFactory.getCurrentSession().save(ingredient);
    }

    /**
     * searches ingredient in DB by its ID
     * @param id        ingredient's ID to find
     * @return          found ingredient
     * throws           EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Ingredient search(int id) {
        return hDaoCriteriaQueries.searchItemById(sessionFactory, Ingredient.class, id);
    }

    /**
     * searches ingredient in DB by name
     * @param name       name of ingredient to find
     * @return           found ingredient
     * throws            EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Ingredient search(String name) {
        return hDaoCriteriaQueries.searchItemByName(sessionFactory, Ingredient.class, name);
    }

    /**
     * deletes ingredient from DB by its ID
     * @param id            ingredient's ID to delete
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void delete(int id) {
        Ingredient ingredient = search(id);
        sessionFactory.getCurrentSession().delete(ingredient);
    }

    /**
     * deletes ingredient from DB by its name
     * @param name           name of ingredient to delete
     * throws                EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void delete(String name) {
        Ingredient ingredient = search(name);
        sessionFactory.getCurrentSession().delete(ingredient);
    }

    /**
     * changes amount of ingredient
     * @param ingredient            ingredient to change its amount in storage
     * @param difference            difference to be added to current amount
     * throws                       EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void changeAmount(Ingredient ingredient, int difference) {
        int newAmount = ingredient.getAmount() + difference;

        hDaoCriteriaQueries.updateValue(sessionFactory, Ingredient.class, ingredient.getId(), FIELD_AMOUNT, newAmount);
    }

    /**
     * finds list of terminating ingredients with amount less than given limit
     * @param limit                 limit to chose an ingredient
     * @return                      list of ingredient
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Ingredient> getTerminatingIngredients(int limit) {
        return hDaoCriteriaQueries.getItemsLimitedByMaxValue(sessionFactory, Ingredient.class, limit);
    }
}
