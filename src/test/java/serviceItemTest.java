import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import pizzaShop.config.mvcConfig.RootConfig;
import pizzaShop.entity.*;
import pizzaShop.entity.embedded.Address;
import pizzaShop.entity.embedded.Contact;
import pizzaShop.repository.ProductsRepo;
import pizzaShop.repository.TempRepository;
import pizzaShop.service.*;

import java.time.LocalDateTime;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@Transactional
@WebAppConfiguration
@ActiveProfiles("test")
public class serviceItemTest {

    @Autowired
    TempRepository tempRepository;
    @Autowired
    ItemService itemService;
    @Autowired
    CategorizedItemService catItemService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ShoppingCartService shoppingCartService;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    ProductsRepo productsRepo;

    @Test
    public void testFindAll() {
        System.out.println(itemService);
        Assert.assertEquals(3, itemService.findAll().size());
        Assert.assertEquals(3, categoryService.findAll().size());
        Assert.assertEquals(4, catItemService.findAll().size());
        Assert.assertEquals(3, productService.findAll().size());
        Assert.assertEquals(2, shoppingCartService.findAll().size());
    }
    @Test
    public void testItemDelete() {
        itemService.delete(2l);
        Assert.assertEquals(1, productService.findAll().size());
    }
    @Test
    public void testUserDelete(){
        userService.deleteByUsername("arslan");
        Assert.assertEquals(1, userService.findAll().size());
        Assert.assertEquals(1, shoppingCartService.findAll().size());
        Assert.assertEquals(2, productService.findAll().size());
    }

    @Test
    public void testAddOne() {
        tempRepository.save(new Temp("rrrr"));
        Assert.assertEquals(2, tempRepository.findAll().size());
        System.out.println(shoppingCartService.findOne(1l));
    }

    @Test
    public void testUserService() {
        Assert.assertEquals(2, userService.findAll().size());
        User newUser = new User("yelena", "ss", true, new Contact("2332", "ds@sdf"), new Address("sfdf", 2,3,1));
        userService.update(userService.findByUsername("arslan"));
        userService.update(newUser);
        Assert.assertEquals(3, userService.findAll().size());
    }

    @Test
    public void testProductsAdd() {
        Product product = new Product(itemService.findOne(1l));
        ShoppingCart cart = shoppingCartService.findOne(1l);
        cart.add(product);
        Assert.assertEquals(6,cart.getProductByItemId(1l).getQuantity());
    }

    @Test
    public void testProductsDelete() {
        productService.findAll().forEach(e->productsRepo.delete(e));
        Assert.assertEquals(0, productService.findAll().size());
    }
    @Test
    public void testShoppingCartDelete() {
        shoppingCartService.delete(1l);
        Assert.assertEquals(1, productService.findAll().size());
    }
    @Test
    public void testProductsSave() {
        Product product = new Product(itemService.findOne(3l));
        product.setCart(shoppingCartService.findOne(1l));
        productService.save(product);
        Assert.assertEquals(4, productService.findAll().size());
    }
    @Test
    public void testShoppingCartSave() {
        Product product = new Product(itemService.findOne(3l));
        ShoppingCart cart = new ShoppingCart();
        cart.add(product);
        cart.setUsername("testUSERNAME");
        cart.setDate(LocalDateTime.now());
        shoppingCartService.save(cart);
        Assert.assertEquals(3, shoppingCartService.findAll().size());
        Assert.assertEquals(4, productService.findAll().size());
    }
}
