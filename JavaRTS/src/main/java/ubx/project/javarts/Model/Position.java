package ubx.project.javarts.Model;

/**
 * Class used to represent the position (with x and y coordinates) of a building on the map
 */
public class Position {
    private final int x;
    private final int y;
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y coordinate
     */
    public int getY() {
        return y;
    }
}
