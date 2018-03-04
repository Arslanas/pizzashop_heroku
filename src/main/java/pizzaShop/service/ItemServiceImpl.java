package pizzaShop.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pizzaShop.entity.Category;
import pizzaShop.entity.Item;
import pizzaShop.repository.ItemRepo;


@Service
public class ItemServiceImpl extends GenericServiceImpl<Item, Long> implements ItemService {

    private static Logger logger = Logger.getLogger(ItemServiceImpl.class);
    ItemRepo repo;
    @Autowired
    public ItemServiceImpl(ItemRepo itemRepo){
        super(Item.class, itemRepo);
        repo = itemRepo;
    }

    @Override
    public Page<Item> getItemsByCategory(Category category, Pageable pageable){
        return repo.findByCategory(category,pageable);
    }

    @Override
    public Page getItemsBySearchString(String search, Pageable pageable) {
        return repo.findByNameContainingIgnoreCase(search, pageable);
    }

}
