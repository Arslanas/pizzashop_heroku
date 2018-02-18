package pizzaShop.entity;

import pizzaShop.pojo.Address;
import pizzaShop.pojo.Contact;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "USERNAME")
    @NotNull
    private String username;
    @Column(name = "PASSWORD")
    @NotNull
    private String password;
    @Column(name = "ENABLED")
    @NotNull
    private Boolean enabled = true;
    @NotNull
    private Contact contact = new Contact();
    @NotNull
    private Address address = new Address();

    @ElementCollection
    @CollectionTable(name = "AUTHORITIES", joinColumns = @JoinColumn(name = "USERNAME", referencedColumnName = "USERNAME"))
    @Column(name = "authority")
    Set<String> authorities ;


    public User() {
        authorities = new HashSet<>();
        authorities.add("USER");
    }

    public User(String username, String password, Boolean enabled) {
        this();
        this.username = username;
        this.password = password;
        this.enabled = enabled;

    }

    public User(String username, String password, Boolean enabled, Contact contact, Address address) {
        this(username, password, enabled);
        this.contact = contact;
        this.address = address;
    }


    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!username.equals(user.username)) return false;
        if (!password.equals(user.password)) return false;
        if (!enabled.equals(user.enabled)) return false;
        if (!contact.equals(user.contact)) return false;
        return address.equals(user.address);

    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + enabled.hashCode();
        result = 31 * result + contact.hashCode();
        result = 31 * result + address.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", contact=" + contact +
                ", address=" + address +
                '}';
    }
}