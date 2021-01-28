package mazegenerator.domain;

import java.util.ArrayList;
import java.util.Random;

public class Prim {

    private int width;
    private int height;
    private boolean[][] paths;
    private boolean[][] frontiers;
    private ArrayList<Cell> frontiersList;

    public Prim(int width, int height) {
        this.width = width;
        this.height = height;

        this.paths = new boolean[height][width];
        this.frontiers = new boolean[height][width];

        this.frontiersList = new ArrayList<>();
    }

    public void initMaze(int y, int x) {
        this.paths[y][x] = true;
        frontierCells(y, x);
    }

    /*
    public ArrayList nextPath() {
        this.pathsToFill = new ArrayList<>();

        if (!frontiersList.isEmpty()) {

            Random rnd = new Random();
            int index = rnd.nextInt(frontiersList.size());

            Cell frontierCell = frontiersList.get(index);
            Cell neighbour = randomNeighbour(frontierCell.getY(), frontierCell.getX());

            connect(frontierCell, neighbour);

            frontierCells(frontierCell.getY(), frontierCell.getX());

            this.paths[frontierCell.getY()][frontierCell.getX()] = true;
            frontiersList.remove(index);
        }

        return pathsToFill;
    }
     */
    public void createMaze(int y, int x) {
        Random rnd = new Random();

        this.frontiers[y][x] = true;
        this.paths[y][x] = true;
        frontierCells(y, x);

        while (!this.frontiersList.isEmpty()) {
            int index = rnd.nextInt(frontiersList.size());

            Cell frontier = frontiersList.get(index);
            Cell neighbour = randomNeighbour(frontier.getY(), frontier.getX());

            connect(frontier, neighbour);
            frontierCells(frontier.getY(), frontier.getX());
            paths[frontier.getY()][frontier.getX()] = true;
            frontiersList.remove(index);

        }
    }

    public void connect(Cell start, Cell end) {
        int startX = start.getX();
        int startY = start.getY();

        int endX = end.getX();
        int endY = end.getY();

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

        this.paths[endY][endX] = true;

    }

    public boolean inGrid(int y, int x) {

        if (y > 0 && x > 0 && y < height - 1 && x < width - 1) {
            return true;
        }

        return false;
    }

    public void frontierCells(int y, int x) {
        int rightX = x + 2;
        int leftX = x - 2;
        int upY = y - 2;
        int downY = y + 2;

        //right      
        if (inGrid(y, rightX)) {
            if (!paths[y][rightX] && !frontiers[y][rightX]) {
                frontiersList.add(new Cell(y, rightX));
                frontiers[y][rightX] = true;
            }
        }

        //left
        if (inGrid(y, leftX)) {
            if (!paths[y][leftX] && !frontiers[y][leftX]) {
                frontiersList.add(new Cell(y, leftX));
                frontiers[y][leftX] = true;
            }
        }

        //up
        if (inGrid(upY, x)) {
            if (!paths[upY][x] && !frontiers[upY][x]) {
                frontiersList.add(new Cell(upY, x));
                frontiers[upY][x] = true;
            }
        }

        //down
        if (inGrid(downY, x)) {
            if (!paths[downY][x] && !frontiers[downY][x]) {
                frontiersList.add(new Cell(downY, x));
                frontiers[downY][x] = true;
            }
        }

    }

    public Cell randomNeighbour(int y, int x) {
        ArrayList<Cell> neighbours = new ArrayList<>();

        int rightX = x + 2;
        int leftX = x - 2;
        int upY = y - 2;
        int downY = y + 2;

        if (inGrid(y, rightX)) {
            if (paths[y][rightX]) {
                neighbours.add(new Cell(y, rightX));
            }
        }

        if (inGrid(y, leftX)) {
            if (paths[y][leftX]) {
                neighbours.add(new Cell(y, leftX));
            }
        }

        if (inGrid(upY, x)) {
            if (paths[upY][x]) {
                neighbours.add(new Cell(upY, x));
            }
        }

        if (inGrid(downY, x)) {
            if (paths[downY][x]) {
                neighbours.add(new Cell(downY, x));
            }
        }

        Random rnd = new Random();
        int index = rnd.nextInt(neighbours.size());

        return neighbours.get(index);
    }

    public boolean[][] getPaths() {
        return paths;
    }

    public ArrayList<Cell> getFrontiersList() {
        return frontiersList;
    }

    public boolean[][] getFrontiers() {
        return frontiers;
    }

    public int getPathCount() {
        int count = 0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (paths[y][x]) {
                    count++;
                }
            }
        }

        return count;
    }

    public int getWallCount() {
        return width * height - getPathCount();
    }

}
