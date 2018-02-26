package pizzaShop.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pizzaShop.entity.ItemForm;

public class ItemFormValidator implements Validator{


    @Override
    public boolean supports(Class<?> aClass) {
        return ItemForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ItemForm itemForm = (ItemForm) o;
        if (itemForm.getPrice() == null){
            errors.rejectValue("price", "itemForm.price", "It can`t be null");
        }
    }
}
