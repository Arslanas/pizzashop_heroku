package pizzaShop.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Temp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TEMP")
    @NotNull
    private String temp;

    public Temp() {
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Temp temp1 = (Temp) o;

        return temp.equals(temp1.temp);

    }

    @Override
    public int hashCode() {
        return temp.hashCode();
    }

}
