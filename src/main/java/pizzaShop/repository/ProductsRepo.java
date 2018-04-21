package pizzaShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pizzaShop.entity.Product;

public interface ProductsRepo extends JpaRepository<Product, Long> {

}
