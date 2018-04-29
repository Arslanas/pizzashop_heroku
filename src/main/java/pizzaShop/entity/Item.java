package pizzaShop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import pizzaShop.entity.embedded.Image;
import pizzaShop.entity.embedded.MonetaryAmount;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ITEM")
public class Item implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "NAME")
    @NotBlank(message = "Заполните поле")
    private String name;

    @Valid
    @NotNull(message = "Назначьте цену")
    private MonetaryAmount price = new MonetaryAmount(0);

    @Column(name = "DESCRIPTION")
    private String description;

    private Image image = new Image();

    @JsonIgnore
    @OneToMany(mappedBy = "item", fetch = FetchType.EAGER, orphanRemoval = true)
    @NotEmpty(message = "Выберите категорию")
    private Set<CategorizedItem> categorizedItems = new HashSet<>();

    public Item() {
    }

    public Item(String name, MonetaryAmount price, String description, Set<CategorizedItem> categorizedItems) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.categorizedItems = categorizedItems;
    }

    public Item(String name, MonetaryAmount price, String description, Image image, Set<CategorizedItem> categorizedItems) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.categorizedItems = categorizedItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MonetaryAmount getPrice() {
        return price;
    }

    public void setPrice(MonetaryAmount price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<CategorizedItem> getCategorizedItems() {
        return categorizedItems;
    }

    public void setCategorizedItems(Set<CategorizedItem> setOfCategorizedItems) {
        this.categorizedItems = setOfCategorizedItems;
    }

    @JsonIgnore
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item other = (Item) o;

        if (!name.equals(other.name)) return false;
        if (!price.equals(other.price)) return false;
        return description != null ? description.equals(other.description) : other.description == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
