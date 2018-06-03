package pizzaShop.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import pizzaShop.entity.*;
import pizzaShop.entity.embedded.Address;
import pizzaShop.entity.embedded.Contact;
import pizzaShop.entity.embedded.MonetaryAmount;
import pizzaShop.entity.Product;
import pizzaShop.service.*;
import pizzaShop.utilities.CustomPropertyMonetaryAmount;
import pizzaShop.utilities.CustomPropertyCategorizedItem;
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
@SessionAttributes({"cart", "categories"})
public class ProductsController {

    private static Logger logger = Logger.getLogger(ProductsController.class);
    private final CategoryService categoryService;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final ItemService itemService;

    @Autowired
    public ProductsController(UserService userService, ShoppingCartService shoppingCartService, CategoryService categoryService, ItemService itemService) {
        this.categoryService = categoryService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.itemService = itemService;
    }

//////////////      PRODUCTS

    @RequestMapping(value = {"/", "/products"})
    public String products(Model model, HttpServletRequest request, @PageableDefault(size = Integer.MAX_VALUE, sort = "price.amount", direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("page", itemService.findAll(pageable));
        model.addAttribute("requestType", request.getRequestURL().toString());
        return "Home";
    }

    @RequestMapping("/products/{category}")
    public String productsByCategory(@PathVariable("category") Category category, Model model, @PageableDefault(size = Integer.MAX_VALUE, sort = "name") Pageable pageable) {
        model.addAttribute("page", itemService.getItemsByCategory(category, pageable));
        return "Products";
    }

    @RequestMapping(value = "/products/search")
    public String productSearch(Model model, @RequestParam("search") String search, @PageableDefault(size = Integer.MAX_VALUE, sort = "name") Pageable pageable) {
        model.addAttribute("page", itemService.getItemsBySearchString(search, pageable));
        model.addAttribute("search", search);
        return "Products";
    }

    @RequestMapping("/products/image/{id}")
    public void productsImage(@PathVariable("id") Item item, HttpServletResponse response) throws ServletException, IOException {
        int DEFAULT_BUFFER_SIZE = 10240;
        response.reset();
        response.setBufferSize(DEFAULT_BUFFER_SIZE);
        response.setContentType("image/JPG");

        try (InputStream inputStream = new ByteArrayInputStream(item.getImage().getPicture());
             BufferedInputStream input = new BufferedInputStream(inputStream, DEFAULT_BUFFER_SIZE);
             BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);
        ) {
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, DEFAULT_BUFFER_SIZE);
            }
        }
    }

//////////////          SHOPPINGCART
    @RequestMapping(value = "/products/add/{itemID}")
    @ResponseBody
    public ShoppingCart addItemToCart(@PathVariable("itemID") Item item, @SessionAttribute("cart") ShoppingCart cart) {
        cart.add(new Product(item));
        return cart;
    }

    @RequestMapping(value = "/products/addCart/{cartID}")
    @ResponseBody
    public ShoppingCart addItemSetToCart(@PathVariable("cartID") ShoppingCart fromCart, @SessionAttribute("cart") ShoppingCart cart) {
        return addProductSetToCart(fromCart.getCart(), cart);
    }

    @RequestMapping("/products/shoppingCart")
    public String shoppingCartHandler(@SessionAttribute ShoppingCart cart, Model model, HttpSession session) {
        if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
            session.setAttribute("isRegistered", false);
        } else {
            session.setAttribute("isRegistered", true);
        }
        session.setAttribute("authorities", SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(e -> e.getAuthority()).collect(Collectors.toList()));
        model.addAttribute("cartSet", cart.getCart());
        return "ShoppingCart";
    }

    @RequestMapping("/products/shoppingCart/clear")
    public String shoppingCartClear(@SessionAttribute ShoppingCart cart) {
        cart.getCart().clear();
        return "redirect:/products";
    }

//////////////      REST

    @RequestMapping("/products/shoppingCart/increase/{item}")
    @ResponseBody
    public ShoppingCart shoppingCartIncrease(@PathVariable("item") long itemID, @SessionAttribute ShoppingCart cart) {
        cart.getProductByItemId(itemID).increaseQuantity();
        return cart;
    }

    @RequestMapping("/products/shoppingCart/decrease/{item}")
    @ResponseBody
    public ShoppingCart shoppingCartDecrease(@PathVariable("item") long itemID, @SessionAttribute ShoppingCart cart) {
        cart.getProductByItemId(itemID).decreaseQuantity();
        return cart;
    }

    @RequestMapping("/products/shoppingCart/remove/{item}")
    @ResponseBody
    public ShoppingCart shoppingCartRemove(@PathVariable("item") long itemID, @SessionAttribute ShoppingCart cart) {
        cart.removeFromCartByItemID(itemID);
        return cart;
    }

//////////////          ORDER_CONFIRMATION

    @RequestMapping("/products/customerDetails")
    public String customerDetails(Model model, HttpSession session) {
        if(!isAnonymous()){
            session.setAttribute("customer", userService.findByUsername(getAuthenticatedUsername()));
            return "redirect:/products/orderConfirmation";
        }
        if (session.getAttribute("customer") != null) {
            model.addAttribute("customer", session.getAttribute("customer"));
            return "CustomerDetails";
        }else {
            User newCustomer = getCustomerUser();
            model.addAttribute("customer", newCustomer);
            return "CustomerDetails";
        }
    }

    @RequestMapping(value = "/products/customerDetails", method = RequestMethod.POST)
    public String customerDetailsPost(HttpSession session, Model model, @Valid @ModelAttribute("customer") User customer, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("customer", customer);
            return "CustomerDetails";
        }
        session.setAttribute("customer", customer);
        return "redirect:/products/orderConfirmation";
    }

    @RequestMapping("/products/orderConfirmation")
    public String confirmationOrder(@SessionAttribute User customer, @SessionAttribute ShoppingCart cart, Model model) {
        model.addAttribute("cartSet", cart.getCart());
        model.addAttribute("customer", customer);
        cart.setUsername(customer.getUsername());
        return "OrderConfirmation";
    }

    @RequestMapping(value = "/products/orderConfirmation", method = RequestMethod.POST)
    public String confirmationOrderPost(@SessionAttribute ShoppingCart cart, SessionStatus status) {
        status.setComplete();
        if (isAnonymous()) {
            return "OrderSuccess";
        }
        shoppingCartService.save(cart);
        return "OrderSuccess";
    }

    @RequestMapping(value = "/about")
    public String about(){
        return "About";
    }
//////////////          VALIDATOR
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(CategorizedItem.class, new CustomPropertyCategorizedItem(categoryService));
        binder.registerCustomEditor(MonetaryAmount.class, new CustomPropertyMonetaryAmount());
    }


//////////////

    @ModelAttribute("cart")
    public ShoppingCart shoppingCart() {
        return new ShoppingCart();
    }

    @ModelAttribute("categories")
    public List<Category> categoriesAll() {
        return categoryService.findAll();
    }

    private User getCustomerUser() {
        return new User("", "customerPassword", false, new Contact("", "customer@email"), new Address());
    }

    private boolean isAnonymous() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(e -> e.getAuthority()).anyMatch(e -> e.equals("ROLE_ANONYMOUS"));

    }

    private String getAuthenticatedUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
    private ShoppingCart addProductSetToCart(Set<Product> products, ShoppingCart cart) {
        products.forEach(product -> {
            product.setId(null);
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