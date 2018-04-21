package pizzaShop.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizzaShop.entity.Product;
import pizzaShop.repository.ProductsRepo;


@Transactional
@Service
public class ProductServiceImpl extends GenericServiceImpl<Product, Long> implements ProductService {

    private static Logger logger = Logger.getLogger(ProductServiceImpl.class);
    CategorizedItemService categorizedItemService;

    @Autowired
    public ProductServiceImpl(ProductsRepo repo) {
        super(Product.class, repo);
    }
}
