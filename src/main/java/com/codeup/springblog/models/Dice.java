package com.codeup.springblog.models;

public class Dice {
/*####################################### Instance Variables/ Properties #############################################*/
    public int randomRollNumber;
    public int chosenNumber;
    final static int sides = 6;




/*################################################## Constructors ####################################################*/
    public Dice () {}

    public Dice(int randomRollNumber){
        this.randomRollNumber = randomRollNumber;
    }
    public Dice(int randomRollNumber, int chosenNumber) {
        this.randomRollNumber = randomRollNumber;
        this.chosenNumber = chosenNumber;
    }


/*##################################################### Methods ######################################################*/

    public int getRandomRollNumber() {
        return randomRollNumber;
    }

    public void setRandomRollNumber(int randomRollNumber) {
        this.randomRollNumber = randomRollNumber;
    }

    public int getChosenNumber() {
        return chosenNumber;
    }

    public void setChosenNumber(int chosenNumber) {
        this.chosenNumber = chosenNumber;
    }

    public static int randomRollMethod() {
        return (int) Math.floor((Math.random() * sides) + 1);
    }
}
