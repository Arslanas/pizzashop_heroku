package pizzaShop.repository;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import pizzaShop.entity.Category;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CategoryDAOImpl extends GenericDAOImpl<Category, Integer> implements CategoryDAO{


    static Logger logger = Logger.getLogger(CategoryDAOImpl.class);

    public CategoryDAOImpl(){
        super(Category.class);
    }



    @Override
    public Category getCategoryByName(String name) {
        CriteriaQuery<Category> query = em.getCriteriaBuilder().createQuery(Category.class);
        Root<Category> root = query.from(Category.class);
        Predicate predicate = em.getCriteriaBuilder().equal(root.get("name"), name);
        query.select(root).where(predicate);
        return  em.createQuery(query).getSingleResult();
    }
}
