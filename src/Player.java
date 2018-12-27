import java.util.ArrayList;
import java.util.Random;

public class Player {
    private static Random rnd = new Random();

    private String name;
    private int position;
    private ArrayList<Integer> previousMoves;
    private Board board;

    /**
     * Player used to play space worms on given board
     * @param name The name you want to give the player
     * @param board Board the player will be playing on
     */
    public Player(String name, Board board){
        this.name = name;
        position = 1;
        previousMoves = new ArrayList<>();
        this.board = board;
    }

    /**
     * Move player to given position on board
     * @param pos position the player should be moved to
     */
    public void goToSquare(int pos){
        if (pos <= 72){
            Square nextSquare = board.getSquareAt(pos);
            position = nextSquare.isHasWormhole() ? nextSquare.getWormhole() : pos;
            //System.out.println(name + " moved to position " + position);
            previousMoves.add(position);
        }
//        else {
//            System.out.println("Tried to move out of bounds, so player stayed put this round!");
//        }

    }

    public int getNumberOfMoves(){
        return previousMoves.size();
    }

    /**
     * Roll the dice, and move player to correct position
     */
    public void moveForwardDiceRoll(){
        int steps = rollDice();
        int pos = position + steps;
        goToSquare(pos);
    }

    private int rollDice(){
        int num = rnd.nextInt(6) + 1;
        //System.out.println("Dice rolled: " + num);
        return num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ArrayList<Integer> getPreviousMoves() {
        return previousMoves;
    }

    public void setPreviousMoves(ArrayList<Integer> previousMoves) {
        this.previousMoves = previousMoves;
    }
}
