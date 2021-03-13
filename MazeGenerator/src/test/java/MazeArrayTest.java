
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
    public void constructorSetsCorrectArrayLength() {
        assertEquals(5, array.arrayLength());
    }

    @Test
    public void constructorSetsCountRight() {
        assertEquals(0, array.size());
    }

    @Test
    public void canAddObjects() {
        array.add(new Cell(1, 1));
        array.add(new Cell(2, 1));

        assertEquals(2, array.size());
    }

    @Test
    public void addDoublesSizeIfNeeded() {
        for (int i = 0; i < 6; i++) {
            array.add(new Cell(1, i));
        }

        assertEquals(10, array.arrayLength());
    }

    @Test
    public void sizeReturnsCount() {
        for (int i = 0; i < 6; i++) {
            array.add(new Cell(1, i));
        }
        assertEquals(6, array.size());
    }

    @Test
    public void containsReturnsTrueIfContains() {
        Cell cell = new Cell(1, 1);
        array.add(new Cell(1, 2));
        array.add(cell);

        assertTrue(array.contains(cell));
    }

    @Test
    public void containsReturnsFalseIfNotContains() {
        Cell cell = new Cell(1, 1);

        assertFalse(array.contains(cell));
    }

    @Test
    public void getReturnsNullIfInvalidIndex() {
        array.add(new Cell(1, 1));
        assertNull(array.get(-1));
    }

    @Test
    public void getReturnsNullIfIndexGreaterThanSize() {
        array.add(new Cell(1, 1));
        assertNull(array.get(1));
    }

    @Test
    public void getReturnsCorrect() {
        Cell cell = new Cell(1, 1);
        array.add(new Cell(1, 2));
        array.add(cell);
        array.add(new Cell(1, 3));

        assertEquals(cell, array.get(1));
    }

    @Test
    public void objectsIndexReturnsCorrect() {
        Cell cell = new Cell(1, 1);
        array.add(cell);
        array.add(new Cell(1, 3));

        assertEquals(0, array.objectsIndex(cell));
    }

    @Test
    public void objectsIndexReturnsMinusOneIfNotContains() {
        Cell cell = new Cell(1, 1);
        array.add(new Cell(1, 3));

        assertEquals(-1, array.objectsIndex(cell));
    }

    @Test
    public void isEmptyReturnsTrueWhenListEmpty() {
        assertTrue(array.isEmpty());
    }

    @Test
    public void isEmptyReturnsFalseWhenListNotEmpty() {
        array.add(new Cell(1, 1));

        assertFalse(array.isEmpty());
    }

    @Test
    public void moveLeftsWorks() {
        Cell cell = new Cell(1, 1);
        array.add(new Cell(1, 2));
        array.add(new Cell(2, 1));
        array.add(cell);

        array.moveLeft(1);

        assertEquals(cell, array.get(1));
    }

    @Test
    public void removeDecreasesCount() {
        Cell cell = new Cell(1, 1);
        array.add(new Cell(1, 2));
        array.add(new Cell(2, 1));
        array.add(cell);

        array.remove(1);

        assertEquals(2, array.size());
    }

    @Test
    public void removeRemovesObject() {
        Cell cell = new Cell(1, 1);
        array.add(new Cell(1, 2));
        array.add(new Cell(2, 1));
        array.add(cell);

        array.remove(2);

        assertFalse(array.contains(cell));
    }

    @Test
    public void removeDoesNothingIfInvaldiIndex() {
        Cell cell = new Cell(1, 1);
        array.add(new Cell(1, 2));
        array.add(new Cell(2, 1));
        array.add(cell);

        array.remove(3);

        assertEquals(3, array.size());
    }

    @Test
    public void removeObjectDoesNothingIfNotContains() {
        Cell cell = new Cell(1, 1);
        array.add(new Cell(1, 2));
        array.add(new Cell(2, 1));
        array.removeObject(cell);

        assertEquals(2, array.size());
    }

    @Test
    public void removeObjectRemovesCorrectObject() {
        Cell cell = new Cell(1, 1);
        array.add(new Cell(1, 2));
        array.add(new Cell(2, 1));
        array.add(cell);
        array.removeObject(cell);

        assertEquals(2, array.size());
    }

    @Test
    public void getRandomReturnsNullIfEmptyList() {
        Cell rnd = array.getRandom();
        assertNull(rnd);
    }

    @Test
    public void getRandomReturnsCorrectWithListSizeOne() {
        Cell rnd = new Cell(2, 2);
        array.add(rnd);

        assertEquals(rnd, array.getRandom());
    }

    @Test
    public void getRandomReturnsCorrectWithListSizeTwo() {
        Cell rnd1 = new Cell(2, 2);
        Cell rnd2 = new Cell(1, 2);
        array.add(rnd1);
        array.add(rnd2);

        Cell rnd = array.getRandom();

        boolean answer = rnd.equals(rnd1) || rnd.equals(rnd2);

        assertTrue(answer);
    }

    @Test
    public void popReturnsNewestValue() {
        Cell cell1 = new Cell(1, 1);
        Cell cell2 = new Cell(2, 1);
        Cell cell3 = new Cell(3, 1);

        array.add(cell1);
        array.add(cell2);
        array.add(cell3);

        assertEquals(cell3, array.pop());
    }

    @Test
    public void popReturnsNullIfListEmpty() {
        assertNull(array.pop());
    }

}
