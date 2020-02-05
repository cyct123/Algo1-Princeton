import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class Interval2D
{
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        double min = Double.parseDouble(args[1]);
        double max = Double.parseDouble(args[2]);
        Interval2D[] intervals = new Interval2D[N];
        for (int i = 0; i < N; i++) {
            Interval1D_1 = get_interval1D(min, max);
            Interval1D_2 = get_interval1D(min, max);
            intervals[i] = new Interval2D(Interval1D_1, Interval1D_2);
            intervals[i].draw();
        }
        int intersect_count = 0;
        int equal_count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                interval1 = intervals[i];
                interval2 = intervals[j];
                if (interval1.intersects(interval2))
                    intersect_count++;
                if (interval1.equals(interval2))
                    equal_count++;
            }
        }
        StdOut.printf("intersect: %d, equal: %d\n", intersect_count, equal_count);
    }

    public static Interval1D get_interval1D(double range_min, double range_max) {
        double random1 = StdRandom.uniform(range_min, range_max);
        double random2 = StdRandom.uniform(range_min, range_max);
        double min = (random1 < random2) ? random1:random2;
        double max = (random1 > random2) ? random1:random2;
        return Interval1D(min, max);
    }
}