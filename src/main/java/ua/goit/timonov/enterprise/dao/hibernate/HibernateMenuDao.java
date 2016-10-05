package ua.goit.timonov.enterprise.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.timonov.enterprise.dao.MenuDAO;
import ua.goit.timonov.enterprise.exceptions.ForbidToAddException;
import ua.goit.timonov.enterprise.exceptions.ForbidToDeleteException;
import ua.goit.timonov.enterprise.exceptions.NoItemInDbException;
import ua.goit.timonov.enterprise.model.Dish;
import ua.goit.timonov.enterprise.model.Menu;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * Hibernate implementation of MenuDao
 */
public class HibernateMenuDao implements MenuDAO {

    private SessionFactory sessionFactory;
    private JpaCriteriaQueries<Menu> hDaoCriteriaQueries = new JpaCriteriaQueries();

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * finds list of all menus in DB
     * @return          list of menus
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional
    public List<Menu> getAll() {
        return hDaoCriteriaQueries.getAllEntityItems(sessionFactory, Menu.class);
    }

    /**
     * adds new menu to DB
     * @param menu      given menus
     */
    @Override
    @Transactional
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
    @Transactional
    public Menu search(int id) {
        try {
            return hDaoCriteriaQueries.searchItemById(sessionFactory, Menu.class, id);
        }
        catch (IndexOutOfBoundsException | NoResultException e) {
            throw new NoItemInDbException("There's no menu with id=" + id + " in database!");
        }
    }

    /**
     * searches menu in DB by name
     * @param name       name of menu to find
     * @return           found menu
     * throws            EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional
    public Menu search(String name) {
        try {
            return hDaoCriteriaQueries.searchItemByName(sessionFactory, Menu.class, name);
        }
        catch (IndexOutOfBoundsException e) {
            throw new NoItemInDbException("There's no menu with name \"" + name + "\" in database!");
        }
    }

    /**
     * deletes menu from DB by its ID
     * @param id            menu's ID to delete
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional
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
    @Transactional
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
    @Transactional
    public void addDish(Menu menu, Dish dish) {
        if (menu.getDishes().contains(dish)) {
            throw new ForbidToAddException("Menu " + menu.getName() + " already contains dish " + dish.getName());
        }
        else {
            Session session = sessionFactory.getCurrentSession();
            List <Dish> dishes = menu.getDishes();
            dishes.add(dish);
            menu.setDishes(dishes);
            session.update("Menu", menu);
        }
    }

    /**
     * deletes dish from menu
     * @param menu          dish menu
     * @param dish          dish to be deleted
     * throws               EmptyResultDataAccessException, DataAccessException
     */
    @Override
    @Transactional
    public void deleteDish(Menu menu, Dish dish) {
        Session session = sessionFactory.getCurrentSession();
        List <Dish> dishes = menu.getDishes();
        if (!dishes.remove(dish)) {
            throw new ForbidToDeleteException("Menu " + menu.getName() + " does not contain dish " + dish.getName(), "");
        }
        menu.setDishes(dishes);
        session.update("Menu", menu);
    }

    /**
     * updates menu in database
     * @param menu      menu to update in database
     */
    @Override
    @Transactional
    public void update(Menu menu) {
        Session session = sessionFactory.getCurrentSession();
        session.update("Menu", menu);
    }
}
