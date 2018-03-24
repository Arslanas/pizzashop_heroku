package pizzaShop.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import pizzaShop.entity.User;
import pizzaShop.service.UserService;

import java.util.List;

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
    public String userRegistration(Model model) {
        model.addAttribute("user", new User());
        return "UserRegistration";
    }

    @RequestMapping(value = "/userRegistration", method = RequestMethod.POST)
    public String userRegistrationPost(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/admin/userManagement";
    }

    @RequestMapping(value = "/userManagementRest")
    @ResponseBody
    public Page<User> userManagementRest(Pageable pageable) {
        return userService.findAll(pageable);
    }

    @RequestMapping(value = "/userManagementRest/disable/{username}")
    @ResponseBody
    public User userDisable(@PathVariable("username") String username) {
        User user = userService.findByUsername(username);
        if(user.getEnabled() == true){
            user.setEnabled(false);
        }else {
            user.setEnabled(true);
        }

        logger.info(user);
        return userService.save(user);
    }

    @RequestMapping(value = "/userManagement")
    public String userManagement(Model model, Pageable pageable) {
        PageRequest pageRequest = new PageRequest(pageable.getPageNumber(), 2, pageable.getSort());
        model.addAttribute("page", userService.findAll(pageRequest));
        return "UserManagement";
    }
//    {"content":[{"username":"olya","password":"a","enabled":true,"contact":{"phoneNum":"79605896231","email":"olya@gmail.com"},"address":{"streetHome":"Feoktistova, 3","appartment":15,"entrance":1,"level":1},"date":"11-03-2018"}],"last":false,"totalPages":5,"totalElements":5,"size":1,"number":1,"sort":[{"direction":"ASC","property":"date","ignoreCase":false,"nullHandling":"NATIVE","ascending":true,"descending":false}],"numberOfElements":1,"first":false}

}
