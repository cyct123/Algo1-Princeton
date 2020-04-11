import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

public class BruteCollinearPoints {

    private final LineSegment[] validSegments;

    public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {
        checkNull(points);
        Point[] sortedPoints = points.clone();
        Arrays.sort(sortedPoints);
        checkDuplicate(sortedPoints);
        final int pointsLength = sortedPoints.length;
        final List<LineSegment> lineSegments = new LinkedList<>();
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
                        if ((Double.compare(slope1, slope2) == 0) && (Double.compare(slope1, slope3) == 0)) {
                            Point[] segmentPoints = {p0, p1, p2, p3};
                            Point smallest = findSmallestPoint(segmentPoints);
                            Point biggest = findBiggestPoint(segmentPoints);
                            LineSegment newLine = new LineSegment(smallest, biggest);
                            lineSegments.add(newLine);
                        }
                    }
                }
            }
        }
        validSegments = lineSegments.toArray(new LineSegment[0]);
    }

    private void checkNull(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException("Argument points is null");
        for (Point p : points) {
            if (p == null)
                throw new IllegalArgumentException("Points has null point");
        }
    }

    private void checkDuplicate(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i+1]) == 0)
                throw new IllegalArgumentException(i + "th and the next point is same");
        }
    }

    private Point findSmallestPoint(Point[] points) {
        Point p0 = null;
        for (Point p: points) {
            if (p0 == null)
                p0 = p;
            else {
                if (p0.compareTo(p) < 0)
                    p0 = p;
            }
        }
        return p0;
    }

    private Point findBiggestPoint(Point[] points) {
        Point p0 = null;
        for (Point p: points) {
            if (p0 == null)
                p0 = p;
            else {
                if (p0.compareTo(p) > 0)
                    p0 = p;
            }
        }
        return p0;
    }

    public int numberOfSegments()        // the number of line segments
    {
        return validSegments.length;
    }

    public LineSegment[] segments()                // the line segments
    {
        return validSegments.clone();
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
        StdOut.println(collinear.numberOfSegments());
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}