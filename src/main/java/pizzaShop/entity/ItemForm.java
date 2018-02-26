package pizzaShop.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

public class ItemForm {
    @NotEmpty(message = "Введите имя")
    @Size(max = 16, message = "Имя не должно превышать 16 символов")
    private String name;
    @Digits(integer = 9, fraction = 2, message = "Только числа")
    @DecimalMin(value = "5", message = "Установите цену не ниже 5")
    private int price = 0;
    @NotEmpty(message = "Опишите товар")
    @Size(max = 100, message = "Опишите товар")
    private String description;
    @NotEmpty(message = "Выберите категории")
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

        if (price != itemForm.price) return false;
        return name != null ? name.equals(itemForm.name) : itemForm.name == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + price;
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
