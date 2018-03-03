package pizzaShop.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pizzaShop.entity.Item;
import pizzaShop.entity.ItemForm;
import pizzaShop.repository.ItemCustomRepository;
import pizzaShop.repository.ItemSpringDAO;

import java.util.List;
import java.util.Set;

@Service
public class ItemServiceImpl extends GenericServiceImpl<Item, Long> implements ItemService {

    private static Logger logger = Logger.getLogger(ItemServiceImpl.class);
    ItemCustomRepository itemCustomDAO;

    @Autowired
    public ItemServiceImpl(ItemSpringDAO itemDAO, ItemCustomRepository itemCustomDAO){
        super(Item.class, itemDAO);
        this.itemCustomDAO = itemCustomDAO;
    }

    @Override
    public Item makePersistent(ItemForm itemForm) {
        return itemCustomDAO.makePersistent(itemForm);
    }

    @Override
    public Set<Item> getItemsByCategoryName(String category) {
        return itemCustomDAO.getItemsByCategoryName(category);
    }

    @Override
    public Set<Item> getItemsBySearchString(String search) {
        return itemCustomDAO.getItemsBySearchString(search);
    }
}
