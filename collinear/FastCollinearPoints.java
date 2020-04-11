import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;


public class FastCollinearPoints {

    private final LineSegment[] validSegments;

    public FastCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {
        checkNull(points);
        Point[] sortedPoints = points.clone();
        Arrays.sort(sortedPoints);
        checkDuplicate(sortedPoints);
        final int pointsLength = sortedPoints.length;
        final List<LineSegment> lineSegments = new LinkedList<>();
        for (int i = 0; i < pointsLength; i++) {
            Point p0 = sortedPoints[i];
            Point[] pointsBySlope = sortedPoints.clone();
            Arrays.sort(pointsBySlope, p0.slopeOrder());
            int j = 1;
            while (j < pointsLength) {
                LinkedList<Point> segmentPoints = new LinkedList<>();
                final double compareSlope = p0.slopeTo(pointsBySlope[j]);
                do { segmentPoints.add(pointsBySlope[j++]);
                } while (j < pointsLength && Double.compare(compareSlope, p0.slopeTo(pointsBySlope[j])) == 0);
                if (segmentPoints.size() >= 3 && p0.compareTo(segmentPoints.peek()) < 0) {
                    Point smallest = p0;
                    Point biggest = segmentPoints.removeLast();
                    lineSegments.add(new LineSegment(smallest, biggest));
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}