package mazegenerator.domain;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class which builds maze using prim's algorithm
 * 
 * @author lilja
 */
public class Prim {

    private MazeHelper helper;

    /**
     * Constructor for Prim, initializes object.
     * 
     * @param width maze width
     * @param height maze height
     */
    public Prim(int width, int height) {
        helper = new MazeHelper(height, width);
    }

    /**
     * Creates complete maze using randomized prim's algorithm
     * 
     * @param y starting y coordinate
     * @param x starting x coordinate
     */
    public void createMaze(int y, int x) {
        Random rnd = new Random();
        
        helper.getFrontiers()[y][x] = true;
        helper.getPaths()[y][x] = true;
        helper.addFrontierCells(y, x);
        
        while(!helper.getFrontierCells().isEmpty()) {
            int index = rnd.nextInt(helper.frontierlistSize());
            
            Cell frontier = helper.getFrontierCells().get(index);
            Cell neighbour = randomNeighbour(frontier.getY(), frontier.getX());
            
            helper.connect(frontier.getY(), frontier.getX(), neighbour.getY(), neighbour.getX());
            helper.addFrontierCells(frontier.getY(), frontier.getX());
            helper.getPaths()[frontier.getY()][frontier.getX()] = true;
            
            helper.getFrontierCells().remove(index);
        }
        
    }


    public Cell randomNeighbour(int y, int x) {
        ArrayList<Cell> neighbours = new ArrayList<>();

        int rightX = x + 2;
        int leftX = x - 2;
        int upY = y - 2;
        int downY = y + 2;

        if (helper.inGrid(y, rightX)) {
            if (helper.getPaths()[y][rightX]) {
                neighbours.add(new Cell(y, rightX));
            }
        }

        if (helper.inGrid(y, leftX)) {
            if (helper.getPaths()[y][leftX]) {
                neighbours.add(new Cell(y, leftX));
            }
        }

        if (helper.inGrid(upY, x)) {
            if (helper.getPaths()[upY][x]) {
                neighbours.add(new Cell(upY, x));
            }
        }

        if (helper.inGrid(downY, x)) {
            if (helper.getPaths()[downY][x]) {
                neighbours.add(new Cell(downY, x));
            }
        }

        Random rnd = new Random();
        int index = rnd.nextInt(neighbours.size());

        return neighbours.get(index);
    }

    public boolean[][] getPaths() {
        return helper.getPaths();
    }

    public ArrayList<Cell> getFrontiersList() {
        return helper.getFrontierCells();
    }

    public boolean[][] getFrontiers() {
        return helper.getFrontiers();
    }

}
