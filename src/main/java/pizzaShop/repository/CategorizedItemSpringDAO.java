package pizzaShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pizzaShop.entity.CategorizedItem;

public interface CategorizedItemSpringDAO extends JpaRepository<CategorizedItem, CategorizedItem.CompositeID> {
}
