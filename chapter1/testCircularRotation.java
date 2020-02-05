import edu.princeton.cs.algs4.StdOut;

public class testCircularRotation {

    public static void main(String[] args) {
        String s1 = "abc";
        String t1 = "def";

        StdOut.println("Is circular Shift 1: " + is_circular_rotation(s1, t1) + " Expected: false");

        String s2 = "rene";
        String t2 = "nere";

        StdOut.println("Is circular Shift 2: " + is_circular_rotation(s2, t2) + " Expected: true");
    }

    public static boolean is_circular_rotation(String s, String t) {
        return ((s + s).indexOf(t) > 0) ? true : false;
    }
}