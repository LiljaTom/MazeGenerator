
import mazegenerator.domain.Cell;
import mazegenerator.domain.MazeHelper;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MazeHelperTest {

    MazeHelper helper;
    int height;
    int width;

    @Before
    public void setUp() {
        this.height = 10;
        this.width = 10;

        helper = new MazeHelper(height, width);
    }

    @Test
    public void inGridReturnsTrueForValid1() {
        int validY = 1;
        int validX = 1;

        assertTrue(helper.inGrid(validY, validX));
    }

    @Test
    public void inGridReturnsTrueForValid2() {
        int validY = 1;
        int validX = 8;

        assertTrue(helper.inGrid(validY, validX));
    }

    @Test
    public void inGridReturnsTrueForValid3() {
        int validY = 8;
        int validX = 1;

        assertTrue(helper.inGrid(validY, validX));
    }

    @Test
    public void inGridReturnsTrueForValid4() {
        int validY = 8;
        int validX = 8;

        assertTrue(helper.inGrid(validY, validX));
    }

    @Test
    public void inGridReturnsTrueForValid5() {
        int validY = 5;
        int validX = 5;

        assertTrue(helper.inGrid(validY, validX));
    }

    @Test
    public void inGridReturnsFalseforInvalid1() {
        int invalidY = 0;
        int invalidX = 0;

        assertFalse(helper.inGrid(invalidY, invalidX));
    }

    @Test
    public void inGridReturnsFalseforInvalid2() {
        int invalidY = 1;
        int invalidX = 9;

        assertFalse(helper.inGrid(invalidY, invalidX));
    }

    @Test
    public void inGridReturnsFalseforInvalid3() {
        int invalidY = 9;
        int invalidX = 1;

        assertFalse(helper.inGrid(invalidY, invalidX));
    }

    @Test
    public void inGridReturnsFalseforInvalid4() {
        int invalidY = 9;
        int invalidX = 9;

        assertFalse(helper.inGrid(invalidY, invalidX));
    }

    @Test
    public void validForFrontierReturnsTrue() {
        int y = 1;
        int x = 1;

        helper.getPaths()[y][x] = false;
        helper.getFrontiers()[y][x] = false;

        assertTrue(helper.validForFrontier(y, x));
    }

    @Test
    public void validForFrontierReturnsFalse1() {
        int y = 1;
        int x = 1;

        helper.getPaths()[y][x] = true;
        helper.getFrontiers()[y][x] = false;

        assertFalse(helper.validForFrontier(y, x));
    }

    @Test
    public void validForFrontierReturnsFalse2() {
        int y = 1;
        int x = 1;

        helper.getPaths()[y][x] = false;
        helper.getFrontiers()[y][x] = true;

        assertFalse(helper.validForFrontier(y, x));
    }

    @Test
    public void validForFrontierReturnsFalse3() {
        int y = 0;
        int x = 1;

        helper.getPaths()[y][x] = false;
        helper.getFrontiers()[y][x] = false;

        assertFalse(helper.validForFrontier(y, x));
    }

    @Test
    public void addFrontierCells1() {
        int y = 1;
        int x = 1;

        helper.addFrontierCells(y, x);

        assertEquals(2, helper.frontierlistSize());
    }

    @Test
    public void addFrontierCells2() {
        int y = 3;
        int x = 3;

        helper.addFrontierCells(y, x);

        assertEquals(4, helper.frontierlistSize());
    }

    @Test
    public void addFrontierCells3() {
        int y = 1;
        int x = 3;

        helper.addFrontierCells(y, x);

        assertEquals(3, helper.frontierlistSize());
    }

    @Test
    public void addFrontierCells4() {
        int y = 8;
        int x = 8;

        helper.addFrontierCells(y, x);

        assertEquals(2, helper.frontierlistSize());
    }

    @Test
    public void initMaze1() {
        int y = 1;
        int x = 1;

        helper.initMaze(y, x);

        assertEquals(2, helper.frontierlistSize());
    }

    @Test
    public void initMaze2() {
        int y = 1;
        int x = 1;

        helper.initMaze(y, x);

        assertTrue(helper.getPaths()[y][x]);
    }

    @Test
    public void connectRight() {
        int startY = 1;
        int startX = 1;

        int endY = 1;
        int endX = 3;

        helper.connect(startY, startX, endY, endX);

        boolean startPath = helper.getPaths()[startY][startX];
        boolean endPath = helper.getPaths()[endY][endX];
        boolean middle = helper.getPaths()[1][2];
        boolean result = startPath && middle && endPath;

        assertTrue(result);
    }

    @Test
    public void connectDown() {
        int startY = 1;
        int startX = 1;

        int endY = 3;
        int endX = 1;

        helper.connect(startY, startX, endY, endX);

        boolean startPath = helper.getPaths()[startY][startX];
        boolean endPath = helper.getPaths()[endY][endX];
        boolean middle = helper.getPaths()[2][1];
        boolean result = startPath && middle && endPath;

        assertTrue(result);
    }

    @Test
    public void connectUp() {
        int startY = 5;
        int startX = 5;

        int endY = 3;
        int endX = 5;

        helper.connect(startY, startX, endY, endX);

        boolean startPath = helper.getPaths()[startY][startX];
        boolean endPath = helper.getPaths()[endY][endX];
        boolean middle = helper.getPaths()[4][5];
        boolean result = startPath && middle && endPath;

        assertTrue(result);
    }

    @Test
    public void connectLeft() {
        int startY = 5;
        int startX = 5;

        int endY = 5;
        int endX = 3;

        helper.connect(startY, startX, endY, endX);

        boolean startPath = helper.getPaths()[startY][startX];
        boolean endPath = helper.getPaths()[endY][endX];
        boolean middle = helper.getPaths()[5][4];
        boolean result = startPath && middle && endPath;

        assertTrue(result);
    }
    
    @Test
    public void getRandomNeighbourWithNoValidNeighboursReturnsNull() {
        assertNull(helper.getRandomNeighbour(1, 1));
    }
    
    @Test
    public void getRandomNeighbourReturnsSomething() {
        helper.getPaths()[1][3] = true;
        
        assertNotNull(helper.getRandomNeighbour(1, 1));
    }
    
    @Test
    public void getRandomNeighbourWithOneValidReturnsIt() {
        helper.getPaths()[1][3] = true;
        Cell valid = new Cell(1, 3);
        
        assertEquals(valid, helper.getRandomNeighbour(1, 1));
    }
    
    /*
    @Test
    public void getRandomNeighbourWithTwoValidCells() {
        helper.getPaths()[1][3] = true;
        helper.getPaths()[3][1] = true;
        helper.getPaths()[2][2] = true;
        
        Cell first = new Cell(1, 3);
        Cell second = new Cell(3, 1);
        
        Cell random = helper.getRandomNeighbour(1, 1);
        
        
        
        assertTrue(random.equals(first) || random.equals(second));
    }
    */

}
