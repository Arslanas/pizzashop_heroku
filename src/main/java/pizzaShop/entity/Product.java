package pizzaShop.entity;

import pizzaShop.entity.embedded.MonetaryAmount;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @Column(name = "QUANTITY")
    private int quantity = 1;

    @ManyToOne
    @JoinColumn(name = "SHOPPINGCART_ID")
    private ShoppingCart cart;

    public Product() {
    }

    public Product(Item item) {
        this.item = item;
    }


    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantity() {
        quantity++;
    }

    public void decreaseQuantity() {
        if (quantity > 1) quantity--;
    }

    public void increaseQuantity(int number) {
        quantity += number;
    }

    public MonetaryAmount getTotalPrice() {
        return new MonetaryAmount(item.getPrice().getAmount().doubleValue() * quantity);
    }

    public Long getId() {
        return id;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return item != null ? item.equals(product.item) : product.item == null;

    }

    @Override
    public int hashCode() {
        return item != null ? item.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Product{" +
                "item=" + item +
                ", quantity=" + quantity +
                '}';
    }
}
