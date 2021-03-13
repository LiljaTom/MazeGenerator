package mazegenerator.domain;

/**
 * Class which builds maze using prim's algorithm
 *
 * @author lilja
 */
public class Prim {

    private MazeHelper helper;
    private int y, x;

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
        this.y = y;
        this.x = x;
    }

    /**
     * Creates complete maze using randomized prim's algorithm
     *
     * 
     */
    public void createMaze() {

        while (!helper.getFrontierCells().isEmpty()) {
            long i = System.nanoTime() % helper.getFrontierCells().size();    
            int index = (int) i;
            
            Cell frontier = getRandomFrontier();
            int frontierY = frontier.getY();
            int frontierX = frontier.getX();

            makeConnection(frontierY, frontierX);
            
            addFrontiers(frontier);
        }

    }
    
    /**
     * Step by step builds the maze
     */
    public void buildMaze() {
        helper.clearNewPaths();
        helper.clearNewFrontiers();
        
        if(!helper.getFrontierCells().isEmpty()) {
            Cell frontier = getRandomFrontier();
            int frontierY = frontier.getY();
            int frontierX = frontier.getX();

            makeConnection(frontierY, frontierX);
            
            addFrontiers(frontier);
        }
    }
    
    /**
     * Picks the next cell, where the maze expands
     * 
     * @return Random frontiercell
     */
    public Cell getRandomFrontier() {
        return helper.getFrontierCells().getRandom();
    }
    
    /**
     * Connects selected cell to random cell in maze
     * 
     * @param frontierY
     * @param frontierX 
     */
    public void makeConnection(int frontierY, int frontierX) {
        Cell neighbour = helper.getRandomNeighbour(frontierY, frontierX);
        
        helper.connect(frontierY, frontierX, neighbour.getY(), neighbour.getX()); 
    }
    
    public void addFrontiers(Cell frontier) {
        helper.addFrontierCells(frontier.getY(), frontier.getX());
        helper.getFrontierCells().removeObject(frontier);
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
    
    public MazeArray<Cell> getNewPaths() {
        return helper.getNewPaths();
    }
    
    public MazeArray<Cell> getNewFrontiers() {
        return helper.getNewFrontiers();
    }

}
