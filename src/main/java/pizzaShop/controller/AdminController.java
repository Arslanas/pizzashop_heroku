package pizzaShop.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaShop.entity.User;
import pizzaShop.repository.CategoryDAO;
import pizzaShop.repository.ItemDAO;
import pizzaShop.repository.TempRepository;
import pizzaShop.repository.UserDAO;

@Controller
@RequestMapping("/admin")
public class AdminController {


    private static Logger logger = Logger.getLogger(AdminController.class);
    private final TempRepository repo;
    private final CategoryDAO categoryDAO;
    private final ItemDAO itemDAO;
    private final UserDAO userDAO;

    @Autowired
    public AdminController(TempRepository repo, CategoryDAO categoryDAO, ItemDAO itemDAO, UserDAO userDAO) {
        Assert.notNull(repo, "TempRepository is null");
        Assert.notNull(categoryDAO, "CategoryDAO is null");
        Assert.notNull(itemDAO, "ItemDAO is null");
        this.repo = repo;
        this.categoryDAO = categoryDAO;
        this.itemDAO = itemDAO;
        this.userDAO = userDAO;
    }

    @RequestMapping("/userRegistration")
    public String userRegistration(Model model){
        model.addAttribute("user", new User());
        return "UserRegistration";
    }
    @RequestMapping(value = "/userRegistration", method = RequestMethod.POST)
    public String userRegistrationPost(@ModelAttribute User user){
        logger.info(user);
        userDAO.makePersistent(user);
        return "redirect:/admin/userManagement";
    }
    @RequestMapping(value = "/userManagement")
    public String userManagement(Model model){
        model.addAttribute("users", userDAO.getAll());
        return "UserManagement";
    }
}
