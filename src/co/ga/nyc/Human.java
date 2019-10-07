package co.ga.nyc;

import java.io.Console;
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
        playerChoice = sc.nextLine().toLowerCase();

        switch (playerChoice) {
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

        // Hide Inputs using console, not available in IDE console
//        Console console = System.console();
//        if(console == null) {
//            System.out.println("Cannot write in this mode");
//            System.exit(0);
//        }
//        System.out.print("Please enter a move: (hidden input)");
//        char[] hiddenChoice = console.readPassword();
//
//        if (hiddenChoice.equals("rock")) {
//            this.setCurrentMove("rock");
//            return "rock";
//        } else if (hiddenChoice.equals("paper")) {
//            this.setCurrentMove("paper");
//            return "paper";
//        } else if (hiddenChoice.equals("scissors")) {
//            this.setCurrentMove("scissors");
//            return "scissors";
//        } else if (hiddenChoice.equals("quit")) {
//            System.out.println("What's the rush?  Well thanks for playing anyway");
//            Main.printMenu();
//            return "none";
//        } else {
//            System.out.println("No input recorded, please try again");
//            chooseMove();
//            return "null";
//        }
//    }
}
