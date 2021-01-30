package mazegenerator.domain;

public class MazeHelper {

    private int height;
    private int width;
    private boolean[][] paths;
    private boolean[][] frontiers;

    public MazeHelper(int height, int width) {
        this.height = height;
        this.width = width;

        this.paths = new boolean[height][width];
        this.frontiers = new boolean[height][width];
    }

    public boolean inGrid(int y, int x) {

        if (y > 0 && x > 0 && y < height - 1 && x < width - 1) {
            return true;
        }

        return false;
    }

    public boolean validForFrontier(int y, int x) {

        if (inGrid(y, x)) {
            if (!paths[y][x] && !frontiers[y][x]) {
                return true;
            }
        }
        return false;
    }

    public boolean[][] getPaths() {
        return paths;
    }

    public boolean[][] getFrontiers() {
        return frontiers;
    }

}
