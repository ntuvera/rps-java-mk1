package co.ga.nyc;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private ArrayList<Player> players;
    private int idNum;
    private boolean quit;
    private String recordsFile = "records.txt";

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

        System.out.println("Will you be playing with 2 players or vs. the computer? ('2 players' or 'vs computer')");
        String playerResponse = scanner.nextLine();


        // method to start game

        if (playerResponse.contains("2")) {
            System.out.println("human vs human detected");
            System.out.println("What is Player 1's name");
            String nameInput1 = scanner.nextLine();
            System.out.println("What is Player 2's name?");
            String nameInput2 = scanner.nextLine();
            Player player1 = new Human(nameInput1);
            Player player2 = new Human(nameInput2);
            player1.setName(nameInput1);
            player2.setName(nameInput2);

            players.add(player1);
            players.add(player2);
        } else if (playerResponse.contains("computer")) {
            System.out.println("human vs bot detected");
            System.out.println("What's the player's name?");
            String nameInput = scanner.nextLine();
            Player player = new Human(nameInput);
            Player bot = new Bot();
            player.setName(nameInput);

            players.add(player);
            players.add(bot);
        } else {
            System.out.println("Sorry, I didn't quite catch that, try '2 players' or 'vs computer'.");
            // print player select again
        }

        System.out.println("choosePlayers: " + players.toString());
        return players;

    }
    // Method to compare moves thrown
    public void compareMoves(Player player1, Player player2) throws IOException {
        System.out.println("CompareMoves");
        System.out.println(player1);
        System.out.println(player2);
        System.out.println("CompareMoves");
        String player1Move = player1.getCurrentMove();
        String player2Move = player2.getCurrentMove();
        System.out.println(player1.getName() + ": " + player1Move);
        System.out.println(player2.getName() + ": " + player2Move);

        if(player1Move.equals(player2Move)){
            System.out.println("It's a Draw");
            writeToFile(recordsFile, player1, player2);
        } else if(player1Move.equals("rock") && player2Move.equals("scissors")) {
            System.out.println("Rock Smashes Scissors");
            System.out.println(player1.getName() + " beats " + player2.getName());
            player1.setWins(player1.getWins()+1);
            writeToFile(recordsFile, player1, player2);
        } else if(player1Move.equals("rock") && player2Move.equals("paper")) {
            System.out.println("Paper Wraps around Rock");
            System.out.println(player1.getName() + " beats " + player2.getName());
            player1.setLosses(player1.getLosses()+1);
            writeToFile(recordsFile, player1, player2);
        } else if(player1Move.equals("scissors") && player2Move.equals("rock")) {
            System.out.println("Rock smashes Scissors");
            System.out.println(player2.getName() + " beats " + player1.getName());
            player1.setLosses(player1.getLosses()+1);
            writeToFile(recordsFile, player1, player2);
        } else if(player1Move.equals("scissors") && player2Move.equals("paper")) {
            System.out.println("Scissors cuts Paper");
            System.out.println(player1.getName() + " beats " + player2.getName());
            player1.setWins(player1.getWins()+1);
            writeToFile(recordsFile, player1, player2);
        } else {
            System.out.println("something went wrong here....");
        }
    }


    public static void displayResults(){
        System.out.println("display some results");
    }

    // method to read scores from record.txt

    public static void readFromFile(String filename) throws IOException {
        ArrayList<String> recordList = new ArrayList<String>();

        String file = filename;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        try {
            String currentLine = reader.readLine();

            while (currentLine != null) {
                // record format: name,wins,loses
                String[] data = currentLine.split(",");
                String result = "";

                result += String.format("%s picked %s and %s picked %s. It was a %s", data[0], data[1], data[2], data[3], data[4]);

                recordList.add(result);
                currentLine = reader.readLine();
            }
        }catch(Exception e){
            System.out.println("Unable to read records, please contact dev");
        }finally{
            reader.close();
        }
        recordList.forEach( record -> System.out.println(record + "\n"));
    }

    public static void writeToFile(String filename, Player player1, Player player2) throws IOException {
        Path path = Paths.get(filename);

        BufferedWriter bufferedWriter = new BufferedWriter((new FileWriter(filename, true)));
        String record = player1.getName()+ "," + player1.getCurrentMove() + "," + player2.getName() + "," + player2.getCurrentMove();
        bufferedWriter.newLine();
        bufferedWriter.write(record);

        bufferedWriter.close();
    }



}
