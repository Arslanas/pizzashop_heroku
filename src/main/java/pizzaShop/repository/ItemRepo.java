package pizzaShop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pizzaShop.entity.Category;
import pizzaShop.entity.Item;

import java.util.List;
import java.util.Set;

public interface ItemRepo extends JpaRepository<Item, Long> {
    Page<Item> findByNameContainingIgnoreCase(String name, Pageable pageable);

    @Query(value = "select i from Item i join i.setOfCategorizedItems c where c.category = :category")
    Page<Item> findByCategory(@Param(value = "category")Category category, Pageable pageable);
}
