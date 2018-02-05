package pizzaShop.repository;

import pizzaShop.entity.CategorizedItem;
import pizzaShop.entity.Item;
import pizzaShop.entity.ItemForm;

public interface ItemDAO extends GenericDAO<Item, Long>{

    Item makePersistent(ItemForm itemForm);
}
