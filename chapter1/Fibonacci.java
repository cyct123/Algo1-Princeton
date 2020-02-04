import edu.princeton.cs.algs4.StdOut;


public class Fibonacci
{
    public static long F(int N)
    {
        long[] a = new long[N+1];
        for (int i = 0; i < N + 1; i++)
        {
            if (i <= 1)
                a[i] = i;
            else
                a[i] = a[i-1] + a[i-2];
        }
        return a[N];
    }

    public static void main(String[] args)
    {
        for (int N = 0; N < 100; N++)
            StdOut.println(N + " " + F(N));
    }

}