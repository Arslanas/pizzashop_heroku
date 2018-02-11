package pizzaShop.pojo;

public class Contact {
    private String phoneNum;
    private String email;

    public Contact() {
    }

    public Contact(String phoneNum) {
        this.phoneNum = phoneNum;

    }

    public Contact(String phoneNum, String email) {
        this.phoneNum = phoneNum;
        this.email = email;
    }


    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (!phoneNum.equals(contact.phoneNum)) return false;
        return email != null ? email.equals(contact.email) : contact.email == null;

    }

    @Override
    public int hashCode() {
        int result = phoneNum.hashCode();
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "phoneNum='" + phoneNum + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
