import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class VisualCounter {

    private final int N;
    private final int max;
    private int count;
    private int ops;

    public VisualCounter(int N, int max) {
        this.N = N;
        this.max = max;
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(-1 * max, max);
        StdDraw.setPenRadius(.005);
        StdDraw.setPenColor(StdDraw.RED);
    }

    public void increment() {
        ops++;
        count++;
        StdDraw.point(ops, count);
    }

    public void reduction() {
        ops++;
        count--;
        StdDraw.point(ops, count);
    }

    public int tally() {
        return count;
    }

    public String toString() {
        return "" + count;
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int max = Integer.parseInt(args[1]);
        int T = Integer.parseInt(args[2]);
        VisualCounter vc = new VisualCounter(N, max);
        for (int i = 0; i < T; i++) {
            if (StdRandom.bernoulli())
                vc.increment();
            else
                vc.reduction();
        }
    }
}