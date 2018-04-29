package pizzaShop.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pizzaShop.entity.Product;
import pizzaShop.entity.ShoppingCart;
import pizzaShop.repository.ShoppingCartRepo;

import java.util.List;
import java.util.Set;

@Service
public class ShoppingCartServiceImpl extends GenericServiceImpl<ShoppingCart, Long> implements ShoppingCartService {
    private static Logger logger = Logger.getLogger(ShoppingCartServiceImpl.class);
    ShoppingCartRepo repo;
    ProductService productService;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartRepo repo, ProductService productService) {
        super(ShoppingCart.class, repo);
        this.repo = repo;
        this.productService = productService;
    }

    @Override
    public List<ShoppingCart> findByUsername(String username) {

        return repo.findByUsernameOrderByDateDesc(username);
    }

    @Override
    public ShoppingCart save(ShoppingCart cart) {
        Set<Product> products = cart.getCart();
        ShoppingCart savedCart = super.save(cart);
        products.forEach(e->{
            e.setCart(savedCart);
            productService.save(e);
        });
        return savedCart;
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
    }
}
