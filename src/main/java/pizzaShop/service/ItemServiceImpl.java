package pizzaShop.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pizzaShop.entity.Item;
import pizzaShop.repository.ItemSpringDAO;

import java.util.List;

@Service
public class ItemServiceImpl extends GenericServiceImpl<Item, Long> implements ItemService {

    private static Logger logger = Logger.getLogger(ItemServiceImpl.class);

    @Autowired
    public ItemServiceImpl(ItemSpringDAO itemDAO){
        super(Item.class, itemDAO);
    }

}
