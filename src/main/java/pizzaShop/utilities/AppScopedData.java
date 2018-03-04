package pizzaShop.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pizzaShop.entity.Category;
import pizzaShop.service.CategoryService;

import java.util.List;

@Component
public class AppScopedData {
    private List<Category> allCategories;

    @Autowired
    public AppScopedData(CategoryService categoryService) {
        try {
            allCategories = categoryService.findAll();
        } catch (Exception e){
            e.getMessage();
        }
    }

    public List<Category> getAllCategories() {
        return allCategories;
    }
}
