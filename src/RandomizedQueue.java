import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by mogrein on 30.07.14.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private static byte defaultQLength = 2;
    private Item[] qArray;
    private int currentLength;
    private int head = 0;
    private int tail = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        qArray = (Item[]) new Object[defaultQLength];
        currentLength = 0;
    }

    private void resize(int capacity) {
        Item[] newArray = (Item[]) new Object[capacity];
        for (int i = 0, j = head;
             i < currentLength;
             ++i, j = (j + 1) % qArray.length) {
            newArray[i] = qArray[j];
        }
        qArray = newArray;
        head = 0;
        tail = currentLength;
    }

    private int getRandomIndex() {
        return (head + StdRandom.uniform(currentLength)) % qArray.length;
    }
    /** Swaps element with random element in the array
     *  Uses currentLength and head class fields
    */
    private void randomSwap(int elementNumber) {
        int randomIndex = getRandomIndex();
        Item stub = qArray[elementNumber];
        qArray[elementNumber] = qArray[randomIndex];
        qArray[randomIndex] = stub;
    }

    // is the queue empty?
    public boolean isEmpty() {
        return (currentLength == 0);
    }

    // return the number of items on the queue
    public int size() {
        return currentLength;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        if (currentLength == qArray.length) resize(2 * qArray.length);
        qArray[tail] = item;
        ++currentLength;
        randomSwap(tail);
        tail = (tail + 1) % qArray.length;
    }

    // delete and return a random item
    public Item dequeue() {
        if (this.isEmpty()) throw new NoSuchElementException();
        if (tail == 0) tail = qArray.length - 1;
        else           --tail;
        //randomSwap(tail);
        Item result = qArray[tail];
        qArray[tail] = null;
        --currentLength;
        if (currentLength <= (qArray.length / 4) && qArray.length >= 4) {
            resize(qArray.length / 4);
        }
        return result;
    }

    // return (but do not delete) a random item
    public Item sample() {
        if (this.isEmpty()) throw new NoSuchElementException();
        return qArray[getRandomIndex()];
    }

    // return an independent iterator over items in random order
    @Override
    public java.util.Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private int[] indicesArray;
            private int currentIndex = 0;
            {
                indicesArray = new int[currentLength];
                for (int i = 0; i < indicesArray.length; ++i)
                    indicesArray[i] = i;
                StdRandom.shuffle(indicesArray);
            }
            @Override
            public boolean hasNext() { return currentIndex != indicesArray.length; }
            @Override
            public Item next() {
                if (!hasNext()) throw new NoSuchElementException();
                return qArray[indicesArray[currentIndex++]];
            }
            @Override
            public void remove() { throw new UnsupportedOperationException(); }
        };
    }

    // unit testing
    public static void main(String[] args) {
        RandomizedQueue<Double> queue = new RandomizedQueue<Double>();
        queue.enqueue(5.);
        queue.enqueue(6.);
        queue.enqueue(7.);
        queue.enqueue(8.);
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        for (Iterator it = queue.iterator(); it.hasNext();) {
            System.out.println(" " + it.next());
        }
    }
}
