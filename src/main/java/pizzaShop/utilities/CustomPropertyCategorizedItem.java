package pizzaShop.utilities;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import pizzaShop.entity.CategorizedItem;
import pizzaShop.service.CategoryService;

import java.beans.PropertyEditorSupport;


public class CustomPropertyCategorizedItem extends PropertyEditorSupport {

    private static Logger logger = Logger.getLogger(CustomPropertyCategorizedItem.class);

    CategoryService categoryService;

    @Autowired
    public CustomPropertyCategorizedItem(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        CategorizedItem categorizedItem = new CategorizedItem();
        categorizedItem.setCategory(categoryService.findByName(text));
        setValue(categorizedItem);
    }
}
