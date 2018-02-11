package pizzaShop.repository;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import pizzaShop.entity.CategorizedItem;
import pizzaShop.entity.Category;
import pizzaShop.entity.Item;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class CategoryDAOImpl extends GenericDAOImpl<Category, Integer> implements CategoryDAO{


    private static Logger logger = Logger.getLogger(CategoryDAOImpl.class);

    public CategoryDAOImpl(){
        super(Category.class);
    }



    @Override
    public Set<Item> getSetOfItems(Category category) {
        Set<Item> setOfItems = new HashSet<>();
        for (CategorizedItem catItem :
                category.getSetOfCategorizedItems()) {
            setOfItems.add(catItem.getItem());
        }
        return setOfItems;
    }

    @Override
    public Category getCategoryByName(String name) {
        CriteriaQuery<Category> query = em.getCriteriaBuilder().createQuery(Category.class);
        Root<Category> root = query.from(Category.class);
        Predicate predicate = em.getCriteriaBuilder().equal(root.get("name"), name);
        query.select(root).where(predicate);
        return  em.createQuery(query).getSingleResult();
    }

    //??????????
    public List<String> getCategoryName(List<Category> categories){
        List<String> categoryNames = new ArrayList<>();
        for (Category category : categories) {
            categoryNames.add(category.getName());
        }
        return categoryNames;
    }
}
