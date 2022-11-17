package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Dice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dice")
public class DiceController {
    @GetMapping
    public String rollDice(){
        return "roll-dice";
    }
//    @GetMapping("/")
//    public String roll(@PathVariable int num1, Model model){
//        Dice diceRoll = new Dice()
//    }








    ////////////////////// MAIN TEST //////////////////////////////////
    public static void main(String[] args) {
        System.out.println(Dice.randomRollMethod());
    }
    ////////////////////// MAIN TEST //////////////////////////////////
}
