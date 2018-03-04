package pizzaShop.entity.embedded;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class Address {

    @Column(name = "STREETHOME")
    @NotNull
    private String streetHome;
    @Column(name = "APPARTMENT")
    private int appartment;
    @Column(name = "ENTRANCE")
    private int entrance;
    @Column(name = "LEVEL")
    private int level;

    public Address() {
    }

    public Address(String streetHome) {
        this.streetHome = streetHome;
    }

    public Address(String streetHome, int appartment, int entrance, int level) {
        this.streetHome = streetHome;
        this.appartment = appartment;
        this.entrance = entrance;
        this.level = level;
    }

    public String getStreetHome() {
        return streetHome;
    }

    public void setStreetHome(String streetHome) {
        this.streetHome = streetHome;
    }

    public int getAppartment() {
        return appartment;
    }

    public void setAppartment(int appartment) {
        this.appartment = appartment;
    }

    public int getEntrance() {
        return entrance;
    }

    public void setEntrance(int entrance) {
        this.entrance = entrance;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address1 = (Address) o;

        if (appartment != address1.appartment) return false;
        if (entrance != address1.entrance) return false;
        if (level != address1.level) return false;
        return streetHome.equals(address1.streetHome);

    }

    @Override
    public int hashCode() {
        int result = streetHome.hashCode();
        result = 31 * result + appartment;
        result = 31 * result + entrance;
        result = 31 * result + level;
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetHome='" + streetHome + '\'' +
                ", appartment=" + appartment +
                ", entrance=" + entrance +
                ", level=" + level +
                '}';
    }
}
