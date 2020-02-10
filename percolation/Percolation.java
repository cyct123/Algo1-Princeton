import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


public class Percolation {

    private final int size;
    private WeightedQuickUnionUF sites_uf;
    private boolean[][] sites;
    private int open_sites;
    private final int top;
    private final int bottom;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("n must be bigger than 1");
        size = n;
        sites = new boolean[n][n];
        sites_uf = new WeightedQuickUnionUF(size * size + 2);
        top = 0;
        bottom = size * size + 1;
    }

    private int point_index(int row, int col) {
        validate(row, col);
        return (row - 1) * size + col;
    }

    private void validate(int row, int col) {
        if ((row < 1) || (row > size))
            throw new IllegalArgumentException("row must be between 1 and " + size);
        if ((col < 1) || (col > size))
            throw new IllegalArgumentException("row must be between 1 and " + size);
    }

    private void connect(int cur_idx, int neighbor_row, int neighbor_col) {
        try {
            validate(neighbor_row, neighbor_col);
        } catch (IllegalArgumentException e) {
            return;
        }
        if (!isOpen(neighbor_row, neighbor_col)) return;
        int neighbor_idx = point_index(neighbor_row, neighbor_col);
        sites_uf.union(cur_idx, neighbor_idx);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (isOpen(row, col)) return;
        int cur_idx = point_index(row, col);
        if (row == 1)
            sites_uf.union(top, cur_idx);
        else if (row == size)
            sites_uf.union(bottom, cur_idx);
        connect(cur_idx, row - 1, col);
        connect(cur_idx, row + 1, col);
        connect(cur_idx, row, col - 1);
        connect(cur_idx, row, col + 1);
        sites[row - 1][col - 1] = true;
        open_sites++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return sites[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        int idx = point_index(row, col);
        return sites_uf.connected(top, idx);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return open_sites;
    }

    // does the system percolate?
    public boolean percolates() {
        return sites_uf.connected(top, bottom);
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
        int rand_test = StdRandom.uniform(size) + 1;
        for (int i = 0; i < rand_test; i++) {
            int rand_row = StdRandom.uniform(size) + 1;
            int rand_col = StdRandom.uniform(size) + 1;
            StdOut.println("" + rand_row + ", " + rand_col + " isOpen: " + p.isOpen(rand_row, rand_col));
            StdOut.println("" + rand_row + ", " + rand_col + " isFull: " + p.isFull(rand_row, rand_col));
        }
    }
}