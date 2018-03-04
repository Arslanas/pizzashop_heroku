package pizzaShop.service;

import pizzaShop.entity.Category;
import pizzaShop.entity.Item;

import java.util.Set;

public interface CategoryService extends GenericService<Category, Integer> {
    Category findByName(String name);
    Set<Item> getSetOfItems(Category category);
}
