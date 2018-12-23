import org.json.JSONObject;


import java.io.IOException;

public class Main {
    private static String jsonBOARD = "https://visningsrom.stacc.com/dd_server_worms/rest/boards/2";
    private static JSONObject objBOARD;

    public static void main(String[] args) {
        try {
            objBOARD = JSONUtils.JSONfromURL(jsonBOARD);
        }catch (IOException e){
            e.printStackTrace();
        }

        Board board = new Board(objBOARD);
        Player bob = new Player("Bob", board);
        boolean done = false;
        while (!done){
            bob.moveForwardDiceRoll();
            if (bob.getPosition() == board.getFinish()){
                done = true;
                System.out.println(bob.getName() + " has won the game!");
            }
        }
    }
}
