package co.ga.nyc;

import sun.lwawt.macosx.CSystemTray;

import java.util.Random;
import java.util.Scanner;

public class Human extends Player{
    private String name;

    // how to initialize player with their name
    public Human(String name) {
        super();
        this.name = name;
    }

    @Override
    public String toString(){
        return "Player name: " + this.name + " - " + this.getCurrentMove() + " " + this.getWins() + "/" + this.getLosses();
    }
    @Override
    public String chooseMove() {
//        Scanner sc = scanner;
        Scanner sc = new Scanner(System.in);

        System.out.println("Please choose a move: \n");
        System.out.println("Press 1. to choose \"Rock\" \n");
        System.out.println("Press 2. to choose \"Paper\" \n");
        System.out.println("Press 3. to choose \"Scissors\" \n");
        System.out.println("Press 4. to choose get a random Choice\n");

        int playerChoice;
        if(sc.hasNextInt()){
           playerChoice = sc.nextInt();
        } else {
            playerChoice = 0;
        }

        switch(playerChoice){
            case 1:
                this.setCurrentMove("rock");
                return "rock";
            case 2:
                this.setCurrentMove("paper");
                return "paper";
            case 3:
                this.setCurrentMove("scissors");
                return "scissors";
            default:
                System.out.println("No input recorded, please try again");
//                chooseMove();
                return "null";
        }
    }
}
