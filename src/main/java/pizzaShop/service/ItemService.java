package pizzaShop.service;

import pizzaShop.entity.Item;
import pizzaShop.entity.ItemForm;

import java.util.Set;

public interface ItemService extends GenericService<Item, Long> {
    Set<Item> getItemsByCategoryName(String category);
    Set<Item> getItemsBySearchString(String search);
}
