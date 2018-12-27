import org.json.JSONObject;


import java.io.IOException;

/**
 * This program will fetch a Space Worms board from an API
 * It will then calculate the least amount of throws needed to complete the game.
 * After, it will verify the calculated number by playing the game 10 000 time
 * keeping statistics about the amount of throws used by the players.
 */
public class Main {
    private static String jsonBOARD = "https://visningsrom.stacc.com/dd_server_worms/rest/boards/2";
    private static JSONObject objBOARD;

    public static void main(String[] args) {
        // Using the URL to build entire board from API
        try {
            System.out.println("Retrieving information from the API and building the board..");
            System.out.println("Please sit tight, it will soon be done");
            objBOARD = JSONUtils.JSONfromURL(jsonBOARD);
        }catch (IOException e){
            e.printStackTrace();
        }
        // Making a Board from the JSON object
        Board board = new Board(objBOARD);

        System.out.println("\n\nThis program will now run an algorithm that will calculate" +
                " the least amount of dice throws one can use to win a game of space worms on " +
                "the current board.");
        System.out.println("\nRunning algoritm on board..");

        int minThrows = Algorithm.findMinDiceThrows(board);
        System.out.println("\nThe minimum amount of throws required to win the game on this board is:\n" +
                minThrows);

        System.out.println("\n\nThe program will now run 10 000 instances of the game, it will " +
                "remember the least amount of throws used by any of the players to complete the game.\n" +
                "This is done to verify the number provided by the algorithm.\n");

        // Start with a large value, so we can assume no players will get a value this high
        int minThrowsPlayers = 10000;
        int maxThrowsPlayers = 0;
        // Array used to keep track of how many players end up using the different amounts of throws
        // We only make 1000 spaces since we dont
        int[] stats = new int[1000];
        // Running the game 10 000 times
        for (int i = 0; i < 10000; i++){
            Player bob = new Player("Bob", board);
            boolean done = false;
            while (!done){
                bob.moveForwardDiceRoll();
                if (bob.getPosition() == board.getFinish()){
                    done = true;
                }
            }
            int numMoves = bob.getNumberOfMoves();
            if (numMoves < stats.length)
                stats[numMoves]++;

            if (numMoves < minThrowsPlayers)
                minThrowsPlayers = numMoves;
            if (numMoves > maxThrowsPlayers)
                maxThrowsPlayers = numMoves;
        }
        System.out.println("After 10 000 games have been played, the minimum number of moves were:\n" +
                minThrowsPlayers);
        System.out.println("\n" + stats[minThrowsPlayers] + " players finished in " + minThrowsPlayers +
                " throws/moves");
        System.out.println("\nThe maximum amount of moves/throws used was " + maxThrowsPlayers);
        System.out.println("\nThe most common amount of throws used was " + findLargest(stats) +
                ", " + stats[findLargest(stats)] + " players used this amount of throws.");
    }

    private static int findLargest(int[] list){
        int indexLargest = 0;
        for (int i = 0; i < list.length; i++){
            if (list[i] > list[indexLargest]) indexLargest = i;
        }
        return indexLargest;
    }
}
