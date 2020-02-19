import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;


public class BruteCollinearPoints {

    private LineSegment[] validSegments;
    private Point[] points;
    private int N;

    public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {
        if (points == null)
            throw new IllegalArgumentException("Argument points is null");
        this.points = sort(points);
        N = 0;
        int pointsLength = this.points.length;
        for (int i = 0; i < pointsLength - 3; i++) {
            for (int j = i; j < pointsLength - 2; j++) {
                for (int m = j; m < pointsLength - 1; m++) {
                    for (int n = m; n < pointsLength; n++) {
                        Point p0 = this.points[i];
                        Point p1 = this.points[j];
                        Point p2 = this.points[m];
                        Point p3 = this.points[n];
                        double slope1 = p0.slopeTo(p1);
                        double slope2 = p0.slopeTo(p2);
                        double slope3 = p0.slopeTo(p3);
                        if ((slope1 == slope2) && (slope1 == slope3)){
                            N++;
                        }
                    }
                }
            }
        }
    }

    private void sort(Points[] points) {
        StdRandom.shuffle(points);
        sort(points, 0, points - 1);
    }

    private void(Points[ points, int lo, int hi]) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    public int numberOfSegments()        // the number of line segments
    {
        return N;
    }

    private Point findPoint(Point p, Point q, Point r, Point s, int condition) {
        Point p0 = p;
        if (p0.compareTo(q) != condition)
            p0 = q;
        if (p0.compareTo(r) != condition)
            p0 = r;
        if (p0.compareTo(s) != condition)
            p0 = s;
        return p0;
    }

    public LineSegment[] segments()                // the line segments
    {
        validSegments = new LineSegment[N];
        int idx = 0;
        int pointsLength = points.length;
        for (int i = 0; i < pointsLength - 3; i++) {
            for (int j = i + 1; j < pointsLength - 2; j++) {
                for (int m = j + 1; m < pointsLength - 1; m++) {
                    for (int n = m + 1; n < pointsLength; n++) {
                        Point p0 = points[i];
                        Point p1 = points[j];
                        Point p2 = points[m];
                        Point p3 = points[n];
                        double slope1 = p0.slopeTo(p1);
                        double slope2 = p0.slopeTo(p2);
                        double slope3 = p0.slopeTo(p3);
                        if ((slope1 == slope2) && (slope1 == slope3)){
                            Point smallest = findPoint(p0, p1, p2, p3, -1);
                            Point biggest = findPoint(p0, p1, p2, p3, 1);
                            validSegments[idx++] = new LineSegment(smallest, biggest);
                        }
                    }
                }
            }
        }
        return validSegments;
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}