package pizzaShop.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pizzaShop.entity.User;
import pizzaShop.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {


    private static Logger logger = Logger.getLogger(AdminController.class);
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/userRegistration")
    public String userRegistration(Model model){
        model.addAttribute("user", new User());
        return "UserRegistration";
    }
    @RequestMapping(value = "/userRegistration", method = RequestMethod.POST)
    public String userRegistrationPost(@ModelAttribute User user){
        userService.save(user);
        return "redirect:/admin/userManagement";
    }
    @RequestMapping(value = "/userManagement")
    public String userManagement(Model model){
        model.addAttribute("users", userService.findAll());
        return "UserManagement";
    }

}
