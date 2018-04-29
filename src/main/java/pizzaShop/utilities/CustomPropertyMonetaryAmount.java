package pizzaShop.utilities;

import pizzaShop.entity.embedded.MonetaryAmount;
import java.beans.PropertyEditorSupport;


public class CustomPropertyMonetaryAmount extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(new MonetaryAmount(Double.valueOf(text)));
    }
}
