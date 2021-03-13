package mazegenerator.domain;

/**
 *  Custom array class
 *  
 * @author lilja
 */
public class MazeArray<T> {

    private T[] objects;
    private int count;

    public MazeArray() {
        this.objects = (T[]) new Object[5];
        this.count = 0;
    }

    /**
     * Adds object to the list
     * 
     * @param obj Object to add
     */
    public void add(T obj) {

        if (count == objects.length) {
            increase();
        }

        objects[count] = obj;
        count++;
    }

    /**
     * Returns the object 
     * 
     * @param index object's index
     * 
     * @return object in the index
     */
    public T get(int index) {

        if (index >= count || index < 0) {
            return null;
        }

        return objects[index];
    }

    /**
     * Returns and removes array's newest object
     * 
     * @return array's latest object
     */
    public T pop() {
        if (count == 0) {
            return null;
        }

        T obj = objects[count - 1];
        remove(count - 1);

        return obj;
    }

    /**
     * Return array's newest object, without removing it
     * 
     * @return array's latest object
     */
    public T peek() {
        if (count == 0) {
            return null;
        }

        T obj = objects[count - 1];

        return obj;
    }

    /**
     * Checks if the list is empty
     * @return true when empty, otherwise false
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Removes object in the index
     * 
     * @param index object's index
     */
    public void remove(int index) {
        if (index >= count || index < 0) {
            return;
        }
        moveLeft(index);
        count--;
    }

    /**
     * Removes object from the array
     * 
     * @param obj object to remove
     */
    public void removeObject(T obj) {
        int index = objectsIndex(obj);

        remove(index);
    }

    /**
     * Returns object's index
     * 
     * @param obj object
     * @return objects index, -1 if not found
     */
    public int objectsIndex(T obj) {
        for (int i = 0; i < count; i++) {
            if (objects[i].equals(obj)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Helper function for remove 
     * @param index
     */
    public void moveLeft(int index) {
        for (int i = index; i < objects.length - 1; i++) {
            objects[i] = objects[i + 1];
        }
    }

    private void increase() {
        int newLenght = objects.length * 2;
        T[] newArr = (T[]) new Object[newLenght];

        for (int i = 0; i < objects.length; i++) {
            newArr[i] = objects[i];
        }

        this.objects = newArr;
    }

    /**
     * Returns array's size
     * 
     * @return size as int
     */
    public int size() {
        return count;
    }

    /**
     * Returns random object from the list
     * 
     * @return random object
     */
    public T getRandom() {

        if (count == 0) {
            return null;
        }
        long index = System.nanoTime() % count;
        int i = (int) index;

        return objects[i];
    }

    /**
     * Check if the array contains given object
     * @param obj object to check
     * @return true if contains
     */
    public boolean contains(T obj) {
        for (int i = 0; i < count; i++) {
            if (obj.equals(objects[i])) {
                return true;
            }
        }
        return false;
    }

    public int arrayLength() {
        return objects.length;
    }

}
