package pizzaShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pizzaShop.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartRepo extends JpaRepository<ShoppingCart, Long> {
    List<ShoppingCart> findByUsernameOrderByDateDesc(String username);
}
