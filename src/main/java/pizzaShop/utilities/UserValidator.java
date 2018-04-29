package pizzaShop.utilities;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pizzaShop.entity.User;
import pizzaShop.service.UserService;

public class UserValidator implements Validator {

    UserService service;

    public UserValidator(UserService service) {
        this.service = service;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        User userEx = service.findByUsername(user.getUsername());
        if (userEx != null){
            if (userEx.getUsername().equals(user.getUsername())) {
                errors.rejectValue("username", "username", "Пользователь с таким именем уже существует");
            }
        }
    }
}
