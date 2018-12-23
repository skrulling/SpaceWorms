import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class Board {
    private String name;
    private int dimX;
    private int dimY;
    private int start;
    private int finish;
    private ArrayList<Square> squares;

    public Board(JSONObject objectBOARD) {
        this.name = objectBOARD.getString("name");
        this.dimX = objectBOARD.getInt("dimX");
        this.dimY = objectBOARD.getInt("dimY");
        this.start = objectBOARD.getInt("start");
        this.finish = objectBOARD.getInt("goal");
        squares = new ArrayList<>();
        // Links were not functioning with http, so changed to https
        String URLstart = objectBOARD.getString("startSquare").replaceAll("http", "https");

        for (int i = start; i <= (dimX * dimY); i++){
            String url = (i == 1) ? URLstart : squares.get(i - 2).getNextURL();
            try {
                JSONObject jsonSquare = JSONUtils.JSONfromURL(url);
                Square square = (jsonSquare.has("wormhole")) ? new Square(
                        jsonSquare.getInt("number"), jsonSquare.getInt("posX"),
                        jsonSquare.getInt("posY"), jsonSquare.getString("name"),
                        true, jsonSquare.getInt("wormhole"),
                        JSONUtils.getNextURL(jsonSquare.getJSONArray("links"))
                ) : new Square(
                        jsonSquare.getInt("number"), jsonSquare.getInt("posX"),
                        jsonSquare.getInt("posY"), jsonSquare.getString("name"),
                        false, JSONUtils.getNextURL(jsonSquare.getJSONArray("links"))
                );
                squares.add(square);
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }

    public Square getSquareAt(int num){
        return squares.get(num - 1);
    }

    @Override
    public String toString(){
        return "Board name: " + name + "\nStart: " + start + "\nFinish: " + finish;
    }

    public int getDimX() {
        return dimX;
    }

    public void setDimX(int dimX) {
        this.dimX = dimX;
    }

    public int getDimY() {
        return dimY;
    }

    public void setDimY(int dimY) {
        this.dimY = dimY;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }
}
