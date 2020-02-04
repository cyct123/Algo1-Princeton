import edu.princeton.cs.algs4.StdOut;


public class HelloWorld {

    public static int lg (int N) {
        int count = 0;
        while (N > 1) {
            count += 1;
            N /= 2;
        }
        return count;
    }

    public static int[] histogram(int[] a, int M) {
        int N = a.length;
        int[] b = new int[M];
        for (int i = 0; i < N; i++) {
            b[a[i]]++;
        }
        return b;
    }

    public static double ln_fact(int N)
    {
        if (N <= 1)
            return 0;
        return Math.log(N) + ln_fact(N-1);
    }
    public static void print_grades(String name, int total, int num)
    {
        double average = total / (double) num;
        StdOut.printf("%s %d, %.3f\n", name, total, average);
    }

    public static void main(String[] args) {
        //int N = Integer.parseInt(args[0]);
        //System.out.println(lg(N));
        //int[] a = new int[10];
        //int[] b = histogram(a, 11);
        for (int i = 0; i < 11; i++) {
        //    System.out.print(b[i] + " ");
            StdOut.printf("%d, %f\n", i, ln_fact(i));
        }
        String name = args[0];
        int total = Integer.parseInt(args[1]);
        int num = Integer.parseInt(args[2]);
        print_grades(name, total, num);
    }
}