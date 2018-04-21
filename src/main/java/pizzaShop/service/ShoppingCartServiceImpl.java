package pizzaShop.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pizzaShop.entity.ShoppingCart;
import pizzaShop.repository.ShoppingCartRepo;

import java.util.List;

@Service
public class ShoppingCartServiceImpl extends GenericServiceImpl<ShoppingCart, Long> implements ShoppingCartService {
    private static Logger logger = Logger.getLogger(ShoppingCartServiceImpl.class);
    ShoppingCartRepo repo;
    ProductService productService;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartRepo repo, ProductService productService) {
        super(ShoppingCart.class, repo);
        this.repo = (ShoppingCartRepo) repo;
        this.productService = productService;
    }

    @Override
    public List<ShoppingCart> findByUsername(String username) {

        return repo.findByUsernameOrderByDateDesc(username);
    }

    @Override
    public ShoppingCart save(ShoppingCart cart) {
        cart.getCart().stream().forEach(productService::save);
        return super.save(cart);
    }

    @Override
    public void delete(Long id) {
        findOne(id).getCart().stream().forEach(e->productService.delete(e.getId()));
        super.delete(id);
    }
}
