package pizzaShop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import pizzaShop.entity.CategorizedItem;
import pizzaShop.entity.Category;
import pizzaShop.entity.Item;

import java.util.Set;

public interface ItemService extends GenericService<Item, Long> {
    Page<Item> getItemsBySearchString(String search, Pageable pageable);
    Page<Item> getItemsByCategory(Category category, Pageable pageable);
    Item save(Item item, Set<CategorizedItem> categorySet);
    Item update(Item item);
    Item setPicture(Item item, MultipartFile picture);
}
