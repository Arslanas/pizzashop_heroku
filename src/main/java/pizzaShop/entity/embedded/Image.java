package pizzaShop.entity.embedded;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Arrays;

@Embeddable
public class Image {

    @Column(name = "IMAGE")
    private byte[] picture;

    public Image() {
    }
    public Image(byte[] image) {
        this.picture = image;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] image) {
        this.picture = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image1 = (Image) o;

        return Arrays.equals(picture, image1.picture);

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(picture);
    }
}
