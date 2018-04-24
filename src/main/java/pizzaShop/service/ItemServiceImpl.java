package pizzaShop.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pizzaShop.entity.CategorizedItem;
import pizzaShop.entity.Category;
import pizzaShop.entity.Item;
import pizzaShop.entity.embedded.Image;
import pizzaShop.repository.ItemRepo;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;


@Transactional
@Service
public class ItemServiceImpl extends GenericServiceImpl<Item, Long> implements ItemService {

    private static Logger logger = Logger.getLogger(ItemServiceImpl.class);
    ItemRepo repo;
    CategorizedItemService categorizedItemService;
    ProductService productService;


    @Autowired
    public ItemServiceImpl(ItemRepo itemRepo, CategorizedItemService categorizedItemService, ProductService productService) {
        super(Item.class, itemRepo);
        repo = itemRepo;
        this.categorizedItemService = categorizedItemService;
        this.productService = productService;
    }

    @Override
    public Page<Item> getItemsByCategory(Category category, Pageable pageable) {
        return repo.findByCategory(category, pageable);
    }

    @Override
    public Page getItemsBySearchString(String search, Pageable pageable) {
        return repo.findByNameContainingIgnoreCase(search, pageable);
    }

    @Override
    public Item save(Item item) {
        Set<Category> categorySet = item.getCategorizedItems().stream().map(e -> e.getCategory()).collect(Collectors.toSet());
        Item itemMerged = super.save(item);
        categorySet.stream().map(e -> new CategorizedItem(e, itemMerged)).forEach(categorizedItemService::save);
        return itemMerged;
    }

    @Override
    public Item update(Item item) {
        Set<Category> categorySet = item.getCategorizedItems().stream().map(e -> e.getCategory()).collect(Collectors.toSet());
        item.getCategorizedItems().clear();
        categorizedItemService.delete(item);
        categorySet.stream().map(e -> new CategorizedItem(e, item)).forEach(categorizedItemService::save);
        super.save(item);
        return item;
    }

    @Override
    public void delete(Long id) {
        productService.findByItem(findOne(id)).forEach(e->productService.delete(e.getId()));
        super.delete(id);
    }

    @Override
    public Item setPicture(Item item, MultipartFile picture) {
        if (item.getId() == null) {
            try {
                if (picture.getBytes().length != 0) item.getImage().setPicture(picture.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return item;
        }
        if (picture.getSize() > 0) {
            try {
                item.getImage().setPicture(picture.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Image image = findOne(item.getId()).getImage();
            item.setImage(image);
        }
        return item;
    }
}
