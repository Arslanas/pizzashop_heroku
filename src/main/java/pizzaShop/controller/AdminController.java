package pizzaShop.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import pizzaShop.repository.CategoryDAO;
import pizzaShop.repository.ItemDAO;
import pizzaShop.repository.TempRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private static Logger logger = Logger.getLogger(ProductsController.class);
    private final TempRepository repo;
    private final CategoryDAO categoryDAO;
    private final ItemDAO itemDAO;

    @Autowired
    public AdminController(TempRepository repo, CategoryDAO categoryDAO, ItemDAO itemDAO) {
        Assert.notNull(repo, "TempRepository is null");
        Assert.notNull(categoryDAO, "CategoryDAO is null");
        Assert.notNull(itemDAO, "ItemDAO is null");
        this.repo = repo;
        this.categoryDAO = categoryDAO;
        this.itemDAO = itemDAO;
    }

    @RequestMapping("/userRegistration")
    public String userRegistration(){
        return "UserRegistration";
    }
}
