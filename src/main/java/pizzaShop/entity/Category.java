package pizzaShop.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CATEGORY")
public class Category implements Comparable<Category>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Column(name = "NAME")
    @NotBlank(message = "Выберите одну или несколько категорий")
    private String name;
    @NotNull
    @Column(name = "ORDERS")
    private int order;

    @OneToMany(mappedBy = "category")
    Set<CategorizedItem> setOfCategorizedItems = new HashSet<>();

    public Category() {
    }

    public Category(String name, int order, Set<CategorizedItem> setOfCategorizedItems) {
        this.name = name;
        this.order = order;
        this.setOfCategorizedItems = setOfCategorizedItems;
    }

    @Override
    public int compareTo(Category o) {

        int result = order < o.order ? -1 : (order > o.order ? 1 : 0);
        if (result == 0) {
            result = name.compareTo(o.name);
        }
        return result;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public Set<CategorizedItem> getSetOfCategorizedItems() {
        return setOfCategorizedItems;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (order != category.order) return false;
        return name.equals(category.name);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + order;
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", order=" + order +
                '}';
    }
}
