package pizzaShop.repository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pizzaShop.entity.CategorizedItem;
import pizzaShop.entity.Category;
import pizzaShop.entity.Item;
import pizzaShop.entity.ItemForm;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.Set;

@Repository
public class ItemCustomRepositoryImpl implements ItemCustomRepository {


    static Logger logger = Logger.getLogger(ItemCustomRepositoryImpl.class);

    @PersistenceContext
    EntityManager em;
    CategoryDAO categoryDAO;
    ItemSpringDAO itemSpringDAO;

    @Autowired
    public ItemCustomRepositoryImpl(CategoryDAO categoryDAO, ItemSpringDAO itemSpringDAO) {
        this.categoryDAO = categoryDAO;
        this.itemSpringDAO = itemSpringDAO;
    }



    @Override
    public Set<Item> getItemsByCategoryName(String categoryName){
        Category category = categoryDAO.getCategoryByName(categoryName);
        return categoryDAO.getSetOfItems(category);
    };

    @Override
    public Set<Item> getItemsBySearchString(String search) {
        search = ("%"+search+"%");
        String q = "select e from Item e where e.name like :name ";
        TypedQuery<Item> query = em.createQuery(q, Item.class).setParameter("name", search);
        return new HashSet(query.getResultList());
    }


    @Transactional
    @Override
    public Item makePersistent(ItemForm itemForm){
        Item item = new Item();
        item.setName(itemForm.getName());
        item.setDescription(itemForm.getDescription());
        item.setPrice(itemForm.getPrice());
        Item mergedItem = itemSpringDAO.save(item);
        createCategorizedItems(itemForm.getSetOfCategorizedItems(), mergedItem);
        return mergedItem;
    }

    private CategorizedItem persistCategorizedItem(CategorizedItem categorizedItem) {
        return em.merge(categorizedItem);
    }

    // improve - double hit to DB
    private void createCategorizedItems(Set<String> categoryNames, Item item){
        for (String categoryName : categoryNames) {
            Category category = categoryDAO.getCategoryByName(categoryName);
            persistCategorizedItem(new CategorizedItem(category, item));
        }
    }
}
