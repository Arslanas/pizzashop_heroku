package pizzaShop.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pizzaShop.entity.*;
import pizzaShop.entity.embedded.MonetaryAmount;
import pizzaShop.entity.embedded.Product;
import pizzaShop.service.*;
import pizzaShop.utilities.AppScopedData;
import pizzaShop.utilities.CustomPropertyMonetaryAmount;
import pizzaShop.validator.CustomPropertyCategorizedItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.*;
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
    public Item getRest(@PathVariable Item item) {
        return item;
    }

    @RequestMapping("/shoppingCart/increase/{item}")
    @ResponseBody
    public ShoppingCart shoppingCartIncrease(@PathVariable("item") long itemID, @SessionAttribute ShoppingCart cart) {
        cart.getProductByItemId(itemID).increaseQuantity();
        return cart;
    }

    @RequestMapping("/shoppingCart/decrease/{item}")
    @ResponseBody
    public ShoppingCart shoppingCartDecrease(@PathVariable("item") long itemID, @SessionAttribute ShoppingCart cart) {
        cart.getProductByItemId(itemID).decreaseQuantity();
        return cart;
    }

    @RequestMapping("/shoppingCart/remove/{item}")
    @ResponseBody
    public ShoppingCart shoppingCartRemove(@PathVariable("item") long itemID, @SessionAttribute ShoppingCart cart) {
        cart.removeFromCartByItemID(itemID);
        return cart;
    }

//////////////      REST

    //////////////      PRODUCTS

    @RequestMapping("/image/{id}")
    public void productsImage(@PathVariable("id") Item item, HttpServletResponse response) throws ServletException, IOException {
        int DEFAULT_BUFFER_SIZE = 10240;
        response.reset();
        response.setBufferSize(DEFAULT_BUFFER_SIZE);
        response.setContentType("image/JPG");

        try (InputStream inputStream = new ByteArrayInputStream(item.getImage().getPicture());
             BufferedInputStream input = new BufferedInputStream(inputStream, DEFAULT_BUFFER_SIZE);
             BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);
        ){
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            int length;
            while((length = input.read(buffer))> 0){
                output.write(buffer, 0, DEFAULT_BUFFER_SIZE);
            }
        }
    }

    @RequestMapping
    public String products(Model model, HttpServletRequest request, Pageable pageable) {
        model.addAttribute("page", itemService.findAll(pageable));
        model.addAttribute("requestType", request.getRequestURL().toString());
        return "Products";
    }

    @RequestMapping("/{category}")
    public String productsByCategory(@PathVariable("category") Category category, HttpServletRequest request, Model model, Pageable pageable, Sort sort) {
        logger.info(category);
        model.addAttribute("sort", sort != null ? sort.iterator().next().getProperty() : "");
        model.addAttribute("page", itemService.getItemsByCategory(category, pageable));
        model.addAttribute("requestType", request.getRequestURL().toString());
        return "Products";
    }



    @RequestMapping(value = "/search")
    public String productSearch(Model model, HttpServletRequest request, HttpSession session, @RequestParam("search") String search, Pageable pageable, Sort sort) {
        if (session.getAttribute("categoryName") != null) session.removeAttribute("categoryName");
        if (pageable.getPageSize() > 3) pageable = new PageRequest(pageable.getPageNumber(), 2, pageable.getSort());
        model.addAttribute("sort", sort != null ? sort.iterator().next().getProperty() : "");
        model.addAttribute("page", itemService.getItemsBySearchString(search, pageable));
        model.addAttribute("requestType", request.getRequestURL().toString());
        model.addAttribute("search", search);
        return "Products";
    }

    //////////////          SHOPPINGCART
    @RequestMapping(value = "/add/{itemID}")
    @ResponseBody
    public ShoppingCart item(@PathVariable("itemID") Item item, @SessionAttribute("cart") ShoppingCart cart) {
        return addProductToCart(new Product(item), cart);
    }
    @RequestMapping(value = "/addCart/{cartID}")
    @ResponseBody
    public ShoppingCart item(@PathVariable("cartID") ShoppingCart fromCart, @SessionAttribute("cart") ShoppingCart cart) {
        return addProductSetToCart(fromCart.getCart(), cart);
    }
    @RequestMapping("/shoppingCart")
    public String shoppingCartHandler(@SessionAttribute ShoppingCart cart, Model model, HttpSession session) {
        if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
            session.setAttribute("isRegistered", false);
        } else {
            session.setAttribute("isRegistered", true);
        }
        session.setAttribute("authorities", SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(e -> e.getAuthority()).collect(Collectors.toList()));

        logger.info(session.getAttribute("authorities"));
        model.addAttribute("cartSet", cart.getCart());
        return "ShoppingCart";
    }

    @RequestMapping("/shoppingCart/clear")
    public String shoppingCartClear(@SessionAttribute ShoppingCart cart) {
        cart.getCart().clear();
        return "redirect:/products";
    }

