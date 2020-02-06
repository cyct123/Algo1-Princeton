import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Queue;


public class Josephus {
    public static void main(String[] args) {
        int M = Integer.parseInt(args[0]);
        int N = Integer.parseInt(args[1]);

        Queue<Integer> q = new Queue<Integer>();
        for (int i = 0; i < N; i++)
            q.enqueue(i);
        int i = 1;
        while (!q.isEmpty()) {
            int num = q.dequeue();
            if (i % 2 != 0)
                q.enqueue(num);
            else
                StdOut.print(num + " ");
            i++;
        }
        StdOut.println();
    }
}
