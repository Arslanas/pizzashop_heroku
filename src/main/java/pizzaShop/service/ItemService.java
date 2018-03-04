package pizzaShop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pizzaShop.entity.Category;
import pizzaShop.entity.Item;
import pizzaShop.entity.ItemForm;

import java.util.Set;

public interface ItemService extends GenericService<Item, Long> {
    Page<Item> getItemsBySearchString(String search, Pageable pageable);
    Page<Item> getItemsByCategory(Category category, Pageable pageable);
}
