
package mazegenerator.domain;

/**
 *
 * @author lilja
 */
public class Timer {
    
    private long startTime;
    private long endTime;
    
    public void startTimer() {
        this.startTime = System.nanoTime();
    }
    
    public void endTimer() {
        this.endTime = System.nanoTime();
    }
    
    public double getTime() {
        double time = (endTime - startTime)/ 1e9;
        
        return time;
    }
    
    
}
