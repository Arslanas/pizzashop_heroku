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

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartRepo repo) {
        super(ShoppingCart.class, repo);
        this.repo = (ShoppingCartRepo) repo;
    }

    @Override
    public List<ShoppingCart> findByUsername(String username) {

        return repo.findByUsernameOrderByDateDesc(username);
    }
}
