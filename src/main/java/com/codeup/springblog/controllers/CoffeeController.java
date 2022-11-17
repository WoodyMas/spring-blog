package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Coffee;
import com.codeup.springblog.repositories.CoffeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller

@RequestMapping("/coffee") // receive get request to /coffee
public class CoffeeController {
    private final CoffeeRepository coffeeDao;

    public CoffeeController(CoffeeRepository coffeeDao) {
        this.coffeeDao = coffeeDao;
    }

    @GetMapping // when the get request is received, it returns a "view" (coffee.html is the view being referenced)
    public String coffee(){
        // Not displaying coffee on page, we are referencing coffee.html (referencing file named "coffee")
        return "coffee";
    }

    @GetMapping("/new")
    public String addCoffeeForm(){
        return "create-coffee";
    }

    @GetMapping("/all-coffees")
    public String allCoffees(Model model){
        List<Coffee> coffees = coffeeDao.findAll();
        model.addAttribute("coffeesHTML", coffees);
        return "all-coffees";
    }

    @PostMapping("/new")
    public String addCoffee(@RequestParam(name = "roast") String roast, @RequestParam(name="origin") String origin, @RequestParam(name = "brand") String brand){
        Coffee coffee = new Coffee(roast, origin, brand);
        coffeeDao.save(coffee);
        return "redirect:/coffee/all-coffees";
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
    @PostMapping
    public String signUp(@RequestParam(name = "email") String email, Model model){
        model.addAttribute("emailAttr", email);


        return "coffee";
    }

} // End CoffeeController Class
