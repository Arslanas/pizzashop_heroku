package pizzaShop.repository;

import pizzaShop.entity.CategorizedItem;
import pizzaShop.entity.Item;
import pizzaShop.entity.ItemForm;

import java.util.List;
import java.util.Set;

public interface ItemDAO extends GenericDAO<Item, Long>{

    Item makePersistent(ItemForm itemForm);
    Set<Item> getItemsByCategoryName(String category);
}
