import java.util.Arrays;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;


public class Dice
{
    static final int SIDES = 6;
    static int N = 0;
    static double[] theoretical_dist = new double[2*SIDES+1];
    static double[] practical_dist = new double[2*SIDES+1];

    public static int roll_dice()
    {
        return StdRandom.uniform(SIDES) + 1;
    }

    public static void theoretical_test()
    {
        for (int i = 1; i < SIDES; i++) {
            for (int j = 1; j <= SIDES; j++)
                theoretical_dist[i + j] += 1.0;
        }
        for (int k = 2; k <= 2 * SIDES; k++)
            theoretical_dist[k] /= SIDES * SIDES;
    }

    public static void practical_test()
    {
        for (int k = 2; k <= 2*SIDES; k++)
            practical_dist[k] *= N;
        int a = roll_dice();
        int b = roll_dice();
        practical_dist[a+b] += 1.0;
        N++;
        for (int k = 2; k <= 2*SIDES; k++)
            practical_dist[k] /= N;
    }

    public static boolean compare(double p)
    {
        for (int i = 0; i < 2*SIDES+1; i++)
        {
            if (Math.abs(theoretical_dist[i] - practical_dist[i]) >= p)
                return false;
        }
        return true;
    }

    public static void main (String[] args)
    {
        double p = Double.parseDouble(args[0]);
        theoretical_test();
        while (!compare(p))
            practical_test();
        StdOut.println(N);
        StdOut.println(Arrays.toString(theoretical_dist));
        StdOut.println(Arrays.toString(practical_dist));
    }
}