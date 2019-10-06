package co.ga.nyc;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // conditional while loop to keep running game until gameOver flag thrown
    boolean quit = false;

    public static void main(String[] args) {

        System.out.println("=========================================================");
        System.out.println("================== Rock Paper Scissors ==================");
        System.out.println("=========================================================");

//        do {
           printMenu();
//        } while (!game.isQuit());
    }

    public static void printMenu() {
        Scanner scanner = new Scanner(System.in);

        // Welcome and Title
        System.out.println("Choose an action:\n");
        System.out.println("1. Play Rock Paper Scissors\n");
        System.out.println("2. View Previous Records\n");
        System.out.println("3. DO something here\n");
        System.out.println("4. Quit\n");

        int menuInputInt = scanner.nextInt();

        Game game = new Game();

//        while(!game.isQuit()) {
            switch (menuInputInt) {
                case 1:

                    ArrayList<Player> players = game.choosePlayers();
                    players.forEach( (player) -> {
                        String pickedMove = player.getCurrentMove();
                        player.setCurrentMove(pickedMove);
                    });
                    break;
                case 2:
                    System.out.println("View Previous Records");
                    break;
                case 3:
                    System.out.println("Do something here");
                    break;
                case 4:
//                game.setQuit(true);
                    System.exit(0);
                default:
                    System.out.println("Invalid output detected, please try again.");
                    printMenu();
            }
//        }
        scanner.close();
    }
}
