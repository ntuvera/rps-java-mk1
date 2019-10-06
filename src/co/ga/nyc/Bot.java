package co.ga.nyc;

import java.util.Random;
import java.util.Scanner;

public class Bot extends Player{
    private String name = "Bot";

    public Bot() {
        super();
    }

    @Override
    public String toString(){
        return "RPS " + this.name +  " " + this.getCurrentMove() + " - " + this.getWins() + "/" + this.getLosses();
    }

    @Override
    public String chooseMove() {
        int randomNumberGenerator = (int) Math.floor(Math.random() * 3+1);
//        return Integer.parseInt(randomNumberGenerator);

        switch (randomNumberGenerator){
            case 1:
                this.setCurrentMove("rock");
                return "rock";
            case 2:
                this.setCurrentMove("paper");
                return "paper";
            case 3:
                this.setCurrentMove("scissors");
                return "scissors";
            default :
                this.setCurrentMove(null);
                System.out.println("Something went wrong - try again.  hint: press 1, 2, or 3");
                return "null";
        }
    }
}
