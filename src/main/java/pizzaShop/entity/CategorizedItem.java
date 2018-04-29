package pizzaShop.entity;

import javax.persistence.*;
import javax.validation.Valid;
import java.io.Serializable;

@Entity
@Table(name = "CATEGORY_ITEM")
public class CategorizedItem implements Serializable{

    @EmbeddedId
    protected CompositeID id = new CompositeID();
    @ManyToOne()
    @JoinColumn(name = "CATEGORY_ID", updatable = false, insertable = false)
    @Valid
    Category category;
    @ManyToOne()
    @JoinColumn(name = "ITEM_ID", updatable = false, insertable = false)
    Item item;

        @Embeddable
        public static class CompositeID implements Serializable{

            @Column(name = "CATEGORY_ID")
            protected Integer categoryID;
            @Column(name = "ITEM_ID")
            protected Long itemID;

            public CompositeID() {
            }

            public CompositeID(Integer categoryID, Long itemID) {
                this.categoryID = categoryID;
                this.itemID = itemID;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                CompositeID that = (CompositeID) o;

                if (!categoryID.equals(that.categoryID)) return false;
                return itemID.equals(that.itemID);

            }

            @Override
            public int hashCode() {
                int result = categoryID != null ? categoryID.hashCode() : 0;
                result = 31 * result + (itemID != null ? itemID.hashCode() : 0);
                return result;
            }
        }

    public CategorizedItem() {
    }

    public CategorizedItem(Category category, Item item) {
        this.category = category;
        this.item = item;
        this.id.categoryID = category.getId();
        this.id.itemID = item.getId();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategorizedItem that = (CategorizedItem) o;

        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        return item != null ? item.equals(that.item) : that.item == null;

    }

    @Override
    public int hashCode() {
        int result = category != null ? category.hashCode() : 0;
        result = 31 * result + (item != null ? item.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CategorizedItem item: " + (item == null?"null" : item.toString())+ ", category: " + (category == null? "null" : category.toString());
    }
}
