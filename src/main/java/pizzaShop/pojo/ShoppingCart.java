package pizzaShop.pojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShoppingCart {
    private Set<Product> cart = new HashSet<>();

    public ShoppingCart() {
    }

    public Set<Product> getCart() {
        return cart;
    }

    public void setCart(Set<Product> cart) {
        this.cart = cart;
    }

    public boolean add(Product product) {
        return cart.add(product);
    }

    public Product getProductByItemId(Long id) {
        for (Product p : cart) {
            if (p.getItem().getId() == id) return p;
        }
        return null;
    }

    public boolean contains(Product product) {
        return cart.contains(product);
    }

    public long getFinalPrice() {
        long finalPrice = 0;
        for (Product p : cart) {
            finalPrice += p.getTotalPrice();
        }
        return finalPrice;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShoppingCart that = (ShoppingCart) o;

        return cart.equals(that.cart);

    }

    @Override
    public int hashCode() {
        return cart.hashCode();
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "cart=" + cart +
                '}';
    }
}
