package co.ga.nyc;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private static ArrayList<Player> players;
    private String recordsFile = "records.txt";

    public Game() { }

    public static void printMenu() throws IOException {
        Scanner scanner = new Scanner(System.in);

        // Welcome and Title
        System.out.println("\n");
        System.out.println("====================  Main Menu  =========================\n");
        System.out.println("Choose an action:\n");
        System.out.println("1. Type 'play' to play 'Rock, Paper, Scissors.'");
        System.out.println("2. Type 'history' to view your game history.");
        System.out.println("3. Type 'quit' to stop playing.");

        String menuInputInt = scanner.nextLine().toLowerCase();

        Game game = new Game();

        switch (menuInputInt) {
            case "play":
                players = game.choosePlayers();
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
                System.out.println("=====================");
                game.readFromFile("records.txt");
                printMenu();
                break;
            case "quit":
                System.out.println("Thanks for playing");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid output detected, please try again.");
                printMenu();
                break;
        }
        scanner.close();
    }


    public ArrayList<Player> choosePlayers() {
        players = new ArrayList<>();
        Scanner scanner = new Scanner (System.in);

        System.out.println("Will you be playing with 2 players or vs. the computer? ('2 players' or 'vs computer')");
        String playerResponse = scanner.nextLine().toLowerCase();
// TODO: Validate empty player.name String inputs
        if (playerResponse.contains("pl")) { // silly linguistic hack
            System.out.println("Human vs Human Game: detected");
            System.out.println("What is Player 1's name?");
            String nameInput1 = scanner.nextLine();
            System.out.println("What is Player 2's name?");
            String nameInput2 = scanner.nextLine();
            Player player1 = new Human(nameInput1);
            Player player2 = new Human(nameInput2);
            player1.setName(nameInput1);
            player2.setName(nameInput2);

            players.add(player1);
            players.add(player2);
        } else if (playerResponse.contains("mp")) { // silly linguistic hack
            System.out.println("Human vs Bot Game: detected");
            System.out.println("What's the player's name?");
            String nameInput = scanner.nextLine();
            Player player = new Human(nameInput);
            Player bot = new Bot();
            player.setName(nameInput);

            players.add(player);
            players.add(bot);
        } else {
            System.out.println("Sorry, I didn't quite catch that, try '2 players' or 'vs computer'.");
            choosePlayers();
        }
        return players;

    }
    // Method to compare moves thrown
    public void compareMoves(Player player1, Player player2) throws IOException {
// TODO: Add setTimeout like function for count down with s.out
        String player1Move = player1.getCurrentMove();
        String player2Move = player2.getCurrentMove();
        System.out.println(player1.getName() + " chose " + player1.getCurrentMove());
        System.out.println(player2.getName() + " chose " + player2.getCurrentMove());
// TODO: DRY this out.
        if(player1Move.equals(player2Move)){
            System.out.println("You both threw " + player1.getCurrentMove());
            System.out.println("It's a Draw");
            writeToFile(recordsFile, player1, player2,"draw");
        } else if(player1Move.equals("rock") && player2Move.equals("scissors")) {
            System.out.println("Rock Smashes Scissors");
            System.out.println(player1.getName() + " beats " + player2.getName());
            player1.setWins(player1.getWins()+1);
            writeToFile(recordsFile, player1, player2, "win");
        } else if(player1Move.equals("rock") && player2Move.equals("paper")) {
            System.out.println("Paper covers Rock");
            System.out.println(player2.getName() + " beats " + player1.getName());
            player1.setLosses(player1.getLosses()+1);
            writeToFile(recordsFile, player1, player2,"loss");
        } else if(player1Move.equals("scissors") && player2Move.equals("rock")) {
            System.out.println("Rock smashes Scissors");
            System.out.println(player2.getName() + " beats " + player1.getName());
            player1.setLosses(player1.getLosses()+1);
            writeToFile(recordsFile, player1, player2, "loss");
        } else if(player1Move.equals("scissors") && player2Move.equals("paper")) {
            System.out.println("Scissors cuts Paper");
            System.out.println(player1.getName() + " beats " + player2.getName());
            player1.setWins(player1.getWins()+1);
            writeToFile(recordsFile, player1, player2, "win");
        } else if(player1Move.equals("paper") && player2Move.equals("rock")) {
            System.out.println("Paper covers Rock");
            System.out.println(player1.getName() + " beats " + player2.getName());
            player1.setWins(player1.getWins()+1);
            writeToFile(recordsFile, player1, player2, "win");
        } else if(player1Move.equals("paper") && player2Move.equals("scissors")) {
            System.out.println("Scissors cuts Paper");
            System.out.println(player1.getName() + " beats " + player2.getName());
            player1.setWins(player1.getWins()+1);
            writeToFile(recordsFile, player1, player2, "loss");
        } else {
            System.out.println("Something went terribly wrong, please quit and restart the game:");
        }

        printMenu();
    }

//    private void refactorWinner(Player player1, Player player2){
//       if(player1.getCurrentMove().equals(player2.getCurrentMove())){
//           System.out.println(player1.getName() + " chose " + player1.getCurrentMove());
//       }
//    }


// TODO: will include function to see current wins/losses for pair of existing players.
//  Where to include in options?
// TODO: need to ensure saving user instance to keep wins/losses record properly
    public static void userStats(Player player){
        System.out.println(player.getName() + "stats:");
        String resultString = String.format("%s went %d - %d", player.getName(), player.getWins(), player.getLosses());
        System.out.println(resultString);

    }

// TODO: consider exercising ArrayList recordList for read AND write
    public void readFromFile(String filename) throws IOException {
        ArrayList<String> recordList = new ArrayList<String>();

        String file = filename;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        try {
            String currentLine = reader.readLine();

            while (currentLine != null) {
                // record format: name,wins,loses
                String[] data = currentLine.split(",");
                String result = "";
                try {
                    result += String.format("%s picked %s and %s picked %s. It was a %s", data[0], data[1], data[2], data[3], data[4]);
                } catch(Exception e){
                    result = "invalid record on file";
                }

                recordList.add(result);
                currentLine = reader.readLine();
            }
        }catch(IOException e){
            System.out.println("Unable to read records, please contact dev");
            System.out.println(e);
        }finally{
            reader.close();
        }
        recordList.forEach( record -> System.out.println(record + "\n"));
    }

    public void writeToFile(String filename, Player player1, Player player2, String result) throws IOException {
        Path path = Paths.get(filename);

        BufferedWriter bufferedWriter = new BufferedWriter((new FileWriter(filename, true)));
        String record = player1.getName()+ "," + player1.getCurrentMove() + "," + player2.getName() + "," + player2.getCurrentMove() + "," + result;
        bufferedWriter.write("\n");
        bufferedWriter.write(record);

        bufferedWriter.close();
    }



}
