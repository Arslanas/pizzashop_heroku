package pizzaShop.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pizzaShop.entity.ShoppingCart;
import pizzaShop.entity.User;
import pizzaShop.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private static Logger logger = Logger.getLogger(UserController.class);
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;


    @Autowired
    public UserController(UserService userService, ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }


    @RequestMapping("/registration")
    public String userRegistration(Model model) {
        model.addAttribute("user", new User());
        return "UserRegistration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String userRegistrationPost(@ModelAttribute User user) {

        userService.save(user);
        return "RegistrationSuccess";
    }


    @RequestMapping("/details")
    public String userDetails(Model model) {
        model.addAttribute("user", userService.findByUsername(getUsername()));
        return "User_details";
    }

    @RequestMapping("/detailsEdit")
    public String userDetailsEdit(Model model) {
        model.addAttribute("user", userService.findByUsername(getUsername()));
        return "User_details_edit";
    }

    @RequestMapping(value = "/detailsEdit", method = RequestMethod.POST)
    public String userDetailsEditPost(@ModelAttribute User user) {
        userService.update(user);
        return "redirect:/products";
    }
    @RequestMapping(value = "/remove/{username}", method = RequestMethod.POST)
    public String userDetailsRemovePost(@PathVariable("username") String username, HttpServletRequest request)throws Exception {
        userService.deleteByUsername(username);
        request.logout();
        return "redirect:/";
    }

    @RequestMapping("/orders")
    public String userOrders(Model model) {
        List<ShoppingCart> cartList = shoppingCartService.findByUsername(getUsername());
        if (cartList.size() > 0) {
            model.addAttribute("sCarts", cartList);
            return "User_orders";
        }
        return "User_orders_empty";

    }


    private String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
