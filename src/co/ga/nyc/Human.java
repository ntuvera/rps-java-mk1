package co.ga.nyc;

import java.io.IOException;
import java.util.Scanner;

public class Human extends Player{

    // how to initialize player with their name
    public Human(String name) {
        super();
    }

    @Override
    public String toString(){
        return "Player name: " + this.getName() + " - " + this.getCurrentMove() + " " + this.getWins() + "/" + this.getLosses();
    }
    @Override
    public String chooseMove() throws IOException {
//        Scanner sc = scanner;
        Scanner sc = new Scanner(System.in);

        System.out.println("Please choose a move: \n");
        System.out.println("Type 'rock' to choose Rock");
        System.out.println("Type 'paper' to choose paper");
        System.out.println("Type 'scissors' to choose Scissors");
        System.out.println("Type 'quit' to go back to the main menu");

        String playerChoice;
//        if(sc.hasNextInt()){
//           playerChoice = sc.nextInt();
//        } else {
//            playerChoice = 0;
//        }
        playerChoice = sc.nextLine().toLowerCase();

        switch(playerChoice){
            case "rock":
                this.setCurrentMove("rock");
                return "rock";
            case "paper":
                this.setCurrentMove("paper");
                return "paper";
            case "scissors":
                this.setCurrentMove("scissors");
                return "scissors";
            case "quit":
                System.out.println("What's the rush?  Well thanks for playing anyway");
                Main.printMenu();
            default:
                System.out.println("No input recorded, please try again");
                chooseMove();
                return "null";
        }
    }
}
