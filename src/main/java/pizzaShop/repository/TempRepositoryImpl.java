package pizzaShop.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pizzaShop.entity.CategorizedItem;
import pizzaShop.entity.Category;
import pizzaShop.entity.Item;
import pizzaShop.entity.Temp;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class TempRepositoryImpl implements TempRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Temp getTemp() {
        Query query = em.createQuery("select e from Temp e where e.id LIKE :num ");
        query.setParameter("num", 1L);
        return (Temp) query.getSingleResult();
    }

    @Override
    public List<Category> getCategories() {
//        TypedQuery<Category> query = em.createQuery("select e from Category e ", Category.class);
//        return query.getResultList();
//        CriteriaQuery<Category> query = em.getCriteriaBuilder().createQuery(Category.class);
//        query.select(query.from(Category.class));
//        return em.createQuery(query).getResultList();
//        CategoryDAO dao = new CategoryDAOImpl();
//        return dao.getAll();
        return null;
    }

    @Override
    public Category getCategoryById(Integer id) {
        TypedQuery<Category> query = em.createQuery("select e from Category e where e.id LIKE :id ", Category.class).setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Item> getItems() {
        TypedQuery<Item> query = em.createQuery("select e from Item e ", Item.class);
        return query.getResultList();
    }

    @Override
    public List<Item> getItemsByCategory(Category category) {
        TypedQuery<CategorizedItem> query = em.createQuery("select e from CategorizedItem e where e.id.categoryID like :id", CategorizedItem.class).setParameter("id", category.getId());
        List<Item> items = new ArrayList<>();
        for (CategorizedItem categorizedItem :
                query.getResultList()) {
            items.add(categorizedItem.getItem());
        }
        return items;
    }
}
