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
import pizzaShop.repository.TempRepository;
import pizzaShop.service.*;

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

    @Test
    public void testFindAll() {
        System.out.println(itemService);
        Assert.assertEquals(2, itemService.findAll().size());
        Assert.assertEquals(3, categoryService.findAll().size());
        Assert.assertEquals(4, catItemService.findAll().size());
        Assert.assertEquals(1, shoppingCartService.findAll().size());
    }

    @Test
    public void testAddOne() {
        tempRepository.save(new Temp("rrrr"));
        Assert.assertEquals(2, tempRepository.findAll().size());
        System.out.println(shoppingCartService.findOne(1l));
    }

    @Test
    public void testUserService() {
        Assert.assertEquals(1, userService.findAll().size());
        User newUser = new User("nazar", "ss", true, new Contact("2332", "ds@sdf"), new Address("sfdf", 2,3,1));
        userService.update(userService.findByUsername("arslan"));
        userService.update(newUser);
        Assert.assertEquals(2, userService.findAll().size());
    }

}
