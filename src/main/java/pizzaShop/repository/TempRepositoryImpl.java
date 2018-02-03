package pizzaShop.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pizzaShop.entity.Temp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class TempRepositoryImpl implements TempRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Temp getTemp() {
        Query query = em.createQuery("select e from Temp e where e.id LIKE :num ");
        query.setParameter("num", 1L);
        return (Temp) query.getSingleResult();
    }
}
