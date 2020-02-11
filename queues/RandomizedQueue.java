import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;
    private int n;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    private void checkItem(Item item) {
        if (item == null)
            throw new IllegalArgumentException("item must not be null");
    }

    private void resize(int length) {
        Item[] newQueue = (Item[]) new Object[length];
        for (int i = 0; i < n; i++) {
            newQueue[i] = queue[i];
        }
        queue = newQueue;
    }

    // add the item
    public void enqueue(Item item) {
        checkItem(item);
        if (n == queue.length)
            resize(2 * queue.length);
        queue[n++] = item;
    }

    private void checkEmpty() {
        if (isEmpty())
            throw new NoSuchElementException("Queue is empty!");
    }

    // remove and return a random item
    public Item dequeue() {
        checkEmpty();
        int randomIdx = StdRandom.uniform(n);
        Item item = queue[randomIdx];
        queue[randomIdx] = queue[n-1];
        queue[--n] = null;
        if ((n > 0) && (n == queue.length / 4))
            resize(queue.length / 2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        checkEmpty();
        int idx = StdRandom.uniform(n);
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
            i = n;
            indexs = new int[n];
            for (int j = 0; j < n; j++)
                indexs[j] = j;
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
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        rq.sample();
    }

}