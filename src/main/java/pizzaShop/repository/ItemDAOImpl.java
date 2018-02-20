package pizzaShop.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pizzaShop.entity.CategorizedItem;
import pizzaShop.entity.Category;
import pizzaShop.entity.Item;
import pizzaShop.entity.ItemForm;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transaction;
import java.util.*;


@Repository
public class ItemDAOImpl extends GenericDAOImpl<Item, Long> implements ItemDAO {

    @Autowired
    CategoryDAO categoryDAO;

    public ItemDAOImpl() {
        super(Item.class);
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
    public Item update(Item item) {
        Item toUpdate = em.find(Item.class, item.getId());
        toUpdate.setPrice(item.getPrice());
        toUpdate.setDescription(item.getDescription());
        toUpdate.setName(item.getName());
        logger.info(toUpdate);
        em.merge(toUpdate);
        return toUpdate;
    }

    @Transactional
    @Override
    public Item makePersistent(ItemForm itemForm){
        Item item = new Item();
        item.setName(itemForm.getName());
        item.setDescription(itemForm.getDescription());
        item.setPrice(itemForm.getPrice());
        Item mergedItem = makePersistent(item);
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
