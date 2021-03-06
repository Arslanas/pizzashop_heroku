package pizzaShop.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pizzaShop.entity.CategorizedItem;
import pizzaShop.entity.Category;
import pizzaShop.entity.Item;
import pizzaShop.entity.User;
import pizzaShop.entity.embedded.MonetaryAmount;
import pizzaShop.service.*;
import pizzaShop.utilities.CustomPropertyMonetaryAmount;
import pizzaShop.utilities.CustomPropertyCategorizedItem;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {


    private static Logger logger = Logger.getLogger(AdminController.class);
    private final CategoryService categoryService;
    private final UserService userService;
    private final ItemService itemService;

    @Autowired
    public AdminController(UserService userService, CategoryService categoryService, ItemService itemService) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.itemService = itemService;
    }


    @RequestMapping(value = "/userManagementRest")
    @ResponseBody
    public Page<User> userManagementRest(Pageable pageable) {
        return userService.findAll(pageable);
    }

    @RequestMapping(value = "/userManagementRest/disable/{username}")
    @ResponseBody
    public User userDisable(@PathVariable("username") String username) {
        User user = userService.findByUsername(username);
        if (user.getEnabled() == true) {
            user.setEnabled(false);
        } else {
            user.setEnabled(true);
        }
        return userService.save(user);
    }

    @RequestMapping(value = "/userManagement")
    public String userManagement(Model model, Pageable pageable) {
        PageRequest pageRequest = new PageRequest(pageable.getPageNumber(), 5, pageable.getSort());
        model.addAttribute("page", userService.findAll(pageRequest));
        return "UserManagement";
    }

    @RequestMapping("/addProduct")
    public String addProduct(Model model, @SessionAttribute List<Category> categories) {
        model.addAttribute("categoryName", getCategoryName(categories));
        model.addAttribute("item", new Item());
        return "Add_product";
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public String addProductPost(@RequestPart("picture") MultipartFile file, Model model, @Valid @ModelAttribute("item") Item item, Errors errors, @SessionAttribute List<Category> categories) {
        if (errors.hasErrors()) {
            model.addAttribute("categoryName", getCategoryName(categories));
            model.addAttribute("item", item);
            return "Add_product";
        }
        if (file != null) itemService.setPicture(item, file);
        itemService.save(item);
        return "redirect:/products";
    }

    @RequestMapping("/editProduct/{id}")
    public String editProduct(@PathVariable("id") Item item, Model model, @SessionAttribute List<Category> categories) {
        model.addAttribute("categoryName", getCategoryName(categories));
        model.addAttribute("oldCategoryItems", item.getCategorizedItems().stream().map(CategorizedItem::getCategory).map(Category::getName).collect(Collectors.toList()));
        model.addAttribute("item", item);
        model.addAttribute("itemID", item.getId());
        return "Edit_product";
    }

    @RequestMapping(value = "/editProduct", method = RequestMethod.POST)
    public String editProductPost(@RequestPart("picture") MultipartFile file, @Valid @ModelAttribute("item") Item item, Errors errors, Model model, @SessionAttribute List<Category> categories) {
        if (errors.hasErrors()) {
            model.addAttribute("categoryName", getCategoryName(categories));
            model.addAttribute("item", item);
            return "Edit_product";
        }
        if (file != null) itemService.setPicture(item, file);
        itemService.update(item);
        return "redirect:/products";
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.POST)
    public String removeProduct(@PathVariable("id") Long id) {
        itemService.delete(id);
        return "redirect:/products";
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(CategorizedItem.class, new CustomPropertyCategorizedItem(categoryService));
        binder.registerCustomEditor(MonetaryAmount.class, new CustomPropertyMonetaryAmount());
    }

    private List<String> getCategoryName(List<Category> categories) {
        return categories.stream().map(Category::getName).collect(Collectors.toList());
    }
}
