package pizzaShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pizzaShop.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

    Category findByName(String name);
}
