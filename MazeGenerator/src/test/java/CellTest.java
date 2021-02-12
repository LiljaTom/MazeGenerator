
import mazegenerator.domain.Cell;
import org.junit.Before;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lilja
 */
public class CellTest {

    Cell cell;

    @Before
    public void setUp() {
        int y = 5;
        int x = 2;

        cell = new Cell(5, 2);
    }

    @Test
    public void constructorWorks1() {
        assertEquals(2, cell.getX());
    }

    @Test
    public void constructorWorks2() {
        assertEquals(5, cell.getY());
    }

    @Test
    public void getXWorks() {
        assertEquals(2, cell.getX());
    }

    @Test
    public void getYWorks() {
        assertEquals(5, cell.getY());
    }

    @Test
    public void equalsReturnsTrueForEquals() {
        Cell equal = new Cell(5, 2);

        assertTrue(cell.equals(equal));
    }

    @Test
    public void equalsReturnsFalseForNotEquals() {
        Cell notEqual = new Cell(1, 2);
        
        assertFalse(cell.equals(notEqual));
    }

}
