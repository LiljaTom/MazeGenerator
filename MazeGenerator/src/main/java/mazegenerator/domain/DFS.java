package mazegenerator.domain;

/**
 *
 * @author lilja
 */
public class DFS {

    private MazeHelper helper;
    private int y, x;
    private MazeArray<Cell> stack;
    private MazeArray<Cell> completePaths;

    public DFS(int width, int height, int y, int x) {
        this.helper = new MazeHelper(height, width);
        this.y = y;
        this.x = x;
        this.stack = new MazeArray<>();
        this.completePaths = new MazeArray<>();

        initDfs();
    }

    public void initDfs() {
        stack.add(new Cell(y, x));
        this.helper.getPaths()[y][x] = true;
        this.helper.getNewPaths().add(new Cell(y, x));
    }

    public void createMaze() {

        while (!stack.isEmpty()) {
            Cell current = stack.peek();
            Cell next = this.helper.getRandomNeighbouringWall(current.getY(), current.getX());

            if (next == null) {
                stack.pop();
                continue;
            }
            stack.add(next);

            this.helper.connect(current.getY(), current.getX(), next.getY(), next.getX());

        }
    }

    public void buildMaze() {
        helper.clearNewPaths();
        helper.clearNewFrontiers();

        if (!stack.isEmpty()) {
            Cell current = stack.peek();
            Cell next = this.helper.getRandomNeighbouringWall(current.getY(), current.getX());

            if (next == null) {
                Cell pop = stack.pop();
                this.completePaths.add(pop);
                cellBetween(pop, stack.peek());
                return;
            }
            this.completePaths = new MazeArray<>();

            stack.add(next);

            this.helper.connect(current.getY(), current.getX(), next.getY(), next.getX());

        }
    }

    public void cellBetween(Cell start, Cell end) {
        int startY = start.getY();
        int startX = start.getX();
        int endY = end.getY();
        int endX = end.getX();

        Cell between = new Cell(0, 0);
        //Same row
        if (startX == endX) {
            //start is up
            if (startY < endY) {
                between = new Cell(startY + 1, startX);
            } else {
                between = new Cell(startY - 1, startX);
            }
        }

        //Same column
        if (startY == endY) {
            //start on left
            if (startX < endX) {
                between = new Cell(startY, startX + 1);
            } else {
                between = new Cell(startY, startX - 1);
            }
        }
        
        this.completePaths.add(between);
    }

    public MazeArray<Cell> getNewPaths() {
        return helper.getNewPaths();
    }

    public boolean[][] getPaths() {
        return helper.getPaths();
    }

    public MazeArray<Cell> getCompletePaths() {
        return completePaths;
    }

}
