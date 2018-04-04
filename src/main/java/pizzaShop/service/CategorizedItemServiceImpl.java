package pizzaShop.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pizzaShop.entity.CategorizedItem;
import pizzaShop.entity.Item;
import pizzaShop.repository.CategorizedItemRepo;

@Service
public class CategorizedItemServiceImpl extends GenericServiceImpl<CategorizedItem, CategorizedItem.CompositeID> implements CategorizedItemService {
    private static Logger logger = Logger.getLogger(ItemServiceImpl.class);

    @Autowired
    public CategorizedItemServiceImpl(CategorizedItemRepo categorizedItemRepo) {
        super(CategorizedItem.class, categorizedItemRepo);
    }

    @Override
    public void delete(Item item) {
        CategorizedItemRepo repo = (CategorizedItemRepo) genericRepo;
        repo.deleteByItem(item);
    }
}
