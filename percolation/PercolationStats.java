import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;


public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;
    private final int trials;
    private final double[] frequency;
    private final double meanVal;
    private final double stdDevVal;
    private final double confidenceLoVal;
    private final double confidenceHiVal;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if ((n <= 0) || (trials <= 0))
            throw new IllegalArgumentException("n or trials must be large than 0");
        this.trials = trials;
        frequency = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int randRow = StdRandom.uniform(n) + 1;
                int randCol = StdRandom.uniform(n) + 1;
                p.open(randRow, randCol);
            }
            frequency[i] = (double) p.numberOfOpenSites() / (n * n);
        }
        meanVal = StdStats.mean(frequency);
        stdDevVal = StdStats.stddev(frequency);
        confidenceLoVal = meanVal - CONFIDENCE_95 * stdDevVal / Math.sqrt(trials);
        confidenceHiVal = meanVal + CONFIDENCE_95 * stdDevVal / Math.sqrt(trials);
    }

    // sample mean of percolation threshold
    public double mean() {
        return meanVal;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stdDevVal;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return confidenceLoVal;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return confidenceHiVal;
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, trials);
        StdOut.printf("%-24s= %.16f\n", "mean", ps.mean());
        StdOut.printf("%-24s= %.16f\n", "stddev", ps.stddev());
        StdOut.printf("%-24s= [%.16f, %.16f]\n", "95% confidence interval", ps.confidenceLo(), ps.confidenceHi());
    }
}