package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Coffee;
import com.codeup.springblog.models.Customer;
import com.codeup.springblog.models.Supplier;
import com.codeup.springblog.repositories.CoffeeRepository;
import com.codeup.springblog.repositories.CustomerRepository;
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
    private final CustomerRepository customerDao;

    public CoffeeController(CoffeeRepository coffeeDao, SupplierRepository suppliersDao, CustomerRepository customerDao) {
        this.coffeeDao = coffeeDao;
        this.suppliersDao = suppliersDao;
        this.customerDao = customerDao;
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


    @GetMapping("/coffee/suppliers")
    public String showSuppliersForm(Model model){
        List<Supplier> suppliers = suppliersDao.findAll();
        model.addAttribute("suppliers", suppliers);
//        model.addAttribute("supplier", supplier)
        return "/suppliers";
    }
    @PostMapping("/coffee/suppliers")
    public String insertSupplier(@ModelAttribute Supplier supplier){
//        Supplier supplier = new Supplier(name);

        suppliersDao.save(supplier);
        return "redirect:coffee/suppliers";

    }

    @GetMapping("/coffee/register")
    public String showRegistrationForm(Model model){
        model.addAttribute("customer", new Customer());
        return "/registration";
    }

//    @GetMapping("/coffee/all-coffees")
//    public String displayAllCoffees(@ModelAttribute )

    @PostMapping("coffee/customer/new")
    public String registerCustomer(@ModelAttribute Coffee coffee){
        coffeeDao.save(coffee);
        return "redirect:/coffee/all-coffees";
    }

    @PostMapping("/customer/{customerId}/favorite/{coffeeId}")
    public String favoriteCoffee(@PathVariable long customerId, @PathVariable long coffeeId){
        Customer customer = customerDao.findById(customerId);
        List<Coffee> favorites = customer.getCoffeeList();
        favorites.add(coffeeDao.findById(coffeeId));
        customer.setCoffeeList(favorites);
        customerDao.save(customer);
        return "redirect:/coffee";
    }
} // End CoffeeController Class
