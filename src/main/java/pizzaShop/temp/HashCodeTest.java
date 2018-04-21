package pizzaShop.temp;

import pizzaShop.entity.Item;
import pizzaShop.entity.embedded.MonetaryAmount;
import pizzaShop.entity.Product;

import java.util.HashSet;
import java.util.Set;

public class HashCodeTest {
    public static void main(String[] args) {
        Item itemA = new Item("A", new MonetaryAmount(33d), "a", null);
        Item itemB = new Item("A", new MonetaryAmount(33d), "a", null);
        itemA.setId(11l);
        itemB.setId(11l);
        Set<Product> set = new HashSet();
        Product productA = new Product(itemA);
        Product productB = new Product(itemB);
        set.add(productA);
        set.add(productB);
        set.forEach(e-> System.out.println(e.hashCode()));
        System.out.println(productA.equals(productB));
        System.out.println(itemA.equals(itemB));
        System.out.println(itemA.getPrice().equals(itemB.getPrice()));
        System.out.println(new MonetaryAmount(33d).getAmount().equals(new MonetaryAmount(33d).getAmount()));

    }
}
