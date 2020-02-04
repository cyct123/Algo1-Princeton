import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;


public class RandomConnection
{
    public static void main (String args[]) {
        int N = Integer.parseInt(args[0]);
        double p = Double.parseDouble(args[1]);
        double circle_radius = 0.5;
        double pen_radius = 0.05;
        double center_x = 0.5;
        double center_y = 0.5;
        StdDraw.setPenRadius(pen_radius);
        StdDraw.circle(center_x, center_y, circle_radius);
        StdDraw.setPenColor(StdDraw.RED);
        double[][] points = draw_point(N, center_x, center_y, circle_radius);
        draw_line(N, p, points);
    }

    public static void draw_line(int N, double p, double[][] points)
    {
        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.GRAY);
        for (int i = 0; i < N; i++)
        {
            for (int j =0; j < N; j++)
            {
                if (i == j)
                    continue;
                if (StdRandom.bernoulli(p))
                    StdDraw.line(points[i][0], points[i][1], points[j][0], points[j][1]);
            }
        }
    }

    public static double[][] draw_point(int N, double center_x, double center_y, double circle_radius)
    {
        double[][] points = new double[N][2];
        for (int i = 0; i < N; i++)
        {
            double degree = 2 * Math.PI / N * i;
            double x = center_x + Math.cos(degree) * circle_radius;
            double y = center_y + Math.sin(degree) * circle_radius;
            StdDraw.point(x, y);
            points[i][0] = x;
            points[i][1] = y;
        }
        return points;
    }

}