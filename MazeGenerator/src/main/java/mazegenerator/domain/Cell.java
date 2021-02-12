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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + this.y;
        hash = 11 * hash + this.x;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cell other = (Cell) obj;
        if (this.y != other.y) {
            return false;
        }
        if (this.x != other.x) {
            return false;
        }
        return true;
    }

}
