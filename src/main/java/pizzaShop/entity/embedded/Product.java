package pizzaShop.entity.embedded;

import pizzaShop.entity.Item;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class Product {

    @ManyToOne
    @JoinColumn(name = "ITEM_ID" )
    private Item item;
    @Column(name = "QUANTITY")
    private int quantity = 1;

    public Product() {
    }

    public Product(Item item){
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

    public void increaseQuantity(){
        quantity++;
    }
    public void decreaseQuantity(){
       if(quantity > 1) quantity--;
    }
    public void increaseQuantity(int number){
        quantity += number;
    }
    public long getTotalPrice(){
        return item.getPrice()*quantity;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;
        return item.equals(product.item);

    }

    @Override
    public int hashCode() {
        return item.hashCode();
    }

    @Override
    public String toString() {
        return "Product{" +
                "item=" + item +
                ", quantity=" + quantity +
                '}';
    }
}
