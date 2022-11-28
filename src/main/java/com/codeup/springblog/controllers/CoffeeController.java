package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Coffee;
import com.codeup.springblog.models.Supplier;
import com.codeup.springblog.repositories.CoffeeRepository;
import com.codeup.springblog.repositories.SupplierRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
//@RequestMapping(value = "/coffees") // receive get request to /coffee
public class CoffeeController {
    private final CoffeeRepository coffeeDao;
    private final SupplierRepository suppliersDao;

    public CoffeeController(CoffeeRepository coffeeDao, SupplierRepository suppliersDao) {
        this.coffeeDao = coffeeDao;
        this.suppliersDao = suppliersDao;
    }

    @GetMapping // when the get request is received, it returns a "view" (coffee.html is the view being referenced)
    public String coffee(){
        // Not displaying coffee on page, we are referencing coffee.html (referencing file named "coffee")
        return "coffee";
    }

    @GetMapping("/{roast}")
    public String roast(@PathVariable String roast, Model model){
        Coffee selection = new Coffee(roast, "Cool beans");
        Coffee selection2 = new Coffee(roast, "Coffee Bros");


        selection.setOrigin("Ethiopia");
        selection2.setOrigin("Vietnam");

        List<Coffee> selections = new ArrayList<>(List.of(selection, selection2));

        model.addAttribute("selections", selections);


        return "coffee";
    }

    // To make the form work we need a @PostMapping
//    @PostMapping
//    public String signUp(@RequestParam(name = "email") String email, Model model){
//        model.addAttribute("emailAttr", email);
//        return "coffee";
//    }
    @GetMapping("/coffee/suppliers")
    public String showSuppliersForm(Model model){
        List<Supplier> suppliers = suppliersDao.findAll();
        model.addAttribute("suppliers", suppliers);
        return "/suppliers";
    }
    @PostMapping("/coffee/suppliers")
    public String insertSupplier(@RequestParam (name = "name") String name, Model model){
        Supplier supplier = new Supplier(name);
        suppliersDao.save(supplier);
        return "redirect:/suppliers";

    }

} // End CoffeeController Class
