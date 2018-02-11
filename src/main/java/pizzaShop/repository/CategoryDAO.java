package pizzaShop.repository;

import pizzaShop.entity.Category;
import pizzaShop.entity.Item;

import java.util.List;
import java.util.Set;

public interface CategoryDAO extends GenericDAO<Category, Integer> {
    Category getCategoryByName(String name);
    Set<Item> getSetOfItems(Category category);
}
