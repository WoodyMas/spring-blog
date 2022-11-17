package com.codeup.springblog.models;

public class Dice {
    private int chosenNum;
    private int randomNum;

    Dice(){}

    public static int rollDice() {
        return (int) (Math.random() * 8);
    }


}
