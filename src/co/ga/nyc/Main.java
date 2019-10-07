package co.ga.nyc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static boolean quit = false;

    public static void main(String[] args) throws IOException {
//        Game.writeToFile("records.txt");
        System.out.println("=========================================================");
        System.out.println("================== Rock Paper Scissors ==================");
        System.out.println("=========================================================");

//        do {
           printMenu();
//        } while (!game.isQuit());
    }

    public static void printMenu() throws IOException {
        Scanner scanner = new Scanner(System.in);

        // Welcome and Title
        System.out.println("Main Menu::\n");
        System.out.println("Choose an action:\n");
        System.out.println("1. Type 'play' to play Rock Paper Scissors.\n");
        System.out.println("2. Type 'history' to view your game history.\n");
        System.out.println("3. Type 'quit' to stop playing.\n");

        String menuInputInt = scanner.nextLine().toLowerCase();

        Game game = new Game();

//        while(!quit) {
            switch (menuInputInt) {
                case "play":

                    ArrayList<Player> players = game.choosePlayers();

                    players.forEach( (player) -> {
                        String pickedMove = null;
                        try {
                            pickedMove = player.chooseMove();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        player.setCurrentMove(pickedMove);
                    });

                    game.compareMoves(players.get(0), players.get(1));
                    break;
                case "history":
                    System.out.println("View Previous Records");
                    game.readFromFile("records.txt");
                    printMenu();
                    break;
                case "quit":
                    System.out.println("Thanks for playing");
                    quit = true;
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid output detected, please try again.");
                    printMenu();
                    break;
            }
//        }
        scanner.close();
    }
}
