package mazegenerator.domain;

/**
 *
 * @author lilja
 */
public class MazeArray<T> {
    
    private T[] objects;
    private int count;
    
    public MazeArray() {
        this.objects = (T[]) new Object[5];
        int count = 0;
    }
    
    public void add(T obj) {
        
        if (count == objects.length) {
            increase();
        }
        
        objects[count] = obj;
        count++;
    }
    
    public T get(int index) {
        
        if (index >= count || index < 0) {
            return null;
        }
        
        return objects[index];
    }
    
    public boolean isEmpty() {
        return count == 0;
    }
    
    public void remove(int index) {
        moveLeft(index);
        count--;        
    }
    
    public void removeObject(T obj) {
        for(int i = 0; i < objects.length; i++) {
            if(obj.equals(objects[i])) {
                moveLeft(i);
                count--;
                break;
            }
        }
    }
    
    private void moveLeft(int index) {
        for(int i = index; i < objects.length - 1; i++) {
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
    
    public int size() {
        return count;
    }
    
    public T getRandom() {
        int index = (int) System.nanoTime() % count;
        
        return objects[index];
    }
    
    public boolean contains(T obj) {
        for (int i = 0; i < objects.length; i++) {
            if (obj.equals(objects[i])) {
                return true;
            }
        }
        return false;
    }
    
}
