import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Created by mogrein on 30.07.14.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    // construct an empty randomized queue
    public RandomizedQueue() {

    }

    // is the queue empty?
    public boolean isEmpty() {

    }

    // return the number of items on the queue
    public int size() {

    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();

    }

    // delete and return a random item
    public Item dequeue() {
        if (this.isEmpty()) throw new NoSuchElementException();

    }

    // return (but do not delete) a random item
    public Item sample() {
        if (this.isEmpty()) throw new NoSuchElementException();

    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {

    }

    // unit testing
    public static void main(String[] args) {

    }
}
