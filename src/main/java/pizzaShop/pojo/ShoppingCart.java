package pizzaShop.pojo;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "totalPrice")
    private long totalPrice;
    @Column(name = "DATE", updatable = false, insertable = false)
    private LocalDateTime date;
    @ElementCollection
    @CollectionTable(name = "PRODUCTS", joinColumns = @JoinColumn(name = "SHOPPINGCART_ID"))
    private Set<Product> cart = new HashSet<>();

    public ShoppingCart() {
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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

    public long getTotalPrice() {
        calculateTotalPrice();
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    private ShoppingCart calculateTotalPrice(){
        setTotalPrice(cart.stream().map(e->e.getTotalPrice()).reduce(0l, (acc, element)-> acc+element));
        return this;
    }

    public Product getProductByItemId(Long id) {
        return  cart.stream().filter(product -> product.getItem().getId() == id).findFirst().get();
    }

    public boolean contains(Product product) {
        return cart.contains(product);
    }

    public Product getProductByItemID(long itemID){
       return cart.stream().filter(e-> e.getItem().getId()==itemID).findFirst().get();
    }

    public void removeFromCartByItemID(long itemID){
        Product p = cart.stream().filter(e-> e.getItem().getId()==itemID).findFirst().get();
        cart.remove(p);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShoppingCart that = (ShoppingCart) o;

        if (!username.equals(that.username)) return false;
        return cart.equals(that.cart);

    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + cart.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "username='" + username + '\'' +
                ", cart=" + cart +
                '}';
    }
}
