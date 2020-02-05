import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.Interval2D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

public class testInterval2D
{
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        double min = Double.parseDouble(args[1]);
        double max = Double.parseDouble(args[2]);
        Interval2D[] intervals = new Interval2D[N];
        for (int i = 0; i < N; i++) {
            Interval1D i1 = get_interval1D(min, max);
            Interval1D i2 = get_interval1D(min, max);
            intervals[i] = new Interval2D(i1, i2);
            intervals[i].draw();
        }
        int intersect_count = 0;
        int contain_count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                Interval2D interval1 = intervals[i];
                Interval2D interval2 = intervals[j];
                if (interval1.intersects(interval2))
                    intersect_count++;
            }
        }
        //TODO: contain
        StdOut.printf("intersect: %d, contain: %d\n", intersect_count, contain_count);
    }

    public static Interval1D get_interval1D(double range_min, double range_max) {
        double random1 = StdRandom.uniform(range_min, range_max);
        double random2 = StdRandom.uniform(range_min, range_max);
        double min = (random1 < random2) ? random1:random2;
        double max = (random1 > random2) ? random1:random2;
        return new Interval1D(min, max);
        //return interval;
    }
}