
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
    
    @Test
    public void getRandomReturnsRandom() {
        array.add(new Cell(1, 1));
        
        assertNotNull(array.getRandom());
    }
    
    @Test
    public void isEmptyReturnsTrueWhenEmpty() {
        assertTrue(array.isEmpty());
    }
    
    @Test
    public void isEmptyReturnsFalseWhenNotEmpty() {
        array.add(new Cell(1, 2));
        
        assertFalse(array.isEmpty());
    }
    
    @Test
    public void containsReturnsTrueIfContainsObject() {
        array.add(new Cell(1, 2));
        array.add(new Cell(2, 1));
        
        assertTrue(array.contains(new Cell(1, 2)));
    }
    
    @Test
    public void containsReturnsFalseIfNotContainsObject() {
        array.add(new Cell(1, 2));
        array.add(new Cell(2, 1));
        
        assertFalse(array.contains(new Cell(3, 2)));
    }
    
    @Test
    public void getReturnsNullIfInvalidIndex() {
        assertNull(array.get(1));
    }
    
    @Test
    public void getReturnsCorrectObject() {
        Cell cell = new Cell(2, 1);
        array.add(new Cell(5, 5));
        array.add(cell);
        array.add(new Cell(1, 2));
        
        assertEquals(cell, array.get(1));
    }
    
    @Test
    public void getReturnsNullIfIndexUnderZero() {
        assertNull(array.get(-1));
    }
    
    @Test
    public void removeReducesSize() {
        array.add(new Cell(1, 2));
        array.add(new Cell(2, 1));
        
        array.remove(1);
        
        assertEquals(1, array.size());
    }
    
    @Test
    public void removeRemovesCorrectObject() {
        array.add(new Cell(1, 2));
        array.add(new Cell(2, 1));
        
        array.remove(1);
        
        assertFalse(array.contains(new Cell(2, 1)));
    }
    
}
