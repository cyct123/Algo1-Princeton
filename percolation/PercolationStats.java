import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;


public class PercolationStats {

    private final int T;
    private double[] frequency;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        T = trials;
        frequency = new double[trials];
        for (int i = 0; i < T; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int rand_row = StdRandom.uniform(n) + 1;
                int rand_col = StdRandom.uniform(n) + 1;
                p.open(rand_row, rand_col);
            }
            frequency[i] = (double) p.numberOfOpenSites() / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(frequency);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(frequency);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
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