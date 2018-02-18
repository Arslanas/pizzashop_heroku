package pizzaShop.repository;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import pizzaShop.pojo.ShoppingCart;

@Repository
public class ShoppingCartDAOImpl extends GenericDAOImpl<ShoppingCart,Long> implements ShoppingCartDAO {


    private static Logger logger = Logger.getLogger(ShoppingCartDAOImpl.class);



    public ShoppingCartDAOImpl() {
        super(ShoppingCart.class);
    }
}
