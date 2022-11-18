package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Dice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/roll-dice")
public class DiceController {
    @GetMapping
    public String rollDice(){
        return "roll-dice";
    }
    @GetMapping("/{num1}")
    public String roll(@PathVariable int num1, Model model) {
        Dice diceRoll = new Dice(/* randomRoll ,*/ /* Chosen number*/);
        model.addAttribute("randomNum", diceRoll.randomRollMethod());
        model.addAttribute("chosenNum", num1);
        return "roll-dice";
    }











    ////////////////////// MAIN TEST //////////////////////////////////
    public static void main(String[] args) {
        System.out.println(Dice.randomRollMethod());
    }
    ////////////////////// MAIN TEST //////////////////////////////////
}
