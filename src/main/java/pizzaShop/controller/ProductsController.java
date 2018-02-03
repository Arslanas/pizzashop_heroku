package pizzaShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import pizzaShop.repository.TempRepository;

@Controller
@RequestMapping("/products")
public class ProductsController {


    private final TempRepository repo;

    @Autowired
    public ProductsController(TempRepository repo){
        Assert.notNull(repo,"TempRepository is null!!!");
        this.repo = repo;
    }


    @RequestMapping
    public String products(Model model){
        model.addAttribute("temp",repo.getTemp());
        return "Products";
    }
}
