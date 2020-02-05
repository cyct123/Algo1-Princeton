import edu.princeton.cs.algs4.StdOut;


public class SmartDate
{
    private final int month;
    private final int day;
    private final int year;

    public SmartDate(int m, int d, int y)
    {
        if ((m < 1) || (m > 12))
            throw new IllegalArgumentException("Month should between 1 and 12");
        if ((d < 1) || (d > 31))
            throw new IllegalArgumentException("Day should between 1 and 31");
        month = m;
        day = d;
        year = y;
    }

    public SmartDate(String date)
    {
        String a[] = date.split("\\/");
        month = Integer.parseInt(a[0]);
        day = Integer.parseInt(a[1]);
        year = Integer.parseInt(a[2]);
    }

    public int month()
    {
        return month;
    }

    public int day()
    {
        return day;
    }

    public int year()
    {
        return year;
    }

    public String toString()
    {
        return month() + "/" + day() + "/" + year();
    }

    public boolean equals(Object x)
    {
        if (this == x) return true;
        if (x == null) return false;
        if (this.getClass() != x.getClass()) return false;
        SmartDate that = (SmartDate) x;
        if (this.day != that.day) return false;
        if (this.month != that.month) return false;
        if (this.year != that.year) return false;
        return true;
    }

    public static void main(String[] args) {
        int m = Integer.parseInt(args[0]);
        int d = Integer.parseInt(args[1]);
        int y = Integer.parseInt(args[2]);
        SmartDate d1 = new SmartDate(m, d, y);
        SmartDate d2 = new SmartDate("12/31/2009");
        StdOut.println(d1);
        StdOut.println(d2);
        StdOut.println(d1.equals(d2));
    }
}