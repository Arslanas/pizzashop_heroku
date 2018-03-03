package pizzaShop.repository;

import pizzaShop.entity.Item;
import pizzaShop.entity.ItemForm;

import java.util.Set;

public interface ItemCustomRepository {
    Item makePersistent(ItemForm itemForm);
    Set<Item> getItemsByCategoryName(String category);
    Set<Item> getItemsBySearchString(String search);
}
