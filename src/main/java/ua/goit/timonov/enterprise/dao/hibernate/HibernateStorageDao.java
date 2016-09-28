package ua.goit.timonov.enterprise.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.dao.StorageDAO;
import ua.goit.timonov.enterprise.exceptions.NoItemInDbException;
import ua.goit.timonov.enterprise.model.Ingredient;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * Hibernate implementation of OrderDAO
 */
public class HibernateStorageDao implements StorageDAO {

    public static final String FIELD_AMOUNT = "amount";
    private SessionFactory sessionFactory;
    private JpaCriteriaQueries<Ingredient> jpaCriteriaQueries = new JpaCriteriaQueries();

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * finds list of all ingredients in DB
     * @return              list of ingredients
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional
    public List<Ingredient> getAll() {
        return jpaCriteriaQueries.getAllEntityItems(sessionFactory, Ingredient.class);
    }

    /**
     * adds new ingredient to DB
     * @param ingredient      given ingredient
     */
    @Override
    @Transactional
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
    @Transactional
    public Ingredient search(int id) {
        try {
            return jpaCriteriaQueries.searchItemById(sessionFactory, Ingredient.class, id);
        }
        catch (Exception e) {
            throw new NoItemInDbException("There's no ingredient with id=" + id + " in database!");
        }
    }

    /**
     * searches ingredient in DB by startChars
     * @param name       startChars of ingredient to find
     * @return           found ingredient
     * throws            EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional
    public Ingredient search(String name) {
        try {
            return jpaCriteriaQueries.searchItemByName(sessionFactory, Ingredient.class, name);
        }
        catch (IndexOutOfBoundsException | NoResultException e) {
            throw new NoItemInDbException("There's no ingredient with name \"" + name + "\" in database!");
        }
    }

    /**
     * deletes ingredient from DB by its ID
     * @param id            ingredient's ID to delete
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional
    public void delete(int id) {
        Ingredient ingredient = search(id);
        sessionFactory.getCurrentSession().delete(ingredient);
    }

    /**
     * deletes ingredient from DB by its startChars
     * @param name           startChars of ingredient to delete
     * throws                EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional
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
    @Transactional
    public void changeAmount(Ingredient ingredient, int difference) {
        int newAmount = ingredient.getAmount() + difference;
        jpaCriteriaQueries.updateValue(sessionFactory, Ingredient.class, ingredient.getId(), FIELD_AMOUNT, newAmount);
    }

    /**
     * finds list of terminating ingredients with amount less than given limit
     * @param limit                 limit to chose an ingredient
     * @return                      list of ingredient
     */
    @Override
    @Transactional
    public List<Ingredient> getTerminatingIngredients(int limit) {
        return jpaCriteriaQueries.getItemsLimitedByMaxValue(sessionFactory, Ingredient.class, limit);
    }

    /**
     * updates ingredient's data in DB
     * @param ingredient      given ingredient with data
     */
    @Override
    @Transactional
    public void update(Ingredient ingredient) {
        Session session = sessionFactory.getCurrentSession();
        session.update("Ingredient", ingredient);
    }

    @Override
    @Transactional
    public List<Ingredient> filterWithStartChars(String startChars) {
        return jpaCriteriaQueries.getItemsStartWithChars(sessionFactory, Ingredient.class, "name", startChars);
    }
}
