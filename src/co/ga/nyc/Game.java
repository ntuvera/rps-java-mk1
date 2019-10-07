package co.ga.nyc;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private ArrayList<Player> players;
    private String recordsFile = "records.txt";
    private ArrayList<Player> playerList;

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

//        ArrayList<Player> players = new ArrayList<>();
        Scanner scanner = new Scanner (System.in);

        System.out.println("Will you be playing with 2 players or vs. the computer? ('2 players' or 'vs computer')");
        String playerResponse = scanner.nextLine().toLowerCase();


        // method to start game

        if (playerResponse.contains("pl")) {
            System.out.println("Human vs Human detected");
            System.out.println("What is Player 1's name?");
            String nameInput1 = scanner.nextLine();
            System.out.println("What is Player 2's name?");
            String nameInput2 = scanner.nextLine();
            Player player1 = new Human(nameInput1);
            Player player2 = new Human(nameInput2);
            player1.setName(nameInput1);
            player2.setName(nameInput2);

            playerList.add(player1);
            playerList.add(player1);
//            players.add(player1);
//            players.add(player2);
        } else if (playerResponse.contains("omp")) {
            System.out.println("Human vs Bot detected");
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
        return players;

    }
    // Method to compare moves thrown
    // TODO: DRY this out... needs it badly
    public void compareMoves(Player player1, Player player2) throws IOException {
        String player1Move = player1.getCurrentMove();
        String player2Move = player2.getCurrentMove();

        if(player1Move.equals(player2Move)){
            System.out.println(player1.getName() + " chose " + player1.getCurrentMove());
            System.out.println(player2.getName() + " chose " + player2.getCurrentMove());
            System.out.println("It's a Draw");
            writeToFile(recordsFile, player1, player2,"draw");
        } else if(player1Move.equals("rock") && player2Move.equals("scissors")) {
            System.out.println(player1.getName() + " chose " + player1.getCurrentMove());
            System.out.println(player2.getName() + " chose " + player2.getCurrentMove());
            System.out.println("Rock Smashes Scissors");
            System.out.println(player1.getName() + " beats " + player2.getName());
            player1.setWins(player1.getWins()+1);
            writeToFile(recordsFile, player1, player2, "win");
        } else if(player1Move.equals("rock") && player2Move.equals("paper")) {
            System.out.println(player1.getName() + " chose " + player1.getCurrentMove());
            System.out.println(player2.getName() + " chose " + player2.getCurrentMove());
            System.out.println("Paper Wraps around Rock");
            System.out.println(player1.getName() + " beats " + player2.getName());
            player1.setLosses(player1.getLosses()+1);
            writeToFile(recordsFile, player1, player2,"loss");
        } else if(player1Move.equals("scissors") && player2Move.equals("rock")) {
            System.out.println(player1.getName() + " chose " + player1.getCurrentMove());
            System.out.println(player2.getName() + " chose " + player2.getCurrentMove());
            System.out.println("Rock smashes Scissors");
            System.out.println(player2.getName() + " beats " + player1.getName());
            player1.setLosses(player1.getLosses()+1);
            writeToFile(recordsFile, player1, player2, "loss");
        } else if(player1Move.equals("scissors") && player2Move.equals("paper")) {
            System.out.println(player1.getName() + " chose " + player1.getCurrentMove());
            System.out.println(player2.getName() + " chose " + player2.getCurrentMove());
            System.out.println("Scissors cuts Paper");
            System.out.println(player1.getName() + " beats " + player2.getName());
            player1.setWins(player1.getWins()+1);
            writeToFile(recordsFile, player1, player2, "win");
        } else if(player1Move.equals("paper") && player2Move.equals("rock")) {
            System.out.println(player1.getName() + " chose " + player1.getCurrentMove());
            System.out.println(player2.getName() + " chose " + player2.getCurrentMove());
            System.out.println("Paper wraps Rock");
            System.out.println(player1.getName() + " beats " + player2.getName());
            player1.setWins(player1.getWins()+1);
            writeToFile(recordsFile, player1, player2, "win");
        } else if(player1Move.equals("paper") && player2Move.equals("scissors")) {
            System.out.println(player1.getName() + " chose " + player1.getCurrentMove());
            System.out.println(player2.getName() + " chose " + player2.getCurrentMove());
            System.out.println("Scissors cuts Paper");
            System.out.println(player1.getName() + " beats " + player2.getName());
            player1.setWins(player1.getWins()+1);
            writeToFile(recordsFile, player1, player2, "loss");
        } else {
            System.out.println(player1.getName() + " chose " + player1.getCurrentMove());
            System.out.println(player2.getName() + " chose " + player2.getCurrentMove());
            System.out.println("Something went terribly wrong, please quit and restart the game:");
        }

        printMenu();
    }
    private void determineWinner(ArrayList<Player> playerList){
        System.out.println("do a thing");
       return;
    }


    public static void displayResults(ArrayList<Player> players){
        System.out.println("Finally Tall\n");
        players.forEach( player -> {
            String resultString = String.format("%s went %d - %d", player.getName(), player.getWins(), player.getLosses());
            System.out.println(resultString);
        });

    }

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

                result += String.format("%s picked %s and %s picked %s. It was a %s", data[0], data[1], data[2], data[3], data[4]);

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
