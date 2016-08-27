package ua.goit.timonov.enterprise.module_6_2.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.module_6_2.dao.MenuDAO;
import ua.goit.timonov.enterprise.module_6_2.model.Dish;
import ua.goit.timonov.enterprise.module_6_2.model.Menu;

import java.util.List;

/**
 * Hibernate implementation of MenuDao
 */
public class HMenuDao implements MenuDAO {

    private SessionFactory sessionFactory;
    private HDaoCriteriaQueries<Menu> hDaoCriteriaQueries = new HDaoCriteriaQueries();

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * finds list of all menus in DB
     * @return          list of menus
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Menu> getAll() {
        return hDaoCriteriaQueries.getAllEntityItems(sessionFactory, Menu.class);
    }

    /**
     * adds new menu to DB
     * @param menu      given menus
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void add(Menu menu) {
        sessionFactory.getCurrentSession().save(menu);
    }

    /**
     * searches menu in DB by its ID
     * @param id        menu's ID to find
     * @return          found menu
     * throws           EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Menu search(int id) {
        return hDaoCriteriaQueries.searchItemById(sessionFactory, Menu.class, id);
    }

    /**
     * searches menu in DB by name
     * @param name       name of menu to find
     * @return           found menu
     * throws            EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Menu search(String name) {
        return hDaoCriteriaQueries.searchItemByName(sessionFactory, Menu.class, name);
    }

    /**
     * deletes menu from DB by its ID
     * @param id            menu's ID to delete
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void delete(int id) {
        Menu menu = search(id);
        sessionFactory.getCurrentSession().delete(menu);
    }

    /**
     * deletes menu from DB by its name
     * @param name           name of menu to delete
     * throws                EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void delete(String name) {
        Menu menu = search(name);
        sessionFactory.getCurrentSession().delete(menu);
    }

    /**
     * adds dish to menu
     * @param menu          dish menu
     * @param dish          dish to be added
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void addDish(Menu menu, Dish dish) {
        if (menu.getDishes().contains(dish)) {
            throw new IllegalArgumentException("Menu " + menu.getName() + " already contains dish " + dish.getName());
        }
        else {
            Session session = sessionFactory.getCurrentSession();
            session.delete(menu);
            menu.getDishes().add(dish);
            session.save(menu);
        }
    }

    /**
     * deletes dish from menu
     * @param menu          dish menu
     * @param dish          dish to be deleted
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void deleteDish(Menu menu, Dish dish) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(menu);
        if (!menu.getDishes().remove(dish)) {
            throw new IllegalArgumentException("Menu " + menu.getName() + " does not contain dish " + dish.getName());
        }
        session.save(menu);
    }
}
