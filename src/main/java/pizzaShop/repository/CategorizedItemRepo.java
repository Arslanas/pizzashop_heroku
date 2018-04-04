package pizzaShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import pizzaShop.entity.CategorizedItem;
import pizzaShop.entity.Item;

public interface CategorizedItemRepo extends JpaRepository<CategorizedItem, CategorizedItem.CompositeID> {
    @Transactional
    void deleteByItem(Item item);
}
