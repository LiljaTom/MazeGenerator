
import mazegenerator.domain.Cell;
import mazegenerator.domain.MazeArray;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lilja
 */
public class MazeArrayTest {
    
    MazeArray<Cell> array;
    
    @Before
    public void setUp() {
        array = new MazeArray<>();
    }
    
    @Test
    public void sizeReturnsCorrectValue() {
        array.add(new Cell(2, 2));
        array.add(new Cell(3, 4));
        
        assertEquals(2, array.size());
    }
    
    @Test
    public void addWorks() {
        array.add(new Cell(2, 2));
        
        assertEquals(1, array.size());
    }
    
    @Test
    public void listLengthIncreases() {
        for (int i = 0; i < 5; i++) {
            array.add(new Cell(i, 1));
        }
        array.add(new Cell(5, 5));
        
        assertEquals(6, array.size());
    }
    
}
