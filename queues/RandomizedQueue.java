import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;


public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;
    private int N;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
        N = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return N;
    }

    private void checkItem(Item item) {
        if (item == null)
            throw new IllegalArgumentException("item must not be null");
    }

    private void resize(int length) {
        Item[] newQueue = (Item[]) new Object[length];
        for (int i = 0; i < N; i++) {
            newQueue[i] = queue[i];
        }
        queue = newQueue;
    }

    // add the item
    public void enqueue(Item item) {
        checkItem(item);
        if (N == queue.length)
            resize(2 * queue.length);
        queue[N++] = item;
    }

    private void checkEmpty() {
        if (isEmpty())
            throw new NoSuchElementException("Queue is empty!");
    }

    // remove and return a random item
    public Item dequeue() {
        checkEmpty();
        int randomIdx = StdRandom.uniform(N);
        Item item = queue[randomIdx];
        queue[randomIdx] = queue[N-1];
        queue[--N] = null;
        if (N == queue.length / 4)
            resize(queue.length / 2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        int idx = StdRandom.uniform(N);
        return queue[idx];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }

    private class RandomizedIterator implements Iterator<Item> {

        private int i;
        private int[] indexs;

        RandomizedIterator() {
            i = N;
            indexs = new int[N];
            for (int i = 0; i < N; i++)
                indexs[i] = i;
        }

        public boolean hasNext() {
            return i > 0;
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("Iterator is empty");
            int randomIdx = StdRandom.uniform(i);
            int idx = indexs[randomIdx];
            indexs[randomIdx] = indexs[--i];
            return queue[idx];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<>();
        q.enqueue(1);
        StdOut.println(q.sample());
        q.enqueue(2);
        q.enqueue(3);
        for (int i: q)
            StdOut.printf(i + " ");
        StdOut.println();
    }

}