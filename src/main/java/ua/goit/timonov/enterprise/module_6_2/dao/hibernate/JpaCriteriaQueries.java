package ua.goit.timonov.enterprise.module_6_2.dao.hibernate;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by Alex on 20.08.2016.
 */
public class JpaCriteriaQueries<T> {

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

    private boolean surnameIsBlank(String[] fullName) {
        return StringUtils.isBlank(fullName[1]);
    }

    private boolean nameIsBlank(String[] fullName) {
        return StringUtils.isBlank(fullName[0]);
    }

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
