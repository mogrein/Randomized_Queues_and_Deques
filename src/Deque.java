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
        Node oldfirst = first;
        first = new Node(item, oldfirst, null);
        if isEmpty() {
			last = first;
		} else {
			oldfirst.prev = first;
		}
		++length;
    }

    // insert the item at the end
    public void addLast(Item item) {
		Node oldlast = last;
        last = new Node(item, null, oldlast);
        if isEmpty() {
			first = last;
		} else {
			oldfirst.prev = first;
		}
		++length;
    }

    // delete and return the item at the front
    public Item removeFirst() {
		Node oldfirst = first;
		first = oldfirst.next;
		if (first != null) {
			first.prev = null;
			oldfirst.next = null;
		}
		--length;
		return oldfirst.item;
    }

    // delete and return the item at the end
    public Item removeLast() {
		Node oldlast = last;
		last = oldlast.prev;
		if (last != null) {
			last.next = null;
			oldlast.prev = null;
		}
		--length;
		return oldlast.item;
	}

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {}

    // unit testing
    public static void main(String[] args) {
		Deque<Double> deq = new Deque();
		deq.addFirst(5);
		System.out.println(deq.removeFirst);
	}
}
