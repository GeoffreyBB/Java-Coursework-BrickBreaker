package collisionDetection;

/**
 * The Counter class keeps track of various quantifiable values.
 */
public class Counter {
    private int count = 0;

    /**
     * This method adds a number to the counter.
     * @param number the number to be added.
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * This method subtracts a number from the counter.
     * @param number the number to be subtracted.
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * This method returns the value of the counter.
     * @return the value of the counter.
     */
    public int getValue() {
        return count;
    }
}
