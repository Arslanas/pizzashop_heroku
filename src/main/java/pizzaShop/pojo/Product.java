package pizzaShop.pojo;

import pizzaShop.entity.Item;

public class Product {
    private final Item item;
    private int quantity = 1;

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
    public void increaseQuantity(int number){
        quantity+= number;
    }
    public long getTotalPrice(){
        return item.getPrice()*quantity;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (quantity != product.quantity) return false;
        return item.equals(product.item);

    }

    @Override
    public int hashCode() {
        int result = item.hashCode();
        result = 31 * result + quantity;
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "item=" + item +
                ", quantity=" + quantity +
                '}';
    }
}
