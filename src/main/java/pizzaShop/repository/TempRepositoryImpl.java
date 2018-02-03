package pizzaShop.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pizzaShop.entity.Temp;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
public class TempRepositoryImpl implements TempRepository{

    @Autowired
    EntityManager em;

    public TempRepositoryImpl() {
    }


    @Override
    public Temp getTemp() {
        Query query = em.createQuery("select temp from Temp temp where temp.id = 1");

        return  (Temp)query.getSingleResult();
    }
}
