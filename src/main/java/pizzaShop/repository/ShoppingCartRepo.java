package pizzaShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pizzaShop.entity.ShoppingCart;

public interface ShoppingCartRepo extends JpaRepository<ShoppingCart, Long> {
}
