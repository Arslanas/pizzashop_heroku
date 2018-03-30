package pizzaShop.utilities;

import javax.persistence.AttributeConverter;

public class CurrencyConverterJPA implements AttributeConverter<String, String> {
    @Override
    public String convertToDatabaseColumn(String s) {
        if (s.equals("&#8381")) return "RUB";
        else
            return s;
    }

    @Override
    public String convertToEntityAttribute(String s) {
        if (s.equals("RUB")) return "&#8381";
        else
            return s;
    }
}
