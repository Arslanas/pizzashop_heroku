package pizzaShop.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
import pizzaShop.entity.embedded.MonetaryAmount;
import pizzaShop.entity.embedded.Product;
import pizzaShop.service.*;
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
    private final CategoryService categoryService;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final ItemService itemService;
    private final CategorizedItemService categorizedItemService;

    @Autowired
    public ProductsController(UserService userService, ShoppingCartService shoppingCartService, CategoryService categoryService, ItemService itemService, CategorizedItemService categorizedItemService) {
        this.categoryService = categoryService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.itemService = itemService;
        this.categorizedItemService = categorizedItemService;
    }


    //////////////      PRODUCTS


    @RequestMapping
    public String products(Model model, HttpServletRequest request, @PageableDefault(size = Integer.MAX_VALUE, sort = "name") Pageable pageable) {
        model.addAttribute("page", itemService.findAll(pageable));
        model.addAttribute("requestType", request.getRequestURL().toString());
        return "Products";
    }

    @RequestMapping("/{category}")
    public String productsByCategory(@PathVariable("category") Category category, Model model, @PageableDefault(size = Integer.MAX_VALUE, sort = "name") Pageable pageable) {
        model.addAttribute("page", itemService.getItemsByCategory(category, pageable));
        return "Products";
    }

    @RequestMapping(value = "/search")
    public String productSearch(Model model, @RequestParam("search") String search, @PageableDefault(size = Integer.MAX_VALUE, sort = "name") Pageable pageable) {
        model.addAttribute("page", itemService.getItemsBySearchString(search, pageable));
        model.addAttribute("search", search);
        return "Products";
    }

    @RequestMapping("/image/{id}")
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
    @RequestMapping(value = "/add/{itemID}")
    @ResponseBody
    public ShoppingCart addItemToCart(@PathVariable("itemID") Item item, @SessionAttribute("cart") ShoppingCart cart) {
        return addProductToCart(new Product(item), cart);
    }

    @RequestMapping(value = "/addCart/{cartID}")
    @ResponseBody
    public ShoppingCart addItemSetToCart(@PathVariable("cartID") ShoppingCart fromCart, @SessionAttribute("cart") ShoppingCart cart) {
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

    //////////////      REST

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
//////////////          SHOPPINGCART

    //////////////          ORDER_CONFIRMATION

    @RequestMapping("/customerDetails")
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

    @RequestMapping(value = "/customerDetails", method = RequestMethod.POST)
    public String customerDetailsPost(HttpSession session, Model model, @Valid @ModelAttribute("customer") User customer, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("customer", customer);
            return "CustomerDetails";
        }
        session.setAttribute("customer", customer);
        return "redirect:/products/orderConfirmation";
    }

    @RequestMapping("/orderConfirmation")
    public String confirmationOrder(@SessionAttribute User customer, @SessionAttribute ShoppingCart cart, Model model) {
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


    @ModelAttribute("cart")
    public ShoppingCart shoppingCart() {
        return new ShoppingCart();
    }

    @ModelAttribute("categories")
    public List<Category> categoriesAll() {
        return categoryService.findAll();
    }

    private User getCustomerUser() {
        return new User("", "", false);
    }

    private boolean isAnonymous() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(e -> e.getAuthority()).anyMatch(e -> e.equals("ROLE_ANONYMOUS"));

    }

    private String getAuthenticatedUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private ShoppingCart addProductToCart(Product product, ShoppingCart cart) {
        if (!cart.contains(product)) {
            logger.info(cart.getCart());
            cart.add(product);
        } else {
            cart.getProductByItemId(product.getItem().getId()).increaseQuantity();
        }
        return cart;
    }

    private ShoppingCart addProductSetToCart(Set<Product> products, ShoppingCart cart) {
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
