package pizzaShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pizzaShop.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    void deleteByUsername(String username);
}
