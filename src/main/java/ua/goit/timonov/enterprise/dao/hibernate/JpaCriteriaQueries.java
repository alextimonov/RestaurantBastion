package ua.goit.timonov.enterprise.dao.hibernate;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * Processes operations with database generic items using JPA criteria queries
 */
public class JpaCriteriaQueries<T> {

    /**
     * returns list of all elements in database by given item's type
     * @param sessionFactory        session factory to access to database
     * @param clazz                 class of database item's type
     * @return                      found list of elements in database
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public List<T> getAllEntityItems(SessionFactory sessionFactory, Class<T> clazz) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<T> from = criteriaQuery.from(clazz);
        CriteriaQuery<T> select = criteriaQuery.select(from);
        TypedQuery<T> typedQuery = session.createQuery(select);
        return typedQuery.getResultList();
    }

    /**
     * finds list of all typed order in database by given item's type;
     * order's type can be open or closed
     * @param sessionFactory        session factory to access to database
     * @param clazz                 class of database item's type
     * @param closed                order's type (true if closed)
     * @return                      found list of order in database
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public List<T> getAllTypedOrders(SessionFactory sessionFactory, Class<T> clazz, boolean closed) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<T> from = criteriaQuery.from(clazz);
        CriteriaQuery<T> select = criteriaQuery.select(from);
        select.where(criteriaBuilder.equal(from.get("closed"), closed));
        TypedQuery<T> typedQuery = session.createQuery(select);
        return typedQuery.getResultList();
    }

    /**
     * finds element by given item's type and it's ID in database
     * @param sessionFactory        session factory to access to database
     * @param clazz                 class of database item's type
     * @param id                    element's ID
     * @return                      element by given item's type and it's ID
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public T searchItemById(SessionFactory sessionFactory, Class<T> clazz, int id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<T> from = criteriaQuery.from(clazz);
        CriteriaQuery<T> select = criteriaQuery.select(from);
        select.where(criteriaBuilder.equal(from.get("id"), id));
        TypedQuery<T> typedQuery = session.createQuery(select);
        return typedQuery.getSingleResult();
    }

    /**
     * finds element by given item's type and it's name or full name for employees
     * @param sessionFactory        session factory to access to database
     * @param clazz                 class of database item's type
     * @param fullName              element's name or full name for employees
     * @return                      element by given item's type and it's name
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public T searchItemByName(SessionFactory sessionFactory, Class<T> clazz, String... fullName) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<T> from = criteriaQuery.from(clazz);
        CriteriaQuery<T> select = criteriaQuery.select(from);
        if (nameIsBlank(fullName)) {
            if (fullName.length > 1 & !surnameIsBlank(fullName))
                select.where(criteriaBuilder.like(from.get("surname"), fullName[1]));
        }
        else {
            Predicate predicateName = criteriaBuilder.like(from.get("name"), fullName[0]);
            if (fullName.length > 1 && !surnameIsBlank(fullName))
                select.where(predicateName, criteriaBuilder.like(from.get("surname"), fullName[1]));
            else
                select.where(predicateName);
        }
        TypedQuery<T> typedQuery = session.createQuery(select);
        return typedQuery.getResultList().get(0);
    }

    // returns true if given surname is blank
    private boolean surnameIsBlank(String[] fullName) {
        return StringUtils.isBlank(fullName[1]);
    }

    // returns true if given name is blank
    private boolean nameIsBlank(String[] fullName) {
        return StringUtils.isBlank(fullName[0]);
    }

    /**
     * finds elements by given item's type and limited by max value (elements that less than given max value)
     * @param sessionFactory        session factory to access to database
     * @param clazz                 class of database item's type
     * @param limit                 max value
     * @return
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public List<T> getItemsLimitedByMaxValue(SessionFactory sessionFactory, Class<T> clazz, int limit) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<T> from = criteriaQuery.from(clazz);
        CriteriaQuery<T> select = criteriaQuery.select(from);
        select.where(criteriaBuilder.le(from.get("amount"), limit));
        TypedQuery<T> typedQuery = session.createQuery(select);
        return typedQuery.getResultList();
    }

    /**
     * updates value in database by item, field name and value in database
     * @param sessionFactory        session factory to access to database
     * @param clazz                 class of database item's type
     * @param id                    element's ID
     * @param fieldName             field's name
     * @param newValue              new value to be stored
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public void updateValue(SessionFactory sessionFactory, Class<T> clazz, int id, String fieldName, Object newValue) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaUpdate<T> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(clazz);
        Root<T> from = criteriaUpdate.from(clazz);
        criteriaUpdate.set(fieldName, newValue);
        criteriaUpdate.where(criteriaBuilder.equal(from.get("id"), id));
        session.createQuery(criteriaUpdate).executeUpdate();
    }

    /**
     * searches elements by given value
     * @param sessionFactory        session factory to access to database
     * @param clazz                 class of database item's type
     * @param fieldName             field's name
     * @param value                 given new value
     * @return                      found list of elements
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public List<T> searchItemsByValue(SessionFactory sessionFactory, Class<T> clazz, String fieldName, Object value) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<T> from = criteriaQuery.from(clazz);
        CriteriaQuery<T> select = criteriaQuery.select(from);
        select.where(criteriaBuilder.equal(from.get(fieldName), value));
        TypedQuery<T> typedQuery = session.createQuery(select);
        return typedQuery.getResultList();
    }

    /**
     * searches elements by starting characters in name
     * @param sessionFactory        session factory to access to database
     * @param clazz                 class of database item's type
     * @param fieldName             field's name
     * @param startChars            starting characters
     * @return                      found list of elements
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public List<T> getItemsStartWithChars(SessionFactory sessionFactory, Class<T> clazz, String fieldName, String startChars) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<T> from = criteriaQuery.from(clazz);
        CriteriaQuery<T> select = criteriaQuery.select(from);
        select.where(criteriaBuilder.like(from.get(fieldName), startChars+"%"));
        TypedQuery<T> typedQuery = session.createQuery(select);
        return typedQuery.getResultList();
    }
}