//////////////          SHOPPINGCART

    //////////////          ADMIN
    @RequestMapping("/addProduct")
    public String addProduct(Model model, @SessionAttribute List<Category> categories) {
        model.addAttribute("categoryName", getCategoryName(categories));
        model.addAttribute("item", new Item());
        return "Add_product";
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public String addProductPost(@RequestPart("picture") MultipartFile file, Model model, @Valid @ModelAttribute("item") Item item, Errors errors, @SessionAttribute List<Category> categories) {
        logger.info(item);
        if (errors.hasErrors()) {
            model.addAttribute("categoryName", getCategoryName(categories));
            model.addAttribute("item", item);
            return "Add_product";
        }
        logger.info(String.format("%s_%s_%s_%s", file.getName(), file.getContentType(), file.getOriginalFilename(), String.valueOf(file.getSize())));
        try{
             if(file.getBytes().length != 0) item.getImage().setPicture(file.getBytes());
        }catch (IOException e){
            e.printStackTrace();
        }
        Set<Category> categorySet = item.getSetOfCategorizedItems().stream().map(e -> e.getCategory()).collect(Collectors.toSet());
        Item itemMerged = itemService.save(item);
        categorySet.stream().map(e -> new CategorizedItem(e, itemMerged)).forEach(categorizedItemService::save);
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
    public String editProductPost(@RequestPart("picture") MultipartFile file, @ModelAttribute("itemEdit") Item item) {
        try{
            item.getImage().setPicture(file.getBytes());
        }catch (IOException e){
            e.printStackTrace();
        }
        itemService.save(item);
        return "redirect:/products";
    }

    @RequestMapping("/remove/{id}")
    public String removeProduct(@PathVariable("id") Item item) {
        itemService.delete(item);
        return "redirect:/products";
    }
//////////////          ADMIN

    //////////////          USER
    @RequestMapping("/user/details")
    public String userDetails(Model model) {
        model.addAttribute("user", userService.findByUsername(getUsername()));
        return "User_details";
    }
    @RequestMapping("/user/details/edit")
    public String userDetailsEdit(Model model) {
        model.addAttribute("user", userService.findByUsername(getUsername()));
        return "User_details_edit";
    }
    @RequestMapping("/user/orders")
    public String userOrders(Model model) {
        model.addAttribute("sCarts", shoppingCartService.findByUsername(getUsername()));
        return "User_orders";
    }
//////////////          USER

    //////////////          ORDER_CONFIRMATION
    @RequestMapping("/customerDetails")
    public String customerDetails(Model model) {
        model.addAttribute("customer", getCustomerUser());
        return "CustomerDetails";
    }

    @RequestMapping(value = "/customerDetails", method = RequestMethod.POST)
    public String customerDetailsPost(RedirectAttributes redirectAttributes, Model model,  @Valid @ModelAttribute("customer") User customer, Errors errors) {
        logger.info(errors);
        if(errors.hasErrors()){
            model.addAttribute("customer", customer);
            return "CustomerDetails";
        }
        redirectAttributes.addFlashAttribute("customer", customer);
        return "redirect:/products/orderConfirmation";
    }

    @RequestMapping("/orderConfirmation")
    public String confirmationOrder(@ModelAttribute("customer") User customer, @SessionAttribute ShoppingCart cart, Model model) {
        if (!isAnonymous()) {
            customer = userService.findByUsername(getUsername());
        }
        model.addAttribute("cartSet", cart.getCart());
        model.addAttribute("customer", customer);
        cart.setUsername(customer.getUsername());
        return "OrderConfirmation";
    }

    @RequestMapping(value = "/orderConfirmation", method = RequestMethod.POST)

    public String confirmationOrderPost(@SessionAttribute ShoppingCart cart, SessionStatus status) {
        status.setComplete();
        if (isAnonymous()) {
            return "redirect:/products";
        }
        shoppingCartService.save(cart);
        return "redirect:/products";
    }
//////////////          ORDER_CONFIRMATION


    //////////////          VALIDATOR
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(CategorizedItem.class, new CustomPropertyCategorizedItem(categoryService));
        binder.registerCustomEditor(MonetaryAmount.class, new CustomPropertyMonetaryAmount());
    }
//////////////          VALIDATOR


//////////////          Helpers

    private List<String> getCategoryName(List<Category> categories) {
        return categories.stream().map(e -> e.getName()).collect(Collectors.toList());
    }

    @ModelAttribute("cart")
    public ShoppingCart shoppingCart() {
        return new ShoppingCart();
    }

    @ModelAttribute("categories")
    public List<Category> categoriesAll() {
        return appScopedData.getAllCategories();
    }

    private User getCustomerUser() {
        return new User("", "", false);
    }

    private boolean isAnonymous() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(e -> e.getAuthority()).anyMatch(e -> e.equals("ROLE_ANONYMOUS"));

    }
    private String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private ShoppingCart addProductToCart(Product product, ShoppingCart cart){
        if (!cart.contains(product)) {
            logger.info(cart.getCart());
            cart.add(product);
        } else {
            cart.getProductByItemId(product.getItem().getId()).increaseQuantity();
        }
        return cart;
    }
    private ShoppingCart addProductSetToCart(Set<Product> products, ShoppingCart cart){
        products.stream().forEach(product -> {
            if (!cart.contains(product)) {
                cart.add(product);
            } else {
               Product productCart = cart.getProductByItemId(product.getItem().getId());
                productCart.setQuantity(productCart.getQuantity() + product.getQuantity());
            }
        });

        return cart;
    }

}
