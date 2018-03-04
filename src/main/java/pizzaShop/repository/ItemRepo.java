package pizzaShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pizzaShop.entity.Item;

import java.util.List;
import java.util.Set;

public interface ItemRepo extends JpaRepository<Item, Long> {
    Set<Item> findByNameContainingIgnoreCase(String name);
}
