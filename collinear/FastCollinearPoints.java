import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;


public class FastCollinearPoints {

    private LineSegment[] validSegments;
    private int N;
    private PointSlope[] sortedSlopes;

    private class PointSlope {
        Point point;
        double slope;
    }

    private void checkPointsValid(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException("Argument points is null");
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException(i + "th point is null");
            for (int j = i + 1; j < points.length; j++) {
                if (points[i] == points[j])
                    throw new IllegalArgumentException(i + "th and " + j + "th point is same");
            }
        }
    }

    public FastCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {
        checkPointsValid(points);
        N = 0;
        validSegments = new LineSegment[1];
        int pointsLength = points.length;
        for (int i = 0; i < pointsLength; i++) {
            Point p0 = points[i];
            PointSlope[] slopes = new PointSlope[pointsLength];
            for (int j = 0; j < pointsLength; j++) {
                Point p = points[j];
                PointSlope ps = new PointSlope();
                ps.point = p;
                ps.slope = p0.slopeTo(p);
                slopes[j] = ps;
            }
            sort(slopes);
            for (int j = 0; j < slopes.length - 2; j++) {
                PointSlope ps1 = slopes[j];
                PointSlope ps2 = slopes[j+1];
                PointSlope ps3 = slopes[j+2];
                if (ps1.slope == ps2.slope && ps2.slope == ps3.slope) {
                    Point smallest = findPoint(p0, ps1.point, ps2.point, ps3.point, -1);
                    Point biggest = findPoint(p0, ps1.point, ps2.point, ps3.point, 1);
                    if (validSegments.length == N)
                        segmentsResize(2 * validSegments.length);
                    validSegments[N++] = new LineSegment(smallest, biggest);
                }
            }
        }
    }

    private void segmentsResize(int size) {
        LineSegment[] newSegments = new LineSegment[size];
        for (int i = 0; i < N; i++)
            newSegments[i] = validSegments[i];
        validSegments = newSegments;
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

    private void sort(PointSlope[] slopes) {
        sortedSlopes = new PointSlope[slopes.length];
        sort(slopes, 0, slopes.length-1);
    }

    private void sort(PointSlope[] slopes, int start, int end) {
        if (start >= end)
            return;
        int mid = start + (end - start) / 2;
        sort(slopes, start, mid);
        sort(slopes, mid+1, end);
        merge(slopes, start, mid, end);
    }

    private boolean less(PointSlope ps1, PointSlope ps2) {
        if (ps1.slope < ps2.slope)
            return true;
        else
            return false;
    }

    private void merge(PointSlope[] slopes, int start, int mid, int end) {
        int i = start, j = mid + 1;
        for (int k = start; k <= end; k++)
            sortedSlopes[k] = slopes[k];
        for ( int k = start; k <= end; k++) {
            if (i > mid)
                slopes[k] = sortedSlopes[j++];
            else if (j > end)
                slopes[k] = sortedSlopes[i++];
            else if (less(sortedSlopes[i], sortedSlopes[j]))
                slopes[k] = sortedSlopes[i++];
            else
                slopes[k] = sortedSlopes[j++];
        }

    }

    public int numberOfSegments()        // the number of line segments
    {
        return N;
    }

    public LineSegment[] segments()                // the line segments
    {
        segmentsResize(N);
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}