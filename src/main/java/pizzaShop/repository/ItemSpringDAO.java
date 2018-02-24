package pizzaShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pizzaShop.entity.Item;

public interface ItemSpringDAO extends JpaRepository<Item, Long> {
}
