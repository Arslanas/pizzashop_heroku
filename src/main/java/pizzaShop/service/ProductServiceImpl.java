package pizzaShop.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizzaShop.entity.Item;
import pizzaShop.entity.Product;
import pizzaShop.repository.ProductsRepo;

import java.util.List;


@Transactional
@Service
public class ProductServiceImpl extends GenericServiceImpl<Product, Long> implements ProductService {

    private static Logger logger = Logger.getLogger(ProductServiceImpl.class);
    ProductsRepo repo;
    @Autowired
    public ProductServiceImpl(ProductsRepo repo) {
        super(Product.class, repo);
        this.repo = repo;
    }

    @Override
    public List<Product> findByItem(Item item) {
        return repo.findByItem(item);
    }
}
