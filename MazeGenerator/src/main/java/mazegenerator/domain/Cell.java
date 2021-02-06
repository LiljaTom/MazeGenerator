package mazegenerator.domain;

/**
 * Component for maze
 * 
 * @author lilja
 */
public class Cell {

    private int y;
    private int x;

    /**
     * Constructor for cell, initializes object.
     * 
     * @param y Cell's y coordinate
     * @param x Cell's x coordinate
     */
    public Cell(int y, int x) {
        this.y = y;
        this.x = x;
    }

    /**
     * 
     * @return Cell's y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * 
     * @return Cell's x coordinate
     */
    public int getX() {
        return x;
    }

}
