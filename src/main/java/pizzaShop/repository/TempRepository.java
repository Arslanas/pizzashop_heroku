package pizzaShop.repository;

import pizzaShop.entity.Category;
import pizzaShop.entity.Item;
import pizzaShop.entity.Temp;

import java.util.List;


public interface TempRepository {
    Temp getTemp();

    List<Category> getCategories();

    List<Item> getItems();

    Category getCategoryById(Integer id);

    List<Item> getItemsByCategory(Category category);
}
