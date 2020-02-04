import edu.princeton.cs.algs4.StdOut;


public class BinomialDistribution
{
    public static double binomial(int N, int k, double p)
    {
        double[][] a = new double[N+1][k+1];
        for (int i = 0; i < N+1; i++)
        {
            for (int j = 0; j < k+1; j++)
            {
                if ((i == 0) && (j == 0))
                    a[i][j] = 1.0;
                else {
                    double a1;
                    double a2;
                    if (i == 0) {
                        a1 = 0;
                        a2 = 0;
                    } else if (j == 0) {
                        a1 = a[i - 1][j];
                        a2 = 0;
                    } else
                    {
                        a1 = a[i-1][j];
                        a2 = a[i-1][j-1];
                    }
                    a[i][j] = (1.0 - p) * a1 + p * a2;
                }
            }
        }
        return a[N][k];
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);
        double p = Double.parseDouble(args[2]);
        StdOut.println(binomial(N, k, p));
    }
}