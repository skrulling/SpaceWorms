import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Algorithm {
    /**
     * Class used to represent a square, and how many throws it took to get to this square
     */
    static class SquareDistance{
        int number;
        int distance;
        Square square;
    }

    /**
     * Algorithm to find the least amount of throws needed to finish a game of space worms
     * at the given board, and with given goal.
     * @param board Board you want to use
     * @return  number of throws needed to finish
     */
    public static int findMinDiceThrows(Board board){
        int goal = board.getFinish();
        HashMap<Integer, Square> visited = new HashMap<>();
        Queue<SquareDistance> q = new LinkedList<>();
        int boardSize = board.getDimX() * board.getDimY();

        // Starting with the first square
        SquareDistance sd = new SquareDistance();
        sd.number = board.getSquareAt(1).getNumber();
        sd.distance = 0;
        sd.square = board.getSquareAt(1);

        // Adding to visited and to queue
        visited.put(sd.number, sd.square);
        q.add(sd);

        while (!q.isEmpty()){
            sd = q.remove();
            // Square we are currently checking
            int number = sd.number;

            // Checking of this is the finish square, breaking out of the loop if it is.
            // We are done when it is found
            if (number == goal)
                break;

            // Else we find all the squares reachable from this square, and we calculate their distances
            // and then we add them to the queue
            for (int i = number + 1; i <= (number + 6) && i <= boardSize; i++){
                // Check if square is not already visited
                if (!visited.containsKey(i)){
                    SquareDistance sdTemp = new SquareDistance();
                    sdTemp.distance = (sd.distance + 1);
                    // If the square has a wormhole, we get the square the wormhole goes to
                    Square temp = board.getSquareAt(i).isHasWormhole() ?
                            board.getSquareAt(board.getSquareAt(i).getWormhole()) :
                            board.getSquareAt(i);
                    sdTemp.number = temp.getNumber();
                    sdTemp.square = temp;
                    visited.put(sdTemp.number, sdTemp.square);
                    q.add(sdTemp);
                }
            }
        }
        return sd.distance;
    }
}
