package mazegenerator.domain;

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
     * @param y y coordinate to initialize maze
     * @param x x coordinate to initialize maze
     */
    public Prim(int width, int height, int y, int x) {
        helper = new MazeHelper(height, width);
        helper.initMaze(y, x);
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

        while (!helper.getFrontierCells().isEmpty()) {
            int index = rnd.nextInt(helper.frontierlistSize());

            Cell frontier = helper.getFrontierCells().get(index);
            Cell neighbour = helper.getRandomNeighbour(frontier.getY(), frontier.getX());

            helper.connect(frontier.getY(), frontier.getX(), neighbour.getY(), neighbour.getX());
            helper.addFrontierCells(frontier.getY(), frontier.getX());
            helper.getPaths()[frontier.getY()][frontier.getX()] = true;

            helper.getFrontierCells().remove(index);
        }

    }
    
    /**
     * Step by step builds the maze
     */
    public void buildMaze() {
        Random rnd = new Random();
        
        if(!helper.getFrontierCells().isEmpty()) {
            int index = rnd.nextInt(helper.frontierlistSize());

            Cell frontier = helper.getFrontierCells().get(index);
            Cell neighbour = helper.getRandomNeighbour(frontier.getY(), frontier.getX());

            helper.connect(frontier.getY(), frontier.getX(), neighbour.getY(), neighbour.getX());
            helper.addFrontierCells(frontier.getY(), frontier.getX());
            helper.getPaths()[frontier.getY()][frontier.getX()] = true;

            helper.getFrontierCells().remove(index);
        }
    }

    public boolean[][] getPaths() {
        return helper.getPaths();
    }

    public MazeArray<Cell> getFrontiersList() {
        return helper.getFrontierCells();
    }

    public boolean[][] getFrontiers() {
        return helper.getFrontiers();
    }

}
