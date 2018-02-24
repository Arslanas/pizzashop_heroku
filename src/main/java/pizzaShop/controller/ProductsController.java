package pizzaShop.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaShop.entity.*;
import pizzaShop.pojo.*;
import pizzaShop.repository.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
@SessionAttributes({"cart", "categories"})
public class ProductsController {

    private static Logger logger = Logger.getLogger(ProductsController.class);
    private final TempRepository repo;
    private final CategoryDAO categoryDAO;
    private final ItemDAO itemDAO;
    private final ItemSpringDAO itemSpringDAO;
    private final UserDAO userDAO;
    private final ShoppingCartDAO shoppingCartDAO;

    @Autowired
    public ProductsController(TempRepository repo, CategoryDAO categoryDAO, ItemDAO itemDAO, ShoppingCartDAO shoppingCartDAO, UserDAO userDAO, ItemSpringDAO itemSpringDAO) {
        Assert.notNull(repo, "TempRepository is null");
        Assert.notNull(categoryDAO, "CategoryDAO is null");
        Assert.notNull(itemDAO, "ItemDAO is null");
        this.repo = repo;
        this.categoryDAO = categoryDAO;
        this.itemDAO = itemDAO;
        this.itemSpringDAO = itemSpringDAO;
        this.shoppingCartDAO = shoppingCartDAO;
        this.userDAO = userDAO;
    }

//////////////      REST
    @RequestMapping(value = "/rest")
    @ResponseBody
    public Item  getRest(){
        return itemDAO.findById(3l);
    }
//////////////      REST

//////////////      PRODUCTS
    @RequestMapping
    public String products(Model model, HttpSession session, Pageable pageable, Sort sort) {
        if (session.getAttribute("categoryName") != null) session.removeAttribute("categoryName");
        model.addAttribute("sort", sort != null ? sort.iterator().next().getProperty() : "" );
        model.addAttribute("page", itemSpringDAO.findAll(pageable));
        return "Products";
    }

    @RequestMapping("/{categoryName}")
    public String productsByCategory(@PathVariable("categoryName") String categoryName, Model model, HttpSession session) {
        model.addAttribute("items", itemDAO.getItemsByCategoryName(categoryName));
        session.setAttribute("categoryName", categoryName);
        return "Products";
    }

    // CategoryName not readable code
    @RequestMapping("/add/{itemID}")
    public String item(@PathVariable("itemID") Long itemID, @SessionAttribute("cart") ShoppingCart cart, HttpSession session) {
        Item item = itemDAO.findById(itemID);
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
    public String shoppingCartHandler(@SessionAttribute ShoppingCart cart, Model model, HttpSession session) {
        if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken ){
            session.setAttribute("isRegistered", false);
        }else {
            session.setAttribute("isRegistered", true);
        }
        session.setAttribute("authorities",SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(e->e.getAuthority()).collect(Collectors.toList()));

        logger.info(session.getAttribute("authorities"));
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
    public String addProduct(Model model, @SessionAttribute List<Category> categories) {
        model.addAttribute("categoryName", getCategoryName(categories));
        model.addAttribute("item", new ItemForm());
        return "Add_product";
    }
    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public String addProductPost(@ModelAttribute ItemForm itemForm){
        itemDAO.makePersistent(itemForm);
        return "redirect:/products/addProduct";
    }



    @RequestMapping("/editProduct/{id}")
    public String editProduct(@PathVariable("id") Item itemEdit, Model model, @SessionAttribute List<Category> categories) {
        model.addAttribute("itemEdit", itemEdit);
        model.addAttribute("itemID", itemEdit.getId());
        model.addAttribute("categoryName", getCategoryName(categories));
        model.addAttribute("item", new ItemForm());
        return "Edit_product";
    }

    @RequestMapping(value = "/editProduct", method = RequestMethod.POST)
    public String editProductPost(@ModelAttribute("itemEdit") Item item) {
        itemDAO.update(item);
        return "redirect:/products";
    }
//////////////          ADMIN

//////////////          ORDER_CONFIRMATION
    @RequestMapping("/customerDetails")
    public String customerDetails(Model model) {
        model.addAttribute("customer", getCustomerUser());
        return "CustomerDetails";
    }
    @RequestMapping(value = "/customerDetails", method = RequestMethod.POST)
    public String customerDetailsPost(@ModelAttribute User customer, RedirectAttributes redirectAttributes) {
        logger.info(customer);
        redirectAttributes.addFlashAttribute("customer", customer);
        return "redirect:/products/orderConfirmation";
    }

    @RequestMapping("/orderConfirmation")
    public String confirmationOrder(@ModelAttribute("customer") User customer, @SessionAttribute ShoppingCart cart, Model model) {
        logger.info(customer);
        if(isUser()){
            customer = getUserBySecurityUsername();
        }
        model.addAttribute("cartSet", cart.getCart());
        model.addAttribute("customer", customer);
        cart.setUsername(customer.getUsername());
        return "OrderConfirmation";
    }

    @RequestMapping(value = "/orderConfirmation", method = RequestMethod.POST)

    public String confirmationOrderPost(@SessionAttribute ShoppingCart cart, SessionStatus status) {
        status.setComplete();
        if (!isUser()){
            return "redirect:/products";
        }
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
    private User getCustomerUser(){
        return new User("","",false);
    }

    private boolean isUser(){
        boolean isAnonymous = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(e->e.getAuthority()).anyMatch(e-> e.equals("ROLE_ANONYMOUS"));
        return !isAnonymous;
    }
    private User getUserBySecurityUsername(){
        return userDAO.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

}
