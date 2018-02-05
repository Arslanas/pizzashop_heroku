package pizzaShop.repository;

import pizzaShop.entity.Category;

import java.util.List;

public interface CategoryDAO extends GenericDAO<Category, Integer> {
    Category getCategoryByName(String name);
}
