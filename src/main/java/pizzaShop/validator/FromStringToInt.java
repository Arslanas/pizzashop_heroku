package pizzaShop.validator;

import org.springframework.core.convert.converter.Converter;

import java.beans.PropertyEditorSupport;

public class FromStringToInt extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        int value = 0 ;
        if(text.length() > 0) value = Integer.valueOf(text);
        setValue(value);
    }
}
