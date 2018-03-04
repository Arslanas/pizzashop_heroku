package pizzaShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pizzaShop.entity.CategorizedItem;

public interface CategorizedItemRepo extends JpaRepository<CategorizedItem, CategorizedItem.CompositeID> {
}
