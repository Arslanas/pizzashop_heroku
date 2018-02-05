package pizzaShop.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pizzaShop.entity.CategorizedItem;
import pizzaShop.entity.Category;
import pizzaShop.entity.Item;
import pizzaShop.entity.ItemForm;
import pizzaShop.repository.CategoryDAO;
import pizzaShop.repository.ItemDAO;
import pizzaShop.repository.TempRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {

    static Logger logger = Logger.getLogger(TestController.class);

    @Autowired
    TempRepository repo;

    @Autowired
    CategoryDAO repoCategory;

    @Autowired
    ItemDAO repoItem;

    @RequestMapping("/testForm")
    public String testForm(Model model){
        return "TestForm";
    }

    @RequestMapping("/testCategoryAndItem")
    public String testCategoryAndItem(Model model){
        List<Category> categories = repoCategory.getAll();
        model.addAttribute("categories", categories);
        model.addAttribute("categoriesByName", repoCategory.getCategoryByName("pizza"));
        model.addAttribute("items", repoItem.getAll());

        model.addAttribute("category_pizza", repo.getItemsByCategory(repo.getCategoryById(1)));
        model.addAttribute("category_dessert", repo.getItemsByCategory(repo.getCategoryById(2)));
        return "TestCategoriesAndItems";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(ItemForm itemForm){
        logger.info(itemForm);
        repoItem.makePersistent(itemForm);
        logger.info("Item was saved");
        return "redirect:/test/testAddProduct";
    }

    @RequestMapping(value = "/testAddProduct", method = RequestMethod.GET)
    public String testAddProductGet(Model model){
        List<Category> categories = repoCategory.getAll();
        model.addAttribute("categories", categories);
        model.addAttribute("items", repoItem.getAll());
        model.addAttribute("categoryName", getCategoryName(categories));
        model.addAttribute("item", new ItemForm());
        return "Add_product";
    }

    public List<String> getCategoryName(List<Category> categories){
        List<String> categoryNames = new ArrayList<>();
        for (Category category : categories) {
            categoryNames.add(category.getName());
        }
        return categoryNames;
    }
}
