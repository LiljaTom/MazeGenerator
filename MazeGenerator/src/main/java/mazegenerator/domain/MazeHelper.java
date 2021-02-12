package mazegenerator.domain;

import java.util.ArrayList;

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
    private MazeArray<Cell> frontierCells;

    /**
     * Constructor for MazeHelper, initializes object.
     *
     * @param height maze height
     * @param width maze width
     */
    public MazeHelper(int height, int width) {
        this.height = height;
        this.width = width;

        this.paths = new boolean[height][width];
        this.frontiers = new boolean[height][width];
        this.frontierCells = new MazeArray<>();
    }

    /**
     * Initializes maze's first cell and finds it's frontier cells
     *
     * @param y starting y coordinate
     * @param x starting x coordinate
     */
    public void initMaze(int y, int x) {
        this.paths[y][x] = true;
        addFrontierCells(y, x);
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
     * Checks if cell is valid to be frontier cell
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
     * Makes all valid cells to frotier cells
     *
     * @param y y coordinate
     * @param x x coordinate
     */
    public void addFrontierCells(int y, int x) {
        int rightX = x + 2;
        int leftX = x - 2;
        int upY = y - 2;
        int downY = y + 2;

        //right
        if (validForFrontier(y, rightX)) {
            frontierCells.add(new Cell(y, rightX));
            frontiers[y][rightX] = true;
        }

        //left
        if (validForFrontier(y, leftX)) {
            frontierCells.add(new Cell(y, leftX));
            frontiers[y][leftX] = true;
        }

        //up
        if (validForFrontier(upY, x)) {
            frontierCells.add(new Cell(upY, x));
            frontiers[upY][x] = true;
        }

        //down
        if (validForFrontier(downY, x)) {
            frontierCells.add(new Cell(downY, x));
            frontiers[downY][x] = true;
        }
    }
    
    /**
     * Connects path between starting and end cells
     * 
     * @param startY start cell's y coordinate
     * @param startX start cell's x coordinate
     * @param endY end cell's y coordinate
     * @param endX end cell's x coordinate
     */
    public void connect(int startY, int startX, int endY, int endX) {
        this.paths[startY][startX] = true;
        //Same row
        if (startX == endX) {
            //start is up
            if (startY < endY) {
                this.paths[startY + 1][startX] = true;
            } else {
                this.paths[startY - 1][startX] = true;
            }
        }

        //Same column
        if (startY == endY) {
            //start on left
            if (startX < endX) {
                this.paths[startY][startX + 1] = true;
            } else {
                this.paths[startY][startX - 1] = true;
            }
        }

        paths[endY][endX] = true;
    }
    
    public Cell getRandomNeighbour(int y, int x) {
        MazeArray<Cell> neighbours = new MazeArray<>();
        
        int rightX = x + 2;
        int leftX = x - 2;
        int upY = y - 2;
        int downY = y + 2;
        
        if(inGrid(y, rightX) && paths[y][rightX]) {
            neighbours.add(new Cell(y, rightX));
        }
        
        if(inGrid(y, leftX) && paths[y][leftX]) {
            neighbours.add(new Cell(y, leftX));
        }
        
        if(inGrid(upY, x) && paths[upY][x]) {
            neighbours.add(new Cell(upY, x));
        }
        
        if(inGrid(downY, x) && paths[downY][x]) {
            neighbours.add(new Cell(downY, x));
        }  
        
        return neighbours.getRandom();
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

    /**
     *
     * @return frontiercell list size
     */
    public int frontierlistSize() {
        return frontierCells.size();
    }

    /**
     * 
     * @return frontiercell list
     */
    public MazeArray<Cell> getFrontierCells() {
        return frontierCells;
    }

}
