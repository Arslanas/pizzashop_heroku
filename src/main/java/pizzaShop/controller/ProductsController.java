package pizzaShop.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaShop.entity.Category;
import pizzaShop.entity.Item;
import pizzaShop.entity.ItemForm;
import pizzaShop.pojo.*;
import pizzaShop.repository.CategoryDAO;
import pizzaShop.repository.ItemDAO;
import pizzaShop.repository.ShoppingCartDAO;
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
    private final ShoppingCartDAO shoppingCartDAO;

    @Autowired
    public ProductsController(TempRepository repo, CategoryDAO categoryDAO, ItemDAO itemDAO, ShoppingCartDAO shoppingCartDAO) {
        Assert.notNull(repo, "TempRepository is null");
        Assert.notNull(categoryDAO, "CategoryDAO is null");
        Assert.notNull(itemDAO, "ItemDAO is null");
        this.repo = repo;
        this.categoryDAO = categoryDAO;
        this.itemDAO = itemDAO;
        this.shoppingCartDAO = shoppingCartDAO;
    }


    @RequestMapping
    public String products(Model model, HttpSession session) {
        if (session.getAttribute("categoryName") != null) session.removeAttribute("categoryName");
        model.addAttribute("items", itemDAO.getAll());
        return "Products";
    }

    @RequestMapping("/{categoryName}")
    public String productsByCategory(@PathVariable("categoryName") String categoryName, Model model, HttpSession session) {
        logger.info("productsByCategory() invoking itemDAO.getItemsByCategoryName(" + categoryName + ")");
        model.addAttribute("items", itemDAO.getItemsByCategoryName(categoryName));
        session.setAttribute("categoryName", categoryName);
        return "Products";
    }

    // CategoryName not readable code
    @RequestMapping("/add/{itemID}")
    public String item(@PathVariable("itemID") Long itemID, @SessionAttribute("cart") ShoppingCart cart, HttpSession session) {
        Item item = itemDAO.getByID(itemID);
        Product product = new Product(item);
        if (!cart.contains(product)) {
            cart.add(product);
        } else {
            cart.getProductByItemId(itemID).increaseQuantity();
        }
        String category = (String) session.getAttribute("categoryName");
        session.removeAttribute("categoryName");
        if (category == null) return "redirect:/products/";
        return "redirect:/products/" + category + "";
    }

    @RequestMapping(value = "/search")
    public String productSearch(Model model, HttpSession session, @RequestParam("searchString") String searchString) {
        if (session.getAttribute("categoryName") != null) session.removeAttribute("categoryName");
        model.addAttribute("items", itemDAO.getItemsBySearchString(searchString));
        return "Products";
    }

    //////////////          SHOPPINGCART
    @RequestMapping("/shoppingCart")
    public String shoppingCartHandler(@SessionAttribute ShoppingCart cart, Model model) {
        model.addAttribute("cartSet", cart.getCart());
        return "ShoppingCart";
    }

    @RequestMapping("/shoppingCart/clear")
    public String shoppingCartClear(@SessionAttribute ShoppingCart cart) {
        cart.getCart().clear();
        return "redirect:/products";
    }

    @RequestMapping("/shoppingCart/increase/{item}")
    public String shoppingCartIncrease(@PathVariable("item") long itemID, @SessionAttribute ShoppingCart cart) {
        cart.getProductByItemId(itemID).increaseQuantity();
        return "redirect:/products/shoppingCart";
    }

    @RequestMapping("/shoppingCart/decrease/{item}")
    public String shoppingCartDecrease(@PathVariable("item") long itemID, @SessionAttribute ShoppingCart cart) {
        cart.getProductByItemId(itemID).decreaseQuantity();
        return "redirect:/products/shoppingCart";
    }

    @RequestMapping("/shoppingCart/remove/{item}")
    public String shoppingCartRemove(@PathVariable("item") long itemID, @SessionAttribute ShoppingCart cart) {
        cart.removeFromCartByItemID(itemID);
        return "redirect:/products/shoppingCart";
    }
//////////////          SHOPPINGCART

    //////////////          TEMP
    @RequestMapping("/test")
    public String test(@RequestParam("quantity") int quan, Model model) {
        model.addAttribute("quantity", quan);
        return "ProductsTest";
    }
//////////////          TEMP

    //////////////          ADMIN
    @RequestMapping("/addProduct")
    public String addProduct(Model model, @SessionAttribute("categories") List<Category> categories) {
        model.addAttribute("items", itemDAO.getAll());
        model.addAttribute("categoryName", getCategoryName(categories));
        model.addAttribute("item", new ItemForm());
        return "Add_product";
    }
//////////////          ADMIN

//////////////          ORDER_CONFIRMATION
    @RequestMapping("/customerDetails")
    public String customerDetails(Model model) {
        model.addAttribute("customer", Customer.getCustomer());
        return "CustomerDetails";
    }
    @RequestMapping(value = "/customerDetails", method = RequestMethod.POST)
    public String customerDetailsPost(@ModelAttribute Customer customer, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("customer", customer);
        return "redirect:/products//orderConfirmation";
    }

    @RequestMapping("/orderConfirmation")
    public String confirmationOrder(@ModelAttribute Customer customer, @SessionAttribute ShoppingCart cart, Model model) {
        model.addAttribute("cartSet", cart.getCart());
        cart.setUsername(customer.getName());
        return "OrderConfirmation";
    }

    @RequestMapping(value = "/orderConfirmation", method = RequestMethod.POST)
    public String confirmationOrderPost(@SessionAttribute ShoppingCart cart) {
        if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) return "redirect:/products";
        shoppingCartDAO.makePersistent(cart);
        return "redirect:/products";
    }
//////////////          ORDER_CONFIRMATION


    //Change to CONVERTER
    private List<String> getCategoryName(List<Category> categories) {
        List<String> categoryNames = new ArrayList<>();
        for (Category category : categories) {
            categoryNames.add(category.getName());
        }
        return categoryNames;
    }

    @ModelAttribute("cart")
    public ShoppingCart shoppingCart() {
        return new ShoppingCart();
    }

    //Change due to unnecessary db hit
    @ModelAttribute("categories")
    public List<Category> categoriesAll() {
        return categoryDAO.getAll();
    }

}
