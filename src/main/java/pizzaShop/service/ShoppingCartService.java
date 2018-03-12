package pizzaShop.service;

import pizzaShop.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService extends GenericService<ShoppingCart, Long> {
    List<ShoppingCart> findByUsername(String username);
}
