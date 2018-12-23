public class Square {
    private int number;
    private int posX;
    private int posY;
    private String name;
    private boolean hasWormhole;
    private int wormhole;
    private String nextURL;

    public Square(int number, int posX, int posY, String name, boolean hasWormhole, int wormhole,
                  String nextURL) {
        this.number = number;
        this.posX = posX;
        this.posY = posY;
        this.name = name;
        this.hasWormhole = hasWormhole;
        this.wormhole = wormhole;
        this.nextURL = nextURL;
    }
    // Constructor for instances where square does not have wormhole
    public Square(int number, int posX, int posY, String name, boolean hasWormhole, String nextURL) {
        this.number = number;
        this.posX = posX;
        this.posY = posY;
        this.name = name;
        this.hasWormhole = hasWormhole;
        this.wormhole = -1;
        this.nextURL = nextURL;
    }

    public String getNextURL() {
        return nextURL;
    }

    @Override
    public String toString() {
        return "Square{" +
                "number=" + number +
                ", posX=" + posX +
                ", posY=" + posY +
                ", name='" + name + '\'' +
                ", hasWormhole=" + hasWormhole +
                ", wormhole=" + wormhole +
                ", nextURL='" + nextURL + '\'' +
                '}';
    }

    public void setNextURL(String nextURL) {
        this.nextURL = nextURL;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHasWormhole() {
        return hasWormhole;
    }

    public void setHasWormhole(boolean hasWormhole) {
        this.hasWormhole = hasWormhole;
    }

    public int getWormhole() {
        return wormhole;
    }

    public void setWormhole(int wormhole) {
        this.wormhole = wormhole;
    }

}
