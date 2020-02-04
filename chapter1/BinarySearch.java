import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class BinarySearch
{
    public static int rank(int key, int[] a)
    {
        int pos = rank(key, a, 0, a.length - 1);
        if (pos <= 0)
            return pos;
        while (a[--pos] == key);
        return pos + 1;
    }

    public static int count(int key, int[] a)
    {
        int pos = rank(key, a, 0, a.length - 1);
        if (pos == -1)
            return 0;
        while ((pos < a.length) && (a[pos] == key))
            pos++;
        return pos - rank(key, a);
    }

    public static int rank(int key, int[] a, int low, int high, int depth)
    {
        StdOut.printf("%d %d %d\n", depth, low, high);
        depth++;
        if (low > high)
            return -1;
        int mid = low + (high - low) / 2;
        if (key < a[mid])
            return rank(key, a, low, mid - 1, depth);
        else if (key > a[mid])
            return rank(key, a, mid + 1, high, depth);
        else
            return mid;
    }

    public static int rank(int key, int[] a, int low, int high)
    {
        if (low > high)
            return -1;
        int mid = low + (high - low) / 2;
        if (key < a[mid])
            return rank(key, a, low, mid - 1);
        else if (key > a[mid])
            return rank(key, a, mid + 1, high);
        else
            return mid;
    }

    public static int[] remove_duplicate(int[] a)
    {
        int len = a.length;
        int[] b = new int[len];
        int j = 0 ;
        for (int i = 0; i < len; i++)
        {
            if ((i != 0) && (a[i] == a[i-1]))
                continue;
            b[j] = a[i];
            j++;
        }
        return Arrays.copyOfRange(b, 0, j);
    }

    public static void main(String[] args)
    {
        int[] whitelist = In.readInts(args[0]);
        Arrays.sort(whitelist);
        whitelist = remove_duplicate(whitelist);
        StdOut.println(Arrays.toString(whitelist));
        while (!StdIn.isEmpty())
        {
            int key = StdIn.readInt();
            StdOut.printf("%d %d\n", key, count(key, whitelist));
            //StdOut.printf("%d %d\n", key, rank(key, whitelist));
            //int pos = rank(key, whitelist);
            //if (pos < 0)
            //    StdOut.println("+" + key);
            //else
            //    StdOut.printf("%d %d %d\n", key, pos, whitelist[pos]);
        }
    }
}