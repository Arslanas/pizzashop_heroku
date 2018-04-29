package pizzaShop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import pizzaShop.entity.embedded.MonetaryAmount;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "totalPrice")
    private MonetaryAmount totalPrice = new MonetaryAmount(0d);

    @Column(name = "DATE", updatable = false)
    private LocalDateTime date;

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})
    @JsonManagedReference
    private Set<Product> cart = new HashSet<>();

    public ShoppingCart() {
    }

    @JsonIgnore
    public String getFormattedDate() {
        return date.toLocalDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(new Locale("ru")));
    }
    @JsonIgnore
    public String getFormattedTime() {
        return date.toLocalTime().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(new Locale("ru")));
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
        if (!cart.contains(product)) {
            product.setCart(this);
            return cart.add(product);
        } else {
            getProductByItemId(product.getItem().getId()).increaseQuantity();
            return false;
        }
    }

    public Product getProductByItemId(long id) {
        return  cart.stream().filter(product -> product.getItem().getId() == id).findFirst().get();
    }

    public MonetaryAmount getTotalPrice() {
        calculateTotalPrice();
        return totalPrice;
    }

    public void setTotalPrice(MonetaryAmount totalPrice) {
        this.totalPrice = totalPrice;
    }

    private ShoppingCart calculateTotalPrice(){
        setTotalPrice(cart.stream().map(e->e.getTotalPrice()).reduce(new MonetaryAmount(0d), (acc, element)-> acc.plus(element)));
        return this;
    }

    public boolean contains(Product product) {
        return cart.contains(product);
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