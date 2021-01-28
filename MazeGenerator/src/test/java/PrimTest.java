
import mazegenerator.domain.Prim;
import org.junit.Before;

import org.junit.Test;
import static org.junit.Assert.*;

public class PrimTest {

    Prim prim;
    int height;
    int width;

    @Before
    public void setUp() {
        this.height = 10;
        this.width = 10;

        prim = new Prim(height, width);
    }

    @Test
    public void inGridReturnsTrueForValid() {
        int validY = 1;
        int validX = 1;

        assertTrue(prim.inGrid(validY, validX));
    }

    @Test
    public void inGridReturnsFalseForInvalid() {
        int invalidY = 0;
        int invalidX = 0;

        assertFalse(prim.inGrid(invalidY, invalidX));
    }

    @Test
    public void initMazeSetsCorrectPathCount() {
        int validY = 1;
        int validX = 1;

        prim.initMaze(validY, validX);

        assertEquals(1, prim.getPathCount());
    }

    @Test
    public void initMazeSetsCorrectPath() {
        int validY = 1;
        int validX = 1;

        prim.initMaze(validY, validX);

        assertTrue(prim.getPaths()[1][1]);
    }

    
    @Test
    public void initMazeSetsCorrectFrontierCount1() {
        int validY = 1;
        int validX = 1;
        int count = 0;

        prim.initMaze(validY, validX);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (prim.getFrontiers()[y][x]) {
                    count++;
                }
            }
        }

        assertEquals(2, count);
    }
    

}
