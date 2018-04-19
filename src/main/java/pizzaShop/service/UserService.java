package pizzaShop.service;

import pizzaShop.entity.User;

public interface UserService extends GenericService<User, Long> {
    User findByUsername(String username);
    User update(User user);
}
