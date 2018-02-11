package pizzaShop.pojo;

public class Customer {
    private String name;
    private Contact contact;
    private Address address;

    public Customer() {
    }

    public Customer(String name, Contact contact, Address address) {
        this.name = name;
        this.contact = contact;
        this.address = address;
    }

    public static Customer getCustomer(){
        return new Customer("", new Contact(""), new Address(""));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        Customer that = (Customer) o;

        if (!name.equals(that.name)) return false;
        if (!contact.equals(that.contact)) return false;
        return address.equals(that.address);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + contact.hashCode();
        result = 31 * result + address.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", contact=" + contact +
                ", address=" + address +
                '}';
    }
}
