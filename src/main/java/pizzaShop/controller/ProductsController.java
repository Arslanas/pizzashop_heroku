package pizzaShop.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaShop.entity.*;
import pizzaShop.entity.embedded.Product;
import pizzaShop.repository.*;
import pizzaShop.service.*;
import pizzaShop.utilities.AppScopedData;
import pizzaShop.validator.CustomPropertyCategorizedItem;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
@SessionAttributes({"cart", "categories"})
public class ProductsController {

    private static Logger logger = Logger.getLogger(ProductsController.class);
    private final AppScopedData appScopedData;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final ItemService itemService;
    private final CategorizedItemService categorizedItemService;

    @Autowired
    public ProductsController(UserService userService, ShoppingCartService shoppingCartService, CategoryService categoryService, ItemService itemService, CategorizedItemService categorizedItemService, AppScopedData appScopedData) {
        this.categoryService = categoryService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.itemService = itemService;
        this.categorizedItemService = categorizedItemService;
        this.appScopedData = appScopedData;
    }

//////////////      REST
    @RequestMapping(value = "/rest/{item}")
    @ResponseBody
    public Item  getRest(@PathVariable Item item){
        return item;
    }
//////////////      REST

//////////////      PRODUCTS
    @RequestMapping
    public String products(Model model, HttpSession session, Pageable pageable, Sort sort) {
        if (session.getAttribute("categoryName") != null) session.removeAttribute("categoryName");
        model.addAttribute("sort", sort != null ? sort.iterator().next().getProperty() : "" );
        model.addAttribute("page", itemService.findAll(pageable));
        return "Products";
    }

    @RequestMapping("/{categoryName}")
    public String productsByCategory(@PathVariable("categoryName") String categoryName, Model model, HttpSession session) {
        model.addAttribute("items", itemService.getItemsByCategoryName(categoryName));
        return "Products";
    }

    // CategoryName not readable code
    @RequestMapping(value = "/add/{itemID}")
    @ResponseBody
    public ShoppingCart item(@PathVariable("itemID") Item item, @SessionAttribute("cart") ShoppingCart cart) {
        Product product = new Product(item);
        if (!cart.contains(product)) {
            cart.add(product);
        } else {
            cart.getProductByItemId(item.getId()).increaseQuantity();
        }
        return cart;
    }
//    @RequestMapping("/add/{itemID}")
//    public String item(@PathVariable("itemID") Long itemID, @SessionAttribute("cart") ShoppingCart cart, HttpSession session) {
//        Item item = itemDAO.findById(itemID);
//        Product product = new Product(item);
//        if (!cart.contains(product)) {
//            cart.add(product);
//        } else {
//            cart.getProductByItemId(itemID).increaseQuantity();
//        }
//        String category = (String) session.getAttribute("categoryName");
//        session.removeAttribute("categoryName");
//        if (category == null) return "redirect:/products/";
//        return "redirect:/products/" + category + "";
//    }

    @RequestMapping(value = "/search")
    public String productSearch(Model model, HttpSession session, @RequestParam("searchString") String searchString) {
        if (session.getAttribute("categoryName") != null) session.removeAttribute("categoryName");
        model.addAttribute("page", itemService.getItemsBySearchString(searchString));
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
    @RequestMapping("/shoppingCart/get")
    public String shoppingCartRemove() {
        logger.info(shoppingCartService.findOne(15l).getDate());
        return "redirect:/products";
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
        model.addAttribute("item", new Item());
        return "Add_product";
    }
    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public String addProductPost(Model model, @Valid @ModelAttribute("item") Item item, Errors errors, @SessionAttribute List<Category> categories){
        if(errors.hasErrors()){
            model.addAttribute("categoryName", getCategoryName(categories));
            model.addAttribute("item", item);
            return "Add_product";
        }
        Set<Category> categorySet = item.getSetOfCategorizedItems().stream().map(e->e.getCategory()).collect(Collectors.toSet());
        Item itemMerged = itemService.save(item);
        categorySet.stream().map(e->new CategorizedItem(e,itemMerged)).forEach(categorizedItemService::save);
        return "redirect:/products/addProduct";
    }

    @RequestMapping("/editProduct/{id}")
    public String editProduct(@PathVariable("id") Item item, Model model, @SessionAttribute List<Category> categories) {
        model.addAttribute("item", item);
        model.addAttribute("itemID", item.getId());
        model.addAttribute("categoryName", getCategoryName(categories));
        return "Edit_product";
    }

    @RequestMapping(value = "/editProduct", method = RequestMethod.POST)
    public String editProductPost(@ModelAttribute("itemEdit") Item item) {
        itemService.save(item);
        return "redirect:/products";
    }

    @RequestMapping("/remove/{id}")
    public String removeProduct(@PathVariable("id") Item item) {
        itemService.delete(item);
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
        shoppingCartService.save(cart);
        return "redirect:/products";
    }
//////////////          ORDER_CONFIRMATION


//////////////          VALIDATOR
    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(CategorizedItem.class, new CustomPropertyCategorizedItem(categoryService));
    }
//////////////          VALIDATOR


//////////////          Helpers
    //Change to CONVERTER
    private List<String> getCategoryName(List<Category> categories) {
        return categories.stream().map(e->e.getName()).collect(Collectors.toList());
    }

    @ModelAttribute("cart")
    public ShoppingCart shoppingCart() {
        return new ShoppingCart();
    }

    //Change due to unnecessary db hit
    @ModelAttribute("categories")
    public List<Category> categoriesAll() {
        return appScopedData.getAllCategories();
    }
    private User getCustomerUser(){
        return new User("","",false);
    }

    private boolean isUser(){
        boolean isAnonymous = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(e->e.getAuthority()).anyMatch(e-> e.equals("ROLE_ANONYMOUS"));
        return !isAnonymous;
    }
    private User getUserBySecurityUsername(){
        return userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

}
