package pizzaShop.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class ItemForm {
    @NotNull
    private String name;
    @NotNull
    private Integer price;
    private String description;
    private Set<String> setOfCategorizedItems;

    public ItemForm() {
    }

    public ItemForm(String name, Integer price, String description, Set<String> setOfCategorizedItems) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.setOfCategorizedItems = setOfCategorizedItems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getSetOfCategorizedItems() {
        return setOfCategorizedItems;
    }

    public void setSetOfCategorizedItems(Set<String> setOfCategorizedItems) {
        this.setOfCategorizedItems = setOfCategorizedItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemForm itemForm = (ItemForm) o;

        if (!name.equals(itemForm.name)) return false;
        return price.equals(itemForm.price);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ItemForm{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", setOfCategorizedItems=" + setOfCategorizedItems +
                '}';
    }
}
