
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
    public void buildMazeDoesOneRound() {
        prim.buildMaze();
        
        
        assertEquals(3, prim.getFrontiersList().size());
    }

}
