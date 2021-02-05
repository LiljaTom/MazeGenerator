package mazegenerator.domain;

/**
 * Helper class which contains methods that help with maze operations
 *
 * @author lilja
 */
public class MazeHelper {

    private int height;
    private int width;
    private boolean[][] paths;
    private boolean[][] frontiers;

    /**
     * Initializes MazeHelper class
     *
     * @param height maze height
     * @param width maze width
     */
    public MazeHelper(int height, int width) {
        this.height = height;
        this.width = width;

        this.paths = new boolean[height][width];
        this.frontiers = new boolean[height][width];
    }

    /**
     * Method to check coordinates
     *
     * @param y y coordinate
     * @param x x coordinate
     *
     * @return true if in grid and valid, else false
     */
    public boolean inGrid(int y, int x) {

        if (y > 0 && x > 0 && y < height - 1 && x < width - 1) {
            return true;
        }

        return false;
    }

    /**
     * Finds all valid frontier cells for given coordinates.
     *
     * @param y y coordinate
     * @param x x coordinate
     *
     * @return true if valid to be frontier, else false
     */
    public boolean validForFrontier(int y, int x) {

        if (inGrid(y, x)) {
            if (!paths[y][x] && !frontiers[y][x]) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return two dimensional array containing path cells
     */
    public boolean[][] getPaths() {
        return paths;
    }

    /**
     *
     * @return two dimensional array containing frontier cells
     */
    public boolean[][] getFrontiers() {
        return frontiers;
    }

}
