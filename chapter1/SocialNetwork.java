import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class SocialNetwork
{
    private int[] root;
    private int[] size;
    private int count;

    public SocialNetwork(int n) {
        count = n;
        root = new int[n];
        size = new int[n];
        for(int i = 0; i < n; i++) {
            root[i] = i;
            size[i] = 1;
        }
    }

    public int find(int p) {
        while (p != root[p])
            p = root[p];
        return p;
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        if (size[rootP] < size[rootQ]) {
            root[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        else {
            root[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }

    public boolean if_all_connected() {
        return count == 1;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        SocialNetwork sw = new SocialNetwork(n);
        while (!StdIn.isEmpty()) {
            String timestamp = StdIn.readString();
            int p1 = StdIn.readInt();
            int p2 = StdIn.readInt();
            if (sw.find(p1) == sw.find(p2)) continue;
            sw.union(p1, p2);
            if (sw.if_all_connected()) {
                StdOut.println(timestamp + " all_connected");
                break;
            }
        }
    }
}