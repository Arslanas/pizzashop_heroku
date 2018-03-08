package pizzaShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pizzaShop.entity.Temp;

@Repository
public interface TempRepository extends JpaRepository<Temp, Integer>{
}
