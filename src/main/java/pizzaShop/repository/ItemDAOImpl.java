package pizzaShop.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pizzaShop.entity.CategorizedItem;
import pizzaShop.entity.Category;
import pizzaShop.entity.Item;
import pizzaShop.entity.ItemForm;

import java.util.HashSet;
import java.util.Set;


@Repository
public class ItemDAOImpl extends GenericDAOImpl<Item, Long> implements ItemDAO {

    @Autowired
    CategoryDAO categoryDAO;

    public ItemDAOImpl() {
        super(Item.class);
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
        logger.info(mergedItem.getSetOfCategorizedItems());
        logger.info(getByID(18l).getSetOfCategorizedItems());
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
