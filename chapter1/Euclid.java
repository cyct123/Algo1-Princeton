import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class Euclid
{
    public static int gcd(int p, int q)
    {
        //StdOut.printf("%d %d\n", p, q);
        if (q == 0)
            return p;
        int r = p % q;
        return gcd(q, r);
    }

    public static boolean[][] create_matrix(int N)
    {
        boolean[][] a = new boolean[N][N];
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                if ((i <= 1) || (j <= 1) || (gcd(i, j) != 1))
                    a[i][j] = false;
                else
                    a[i][j] = true;
            }
        }
        return a;
    }

    public static void main(String[] args)
    {
        //int p = Integer.parseInt(args[0]);
        //int q = Integer.parseInt(args[1]);
        //StdOut.println(gcd(p, q));
        int N = Integer.parseInt(args[0]);
        boolean[][] a = create_matrix(N);
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                if (a[i][j] == false)
                    StdOut.printf("- ");
                else
                    StdOut.printf("+ ");
            }
            StdOut.println();
        }
    }
}
