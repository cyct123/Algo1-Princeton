import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


public class testPoint2D
{
    public static void main (String[] args) {
        int N = Integer.parseInt(args[0]);
        double half_length = 0.5;
        Point2D[] points = new Point2D[N];
        for (int i = 0; i < N; i++)
        {
            double x = StdRandom.uniform(half_length - half_length, half_length + half_length);
            double y = StdRandom.uniform(half_length - half_length, half_length + half_length);
            points[i] = new Point2D(x, y);
        }
        double smallest_dist = 2.0;
        for (int i = 0; i < N; i++)
        {
            for (int j = i + 1; j < N; j++)
            {
                Point2D p1 = points[i];
                Point2D p2 = points[j];
                double dist = p1.distanceTo(p2);
                if (dist < smallest_dist)
                    smallest_dist = dist;
            }
        }
        StdOut.println(smallest_dist);
    }
}