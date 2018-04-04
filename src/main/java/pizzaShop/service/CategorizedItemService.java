package pizzaShop.service;

import pizzaShop.entity.CategorizedItem;
import pizzaShop.entity.Item;

public interface CategorizedItemService extends GenericService<CategorizedItem, CategorizedItem.CompositeID> {
    void delete(Item item);

}
