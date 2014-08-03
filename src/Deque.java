import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by mogrein on 22.07.14.
 */
public class Deque<Item> implements Iterable<Item> {
        // construct an empty deque
    private int length = 0;
    private Node first;
    private Node last;
    public Deque() {
        length = 0;
        first = null;
        last = null;
    }

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
        Node(Item item, Node next, Node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    // is the deque empty?
    public boolean isEmpty() {
        return (length == 0);
    }

    // return the number of items on the deque
    public int size() {
        return length;
    }

    // insert the item at the front
    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException();
        Node oldfirst = first;
        first = new Node(item, oldfirst, null);
        if (isEmpty()) {
            last = first;
        } else {
            oldfirst.prev = first;
        }
        ++length;
    }

    // insert the item at the end
    public void addLast(Item item) {
        if (item == null) throw new NullPointerException();
        Node oldlast = last;
        last = new Node(item, null, oldlast);
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
        }
        ++length;
    }

    // delete and return the item at the front
    public Item removeFirst() {
        if (this.isEmpty()) throw new NoSuchElementException();
        Node oldfirst = first;
        first = oldfirst.next;
        if (first != null) {
            first.prev = null;
            oldfirst.next = null;
        } else {
            last = null;
        }
        --length;
        return oldfirst.item;
    }

    // delete and return the item at the end
    public Item removeLast() {
        if (this.isEmpty()) throw new NoSuchElementException();
        Node oldlast = last;
        last = oldlast.prev;
        if (last != null) {
            last.next = null;
            oldlast.prev = null;
        } else {
            first = null;
        }
        --length;
        return oldlast.item;
    }

    // return an iterator over items in order from front to end
    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node current = first;

            @Override
            public boolean hasNext() { return current != null; }

            @Override
            public void remove() { throw new UnsupportedOperationException(); }

            @Override
            public Item next() {
                if (!hasNext()) throw new NoSuchElementException();
                Item item = current.item;
                current = current.next;
                return item;
            }
        };
    }

    // unit testing
    public static void main(String[] args) {
        Deque<Double> deq = new Deque<Double>();
        for (int i = 0; i < 1000; ++i) {
            int choice = StdRandom.uniform(4);
            deq.addFirst(5.);
            if (choice == 0) deq.addFirst(5.);
            else if (choice == 1) deq.addLast(5.);
            else if (choice == 2) deq.removeFirst();
            else deq.removeLast();
        }
        deq.addFirst(5.);
        deq.removeLast();
        deq.addFirst(6.);
        deq.removeFirst();
        deq.addFirst(7.);
        deq.addFirst(8.);
        deq.addFirst(9.);
        deq.removeLast();
        deq.removeLast();
        deq.removeLast();
        deq.removeLast();
        deq.removeLast();
        for (Iterator<Double> iterator = deq.iterator(); iterator.hasNext();) {
            System.out.println(iterator.next());
        }
    }
}
