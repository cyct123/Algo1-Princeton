import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;


public class Deque<Item> implements Iterable<Item> {

    private int n;
    private Node first;
    private Node last;

    private class Node {
        Item item;
        Node prev;
        Node next;
    }

    // construct an empty deque
    public Deque() {
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    private void validate(Item item) {
        if (item == null)
            throw new IllegalArgumentException("item must not be null");
    }

    // add the item to the front
    public void addFirst(Item item) {
        validate(item);
        Node prevFirst = first;
        first = new Node();
        first.item = item;
        first.next = prevFirst;
        if (prevFirst != null) prevFirst.prev = first;
        else last = first;
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        validate(item);
        Node prevLast = last;
        last = new Node();
        last.item = item;
        last.prev = prevLast;
        if (prevLast != null) prevLast.next = last;
        else first = last;
        n++;
    }

    private void checkEmpty() {
        if (isEmpty())
            throw new NoSuchElementException("Deque is empty");
    }

    // remove and return the item from the front
    public Item removeFirst() {
        checkEmpty();
        Node deleted = first;
        first = first.next;
        if (first == null) last = first;
        else first.prev = null;
        n--;
        Item item = deleted.item;
        deleted.next = null;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        checkEmpty();
        Node deleted = last;
        last = last.prev;
        if (last == null) first = last;
        else last.next = null;
        n--;
        Item item = deleted.item;
        deleted.prev = null;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new NodeIterator();
    }

    private class NodeIterator implements Iterator<Item> {

        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
           throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("Iterator is empty");
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> d = new Deque<Integer>();
        d.addFirst(1);
        StdOut.println("" + d.iterator().hasNext()+ " " + d.iterator().next()+ " " + d.size() + " " + d.isEmpty());
        for (int i: d) {
            StdOut.printf(i + " ");
        }
        StdOut.println();
        d.addFirst(2);
        StdOut.println("" + d.iterator().hasNext()+ " " + d.iterator().next()+ " " + d.size() + " " + d.isEmpty());
        for (int i: d) {
            StdOut.printf(i + " ");
        }
        StdOut.println();
        d.addLast(3);
        StdOut.println("" + d.iterator().hasNext()+ " " + d.iterator().next()+ " " + d.size() + " " + d.isEmpty());
        for (int i: d) {
            StdOut.printf(i + " ");
        }
        StdOut.println();
        d.removeFirst();
        StdOut.println("" + d.iterator().hasNext()+ " " + d.iterator().next()+ " " + d.size() + " " + d.isEmpty());
        for (int i: d) {
            StdOut.printf(i + " ");
        }
        StdOut.println();
        d.addLast(2);
        StdOut.println("" + d.iterator().hasNext()+ " " + d.iterator().next()+ " " + d.size() + " " + d.isEmpty());
        for (int i: d) {
            StdOut.printf(i + " ");
        }
        StdOut.println();
        d.removeFirst();
        StdOut.println("" + d.iterator().hasNext()+ " " + d.iterator().next()+ " " + d.size() + " " + d.isEmpty());
        for (int i: d) {
            StdOut.printf(i + " ");
        }
        StdOut.println();
        d.removeLast();
        StdOut.println("" + d.iterator().hasNext()+ " " + d.iterator().next()+ " " + d.size() + " " + d.isEmpty());
        for (int i: d) {
            StdOut.printf(i + " ");
        }
        StdOut.println();
        d.removeFirst();
        StdOut.println("" + d.size() + " " + d.isEmpty());
        for (int i: d) {
            StdOut.printf(i + " ");
        }
        StdOut.println();
        d.addLast(4);
        StdOut.println("" + d.iterator().hasNext()+ " " + d.iterator().next()+ " " + d.size() + " " + d.isEmpty());
        for (int i: d) {
            StdOut.printf(i + " ");
        }
        StdOut.println();
        d.addFirst(5);
        StdOut.println("" + d.iterator().hasNext()+ " " + d.iterator().next()+ " " + d.size() + " " + d.isEmpty());
        for (int i: d) {
            StdOut.printf(i + " ");
        }
        StdOut.println();
        d.removeLast();
        d.removeFirst();
        d.removeLast();
        d.iterator().remove();
    }
}