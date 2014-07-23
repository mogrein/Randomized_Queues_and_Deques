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
    }

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
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
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        first.prev = null;
        oldfirst.prev = first;

    }

    // insert the item at the end
    public void addLast(Item item) {

    }

    // delete and return the item at the front
    public Item removeFirst() {

    }

    // delete and return the item at the end
    public Item removeLast() {}

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {}

    // unit testing
    public static void main(String[] args) {}
}