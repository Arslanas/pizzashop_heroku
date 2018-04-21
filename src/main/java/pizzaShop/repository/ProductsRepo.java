package pizzaShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pizzaShop.entity.Item;
import pizzaShop.entity.Product;

import java.util.List;

public interface ProductsRepo extends JpaRepository<Product, Long> {
    List<Product> findByItem(Item item);
}
