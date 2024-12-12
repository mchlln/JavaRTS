package ubx.project.javarts.Model;

/**
 * Class used to determine the size of a building on the map
 */
public class Size {
    private final int width;
    private final int height;

    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }
    /**
     * @return the width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return an array with the width at index 0 and height at index 1.
     */
    public int[] getSize() {
        return new int[]{width, height};
    }

}
