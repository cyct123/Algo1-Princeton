import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


public class Percolation {

    private final int size;
    private final WeightedQuickUnionUF percolateSitesUF;
    private final WeightedQuickUnionUF fullSitesUF;
    private final boolean[][] sites;
    private int openSites;
    private final int top;
    private final int bottom;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("n must be bigger than 1");
        size = n;
        sites = new boolean[n][n];
        percolateSitesUF = new WeightedQuickUnionUF(size * size + 2);
        fullSitesUF = new WeightedQuickUnionUF(size * size + 1);
        top = 0;
        bottom = size * size + 1;
    }

    private int pointIndex(int row, int col) {
        validate(row, col);
        return (row - 1) * size + col;
    }

    private void validate(int row, int col) {
        if ((row < 1) || (row > size))
            throw new IllegalArgumentException("row must be between 1 and " + size);
        if ((col < 1) || (col > size))
            throw new IllegalArgumentException("row must be between 1 and " + size);
    }

    private void connect(int curIdx, int neighborRow, int neighborCol) {
        try {
            validate(neighborRow, neighborCol);
        } catch (IllegalArgumentException e) {
            return;
        }
        if (!isOpen(neighborRow, neighborCol)) return;
        int neighborIdx = pointIndex(neighborRow, neighborCol);
        percolateSitesUF.union(curIdx, neighborIdx);
        fullSitesUF.union(curIdx, neighborIdx);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (isOpen(row, col)) return;
        int curIdx = pointIndex(row, col);
        if (row == 1) {
            percolateSitesUF.union(top, curIdx);
            fullSitesUF.union(top, curIdx);
        }
        if (row == size)
            percolateSitesUF.union(bottom, curIdx);
        connect(curIdx, row - 1, col);
        connect(curIdx, row + 1, col);
        connect(curIdx, row, col - 1);
        connect(curIdx, row, col + 1);
        sites[row - 1][col - 1] = true;
        openSites++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return sites[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        int idx = pointIndex(row, col);
        return fullSitesUF.connected(top, idx);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return percolateSitesUF.connected(top, bottom);
    }

    // test client (optional)
    public static void main(String[] args) {
        int size = StdIn.readInt();
        Percolation p = new Percolation(size);
        while (!StdIn.isEmpty()) {
            int row = StdIn.readInt();
            int col = StdIn.readInt();
            p.open(row, col);
        }
        StdOut.println(p.numberOfOpenSites());
        StdOut.println(p.percolates());
        int randTest = StdRandom.uniform(size) + 1;
        for (int i = 0; i < randTest; i++) {
            int randRow = StdRandom.uniform(size) + 1;
            int randCol = StdRandom.uniform(size) + 1;
            StdOut.println(
                    "" + randRow + ", " + randCol + " isOpen: " + p.isOpen(randRow, randCol));
            StdOut.println(
                    "" + randRow + ", " + randCol + " isFull: " + p.isFull(randRow, randCol));
        }
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                StdOut.println("" + i + ", " + j + " isOpen: " + p.isOpen(i, j));
                StdOut.println("" + i + ", " + j + " isFull: " + p.isFull(i, j));
            }
        }
        int i = 1;
        int j = 18;
        StdOut.println("" + i + ", " + j + " isFull: " + p.isFull(i, j));
    }
}