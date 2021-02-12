package mazegenerator.domain;

/**
 *
 * @author lilja
 */
public class MazeArray<T> {

    private T[] values;
    private int count;

    public MazeArray() {
        this.values = (T[]) new Object[5];
        int count = 0;
    }

    public void add(T value) {

        if (count == values.length) {
            increase();
        }
        
        values[count] = value;
        count++;
    }

    public void increase() {
        int newLenght = values.length * 2;
        T[] newArr = (T[]) new Object[newLenght];

        for (int i = 0; i < values.length; i++) {
            newArr[i] = values[i];
        }

        this.values = newArr;
    }

    public int size() {
        return count;
    }


}
