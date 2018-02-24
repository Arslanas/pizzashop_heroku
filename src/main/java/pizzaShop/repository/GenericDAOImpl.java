package pizzaShop.repository;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;


public abstract class GenericDAOImpl<T, ID> implements GenericDAO<T, ID>{

    static Logger logger = Logger.getLogger(GenericDAOImpl.class);

    @PersistenceContext
    protected EntityManager em;

    protected final Class<T> entityClass;



    protected GenericDAOImpl(Class<T> entityClass){
        this.entityClass = entityClass;
    }



    @Override
    public List<T> getAll() {
        CriteriaQuery<T> c =  em.getCriteriaBuilder().createQuery(entityClass);
        c.select(c.from(entityClass));
        return em.createQuery(c).getResultList();
    }

    @Override
    public T findById(ID id) {
        return em.find(entityClass,id);
    }

    @Transactional
    @Override
    public T makePersistent(T t) {
        return  em.merge(t);
    }

    @Transactional
    @Override
    public void makeTransient(T t) {
        em.remove(t);
    }

    @Override
    public Long getCount() {
        CriteriaQuery<Long> query = em.getCriteriaBuilder().createQuery(Long.class);
        query.select(em.getCriteriaBuilder().count(query.from(entityClass)));
        return em.createQuery(query).getSingleResult();
    }
}
