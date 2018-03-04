package pizzaShop.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pizzaShop.entity.Category;
import pizzaShop.entity.Item;
import pizzaShop.repository.CategoryRepo;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl extends GenericServiceImpl<Category, Integer> implements CategoryService {

    private static Logger logger = Logger.getLogger(CategoryServiceImpl.class);
    CategoryRepo repo;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo){
        super(Category.class, categoryRepo);
        repo = categoryRepo;
    }

    @Override
    public Category findByName(String name) {
        return repo.findByName(name);
    }
}
