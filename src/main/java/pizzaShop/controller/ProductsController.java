package pizzaShop.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import pizzaShop.entity.Category;
import pizzaShop.entity.Item;
import pizzaShop.entity.ItemForm;
import pizzaShop.pojo.Product;
import pizzaShop.pojo.ShoppingCart;
import pizzaShop.repository.CategoryDAO;
import pizzaShop.repository.ItemDAO;
import pizzaShop.repository.TempRepository;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
@SessionAttributes({"cart", "categories"})
public class ProductsController {

    private static Logger logger = Logger.getLogger(ProductsController.class);
    private final TempRepository repo;
    private final CategoryDAO categoryDAO;
    private final ItemDAO itemDAO;

    @Autowired
    public ProductsController(TempRepository repo, CategoryDAO categoryDAO, ItemDAO itemDAO){
        Assert.notNull(repo,"TempRepository is null");
        Assert.notNull(categoryDAO,"CategoryDAO is null");
        Assert.notNull(itemDAO,"ItemDAO is null");
        this.repo = repo;
        this.categoryDAO = categoryDAO;
        this.itemDAO = itemDAO;
    }

    @RequestMapping("/{categoryName}")
    public String productsByCategory(@PathVariable("categoryName") String categoryName, Model model){
        logger.info("productsByCategory() invoking itemDAO.getItemsByCategoryName("+categoryName+")");
        model.addAttribute("items", itemDAO.getItemsByCategoryName(categoryName));
        return "Products";
    }

    @RequestMapping
    public String products(Model model){
        model.addAttribute("items", itemDAO.getAll());
        return "Products";
    }

    @RequestMapping("/add/{itemID}")
    public String item(@PathVariable("itemID") Long itemID, @SessionAttribute("cart") ShoppingCart cart){
        Item item = itemDAO.getByID(itemID);
        Product product = new Product(item);
        if(!cart.contains(product)){
            cart.add(product);
        }else {
            cart.getProductByItemId(itemID).increaseQuantity();
        }
        return "redirect:/products";
    }
    @RequestMapping("/shoppingCart")
    public String shoppingCartHandler(@SessionAttribute ShoppingCart cart, Model model){
        model.addAttribute("cartSet",cart.getCart());
        return "ShoppingCart";
    }
    @RequestMapping("/test")
    public String test(@RequestParam("quantity") int quan, Model model){
        model.addAttribute("quantity", quan);
        return "ProductsTest";
    }

    @RequestMapping("/addProduct")
    public String addProduct(Model model, @SessionAttribute("categories") List<Category> categories){
        model.addAttribute("items", itemDAO.getAll());
        model.addAttribute("categoryName", getCategoryName(categories));
        model.addAttribute("item", new ItemForm());
        return "Add_product";
    }

    //Change to CONVERTER
    private List<String> getCategoryName(List<Category> categories){
        List<String> categoryNames = new ArrayList<>();
        for (Category category : categories) {
            categoryNames.add(category.getName());
        }
        return categoryNames;
    }

    @ModelAttribute("cart")
    public ShoppingCart shoppingCart(){
        return new ShoppingCart();
    }

    @ModelAttribute("categories")
    public List<Category> categoriesAll(){
        return categoryDAO.getAll();
    }
}
