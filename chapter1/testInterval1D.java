import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Interval1D;


public class testInterval1D
{
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Interval1D[] intervals = new Interval1D[N];
        for (int i = 0; i < N; i++)
        {
            String line = StdIn.readLine();
            if (line == null)
            {
                break;
            }
            String[] nums = line.split("\\s+");
            double min = Double.parseDouble(nums[0]);
            double max = Double.parseDouble(nums[1]);
            intervals[i] = new Interval1D(min, max);
        }
        for (int i = 0; i < N; i++)
        {
            for (int j = i + 1; j < N; j++)
            {
                Interval1D interval1 = intervals[i];
                Interval1D interval2 = intervals[j];
                if (interval1.intersects(interval2))
                    StdOut.printf("%s %s\n", interval1, interval2);
            }
        }
    }
}
