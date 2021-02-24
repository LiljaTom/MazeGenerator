
import mazegenerator.domain.Cell;
import mazegenerator.domain.Prim;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lilja
 */
public class PrimTest {

    Prim prim;

    @Before
    public void setUp() {
        prim = new Prim(10, 10, 1, 1);
    }
    
    @Test
    public void constructorInitializesTheMaze() {
        assertTrue(prim.getPaths()[1][1]);
    }
    
    @Test
    public void constructorInitializesCorrectAmountOfPaths() {
        int count = 0;
        for(int y = 0; y < 10; y++) {
            for(int x = 0; x < 10; x++) {
                if(prim.getPaths()[x][y]) {
                    count++;
                }
            }
        }
        assertEquals(1, count);
    }
    
    @Test
    public void constructorInitializesCorrectAmountOfFrontiers() {
        assertEquals(2, prim.getFrontiersList().size());
    }
    
    @Test
    public void constructorInitializesCorrectFrontiers() {
        Cell frontier1 = new Cell(3, 1);
        Cell frontier2 = new Cell(1, 3);
        
        Cell cell = prim.getFrontiersList().getRandom();
        
        boolean ans = cell.equals(frontier1) || cell.equals(frontier2);
        
        assertTrue(ans);
    }
    
    @Test
    public void buildMazeAddsCorrectAmountFrontierCells() {
        prim.buildMaze();
        
        assertEquals(3, prim.getFrontiersList().size());
    }
    
    @Test
    public void buildMazeAdsCorrectAmountOfPaths() {
        prim.buildMaze();
        int count = 0;
        for(int y = 0; y < 10; y++) {
            for(int x = 0; x < 10; x++) {
                if(prim.getPaths()[x][y]) {
                    count++;
                }
            }
        }
        
        assertEquals(3, count);
    }
    
    @Test
    public void buildMazeDoesNothingIfNoFrontiers() {
        prim.createMaze();
        
        int count = prim.getFrontiersList().size();
        
        prim.buildMaze();
        
        assertEquals(count, prim.getFrontiersList().size());
    }
    
    @Test
    public void createMazeComplitesMaze() {
        prim.createMaze();
        
        assertTrue(prim.getFrontiersList().isEmpty());
    }
    
    @Test
    public void getRndFrontierReturnsCorrect() {
        Cell first = new Cell(1, 3);
        Cell second = new Cell(3, 1);
        
        Cell rnd = prim.getRandomFrontier();
        boolean answer = rnd.equals(first) || rnd.equals(second);
        
        assertTrue(answer);
    }
    
    @Test
    public void makeConnectionConnectsFrontierCellToMaze() {
        prim.makeConnection(1, 3);
        
        assertTrue(prim.getPaths()[1][3] && prim.getPaths()[1][2]);
    }
    
    
    @Test
    public void addFrontiersRemovesParamFromList() {
        Cell frontier = new Cell(1, 1);
        prim.addFrontiers(frontier);
        
        assertTrue(!prim.getFrontiersList().contains(frontier));
    }
    

}
