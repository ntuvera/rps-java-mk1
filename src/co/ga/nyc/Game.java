package co.ga.nyc;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private ArrayList<Player> players;
    private int idNum;
    private boolean quit;

    public boolean isQuit() {
        return quit;
    }

    public void setQuit(boolean quit) {
        this.quit = quit;
    }

    private ArrayList<Player> playerList;


    public static ArrayList<Player> choosePlayers() {

        ArrayList<Player> players = new ArrayList<Player>();
        Scanner scanner = new Scanner (System.in);

        System.out.println("Will you be playing against another person? (Y/n)");
        String playerResponse = scanner.nextLine();


        // method to start game

        if (playerResponse.contains("Y")) {
            System.out.println("human vs human");
            System.out.println("Who is player One?");
            String nameInput1 = scanner.nextLine();
            System.out.println("Who is player Two?");
            String nameInput2 = scanner.nextLine();
            Player player1 = new Human(nameInput1);
            Player player2 = new Human(nameInput2);

            players.add(player1);
            players.add(player2);
        } else if (playerResponse.contains("n")) {
            System.out.println("Who are you?");
            String nameInput = scanner.nextLine();
            Player player = new Human(nameInput);
            Player bot = new Bot();

            players.add(player);
            players.add(bot);
        }

        System.out.println("choosePlayers: " + players.toString());
        return players;

    }
    // Method to compare moves thrown
//    public static Player compareMoves(Player player1, Player player2){
    public static void compareMoves(Player player1, Player player2){
        String player1Move = player1.getCurrentMove();
        String player2Move = player2.getCurrentMove();
        System.out.println(player1Move);
        System.out.println(player2Move);

        if(player1Move.equals(player2Move)){
            System.out.println("It's a Draw");
        } else if(player1Move.equals("rock") && player2Move.equals("scissors")) {
            System.out.println("Rock Smashes Scissors");
            System.out.println(player1.getName() + " beats " + player2.getName());
        } else if(player1Move.equals("rock") && player2Move.equals("paper")) {
            System.out.println("Paper Wraps around Rock");
            System.out.println(player1.getName() + " beats " + player2.getName());
        } else if(player1Move.equals("scissors") && player2Move.equals("rock")) {
            System.out.println("Rock smashes Scissors");
            System.out.println(player2.getName() + " beats " + player1.getName());
        } else if(player1Move.equals("scissors") && player2Move.equals("paper")) {
            System.out.println("Scissors cuts Paper");
            System.out.println(player1.getName() + " beats " + player2.getName());
        } else {
            System.out.println("something went wrong here....");
        }
//        return player1;
    }

        // call bot to throw move

        // grab user input

        // compare

        // display results

    // OR

        // grab player 1 input

        // grab player 2 input

        // compare

    // method display results
    public static void displayResults(){
        System.out.println("Has won");
    }

    // method to read scores from record.txt

    public static void readFromFile(String filename) throws IOException {
        String file = filename;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        try{
            String currentLine = reader.readLine();

            while (currentLine != null) {
//                Superhero superhero = new Superhero();
//                String[] data = currentLine.split(",");
//                superhero.setSuperheroName(data[0]);
//                superhero.setSecretIdentity(data[1]);
//                superhero.setHome(data[2]);
//                superhero.setDomain(data[3]);
//                superheroes.add(superhero);
//                currentLine = reader.readLine();
            }
        }finally{
            reader.close();
        }
    }

    public static void writeToFile(String filename) throws IOException {
        Path path = Paths.get(filename);
//        byte[] strToBytes = (name + " " +score).getBytes();
//
//        Files.write(path, strToBytes);
//
//        String read = Files.readAllLines(path).get(0);
//        return read;

        BufferedWriter bufferedWriter = new BufferedWriter((new FileWriter(filename)));
        bufferedWriter.write("test");

        bufferedWriter.close();
        return;
    }


}
