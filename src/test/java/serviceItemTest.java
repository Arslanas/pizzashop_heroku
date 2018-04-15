import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import pizzaShop.config.mvcConfig.RootConfig;
import pizzaShop.config.mvcConfig.ServletConfig;
import pizzaShop.entity.CategorizedItem;
import pizzaShop.entity.Category;
import pizzaShop.entity.Item;
import pizzaShop.entity.Temp;
import pizzaShop.repository.TempRepository;
import pizzaShop.service.CategorizedItemService;
import pizzaShop.service.CategoryService;
import pizzaShop.service.ItemService;
import pizzaShop.service.ShoppingCartService;

import java.util.HashSet;

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


    @Test
    public void testFindAll(){
        System.out.println(itemService);
        Assert.assertEquals(2,itemService.findAll().size());
        Assert.assertEquals(3,categoryService.findAll().size());
        Assert.assertEquals(4,catItemService.findAll().size());
        Assert.assertEquals(1, shoppingCartService.findAll().size());
    }
    @Test
    public void testAddOne(){
        tempRepository.save(new Temp("rrrr"));
        Assert.assertEquals(2,tempRepository.findAll().size());
        System.out.println(shoppingCartService.findOne(1l));
    }

}
