package mazegenerator.domain;

/**
 *
 * @author lilja
 */
public class Kruskal {

    private MazeArray<Cell> walls;
    private MazeHelper helper;
    private int[] link, size;
    
    private int height, width;

    public Kruskal(int height, int width) {
        this.walls = new MazeArray<>();
        int n = (height - 2) * (width - 2);
        link = new int[n + 1];
        size = new int[n + 1];
        
        this.height = height;
        this.width = width;
        
        helper = new MazeHelper(height, width);

        for (int y = 1; y < height; y++) {
            for (int x = 1; x < width; x++) {
                walls.add(new Cell(y, x));
            }
        }

        for (int i = 1; i < n; i++) {
            link[i] = i;
            size[i] = 1;
        }
    }

    public void createMaze() {
        
        while (!walls.isEmpty()) {
            Cell wall = walls.getRandom();
            int y = wall.getY();
            int x = wall.getX();
            
            int leftX = x - 1;
            int rightX = x + 1;
            int upY = y - 1;
            int downY = y + 1;
            
            if(horizontal()) {
                
                if(helper.inGrid(y, leftX) && helper.inGrid(y, rightX)) {
                    
                    if(differentSet(getIndex(y, leftX), getIndex(y, rightX))) {
                        helper.connect(y, rightX, y, leftX);
                        union(getIndex(y, leftX), getIndex(y, rightX));
                    }
                    
                    
                }
       
            } else {

            }

        }
    }
    
    public boolean differentSet(int a, int b) {
        return find(a) != find(b);
    }
    
    public int getIndex(int y, int x) {
        return (y - 1) * (this.width - 2) + x;
    }

    public int find(int x) {
        while (link[x] != x) {
            x = link[x];
        }

        return x;
    }
    
    public void union(int a, int b) {
        a = find(a);
        b = find(b);
        
        if (size[a] < size[b]) {
            int t = a; a = b; b = t;
        }
        size[a] += size[b];
        link[b] = a;
    }
    
    public void merge(int a, int b) {
        a = find(a);
        b = find(b);
        if(link[a] != link[b]) {
            union(a, b);
        }

    }

    public boolean horizontal() {
        long random = System.nanoTime() % 2;

        if (random == 0) {
            return false;
        }

        return true;
    }

    /**
     *
     *
     * @param y wall's y coordinate
     * @param x wall's x coordinate
     */
    public void cellsDividedByWall(int y, int x) {
        boolean horizontal = horizontal();

        if (horizontal) {
            // Checks if possible to connect horizontally
            if (helper.inGrid(y + 1, x) && helper.inGrid(y - 1, x)) {
                helper.connect(y + 1, x, y - 1, x);
            } else {
                helper.connect(y, x - 1, y, x + 1);
            }
        } else {
            // Checks if possible to connect vertically
            if(helper.inGrid(y, x + 1) && helper.inGrid(y, x - 1)) {
                helper.connect(y, x + 1, y, x - 1);
            } else {
                helper.connect(y + 1, x, y - 1, x);
            }
        }

    }
    
    public boolean[][] getPaths() {
        return helper.getPaths();
    }

}
