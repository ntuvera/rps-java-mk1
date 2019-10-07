package co.ga.nyc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static boolean quit = false;

    public static void main(String[] args) throws IOException {
        Game game = new Game();

        System.out.println("=========================================================");
        System.out.println("================== Rock Paper Scissors ==================");
        System.out.println("=========================================================");

           game.printMenu();
    }

}
