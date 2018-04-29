import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import pizzaShop.config.mvcConfig.RootConfig;
import pizzaShop.entity.*;
import pizzaShop.entity.embedded.Address;
import pizzaShop.entity.embedded.Contact;
import pizzaShop.entity.embedded.MonetaryAmount;
import pizzaShop.service.*;

import java.time.LocalDateTime;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@Transactional
@WebAppConfiguration
@ActiveProfiles("test")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class serviceItemTest {

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

    //_____ITEM________CRUD_test
    @Test
    public void testItemCreate() {
        CategorizedItem categorizedItem = new CategorizedItem();
        categorizedItem.setCategory(categoryService.findOne(1));
        Item item = new Item();
        item.setPrice(new MonetaryAmount(3d));
        item.setName("itemTest");
        item.getCategorizedItems().add(categorizedItem);
        itemService.save(item);
        Assert.assertEquals(4, itemService.findAll().size());
        Assert.assertEquals(item, itemService.findOne(4l));
        Assert.assertEquals(5, catItemService.findAll().size());
    }
    @Test
    public void testItemRead() {
        Item item = new Item();
        item.setPrice(new MonetaryAmount(455d));
        item.setName("pizza");
        item.setDescription("vkusno");
        Assert.assertEquals(3, itemService.findAll().size());
        Assert.assertEquals(item, itemService.findOne(1l));
    }
    @Test
    public void testItemUpdate() {
        Item item = new Item();
        item.setPrice(new MonetaryAmount(23d));
        item.setId(1l);
        item.setName("pizzaUpdated");
        item.setDescription("");
        item.getCategorizedItems().add(new CategorizedItem(categoryService.findOne(1), item));
        item.getCategorizedItems().add(new CategorizedItem(categoryService.findOne(2), item));
        item.getCategorizedItems().add(new CategorizedItem(categoryService.findOne(3), item));
        itemService.update(item);
        Assert.assertEquals(3, itemService.findAll().size());
        Assert.assertEquals(4, catItemService.findAll().size());
    }
    @Test
    public void testItemDelete() {
        itemService.delete(1l);
        Assert.assertEquals(2, itemService.findAll().size());
        Assert.assertEquals(1, catItemService.findAll().size());
        Assert.assertEquals(2, productService.findAll().size());

    }

    //_____ITEM________CRUD_test


    //_____Product________CRUD_test

    @Test
    public void testProductCreate() {
        Product product = new Product(itemService.findOne(3l));
        product.setCart(shoppingCartService.findOne(1l));
        productService.save(product);
        Assert.assertEquals(4, productService.findAll().size());
        Assert.assertEquals(product, productService.findOne(4l));
    }
    @Test
    public void testProductRead() {
        Product product = new Product(itemService.findOne(3l));
        product.setCart(shoppingCartService.findOne(1l));
        productService.save(product);
        Assert.assertEquals(4, productService.findAll().size());
        Assert.assertEquals(product, productService.findOne(4l));
    }
    @Test
    public void testProductUpdate() {
        Product product = new Product(itemService.findOne(3l));
        product.setCart(shoppingCartService.findOne(1l));
        productService.save(product);
        Assert.assertEquals(4, productService.findAll().size());
        Assert.assertEquals(product, productService.findOne(4l));
    }
    @Test
    public void testProductDelete() {
        productService.delete(2l);
        Assert.assertEquals(2, productService.findAll().size());
    }

    @Test
    public void testProductAddToShoppingCart() {
        Product product = new Product(itemService.findOne(1l));
        ShoppingCart cart = shoppingCartService.findOne(1l);
        cart.add(product);
        Assert.assertEquals(6,cart.getProductByItemId(1l).getQuantity());
    }

    //_____Product________CRUD_test


    //_____User________CRUD_test
    @Test
    public void testUserCreate() {
        User user = new User("test", "test", true, new Contact("2332", "email@gmail"), new Address("sfdf", 2,3,1));
        userService.save(user);
        Assert.assertEquals(3, userService.findAll().size());
        Assert.assertEquals(user, userService.findByUsername("test"));
    }
    @Test
    public void testUserRead() {
        User user = new User("test", "test", true, new Contact("2332", "email@gmail"), new Address("sfdf", 2,3,1));
        userService.save(user);
        Assert.assertEquals(3, userService.findAll().size());
        Assert.assertEquals(user, userService.findByUsername("test"));
    }
    @Test
    public void testUserUpdate() {
        User user = userService.findByUsername("arslan");
        user.setEnabled(false);
        userService.update(user);
        Assert.assertEquals(2, userService.findAll().size());
    }
    @Test
    public void testUserDelete(){
        userService.deleteByUsername("arslan");
        Assert.assertEquals(1, userService.findAll().size());
        Assert.assertEquals(1, shoppingCartService.findAll().size());
        Assert.assertEquals(2, productService.findAll().size());
    }

    //_____User________CRUD_test


    //_____ShoppingCart________CRUD_test

    @Test
    public void testShoppingCartCreate() {
        Product product = new Product(itemService.findOne(3l));
        ShoppingCart cart = new ShoppingCart();
        cart.add(product);
        cart.setUsername("testUSERNAME");
        cart.setDate(LocalDateTime.now());
        shoppingCartService.save(cart);
        Assert.assertEquals(3, shoppingCartService.findAll().size());
        Assert.assertEquals(4, productService.findAll().size());
    }
    @Test
    public void testShoppingCartRead() {
        Assert.assertEquals(2, shoppingCartService.findOne(1l).getCart().size());
    }
    @Test
    public void testShoppingCartDelete() {
        shoppingCartService.delete(1l);
        Assert.assertEquals(1, shoppingCartService.findAll().size());
        Assert.assertEquals(1, productService.findAll().size());
    }
}
