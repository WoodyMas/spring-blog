package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Coffee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/coffee") // receive get request to /coffee
public class CoffeeController {
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
    @PostMapping
    public String signUp(@RequestParam(name = "email") String email, Model model){
        model.addAttribute("emailAttr", email);
        return "coffee";
    }

} // End CoffeeController Class