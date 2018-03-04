package pizzaShop.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pizzaShop.entity.Item;
import pizzaShop.repository.ItemRepo;

import java.util.Set;

@Service
public class ItemServiceImpl extends GenericServiceImpl<Item, Long> implements ItemService {

    private static Logger logger = Logger.getLogger(ItemServiceImpl.class);
    CategoryService categoryService;
    ItemRepo repo;
    @Autowired
    public ItemServiceImpl(ItemRepo itemRepo, CategoryService categoryService){
        super(Item.class, itemRepo);
        repo = itemRepo;
        this.categoryService = categoryService;
    }

    @Override
    public Set<Item> getItemsByCategoryName(String categoryName){
        return categoryService.getSetOfItems(categoryService.findByName(categoryName));
    };

    @Override
    public Set<Item> getItemsBySearchString(String search) {
        return repo.findByNameContainingIgnoreCase(search);
    }
}
