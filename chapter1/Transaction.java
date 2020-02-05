import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.StdOut;


public class Transaction
{
    private final String who;
    private final Date when;
    private final double amount;

    public Transaction(String name, Date time, double value)
    {
        who = name;
        when = time;
        amount = value;
    }

    public Transaction(String transaction)
    {
        String[] a = transaction.split("\\s+");
        who = a[0];
        when = new Date(a[1]);
        amount = Double.parseDouble(a[2]);
    }

    public String who()
    {
        return who;
    }

    public Date when()
    {
        return when;
    }

    public double amount()
    {
        return amount;
    }

    public String toString()
    {
        return who() + " " + when() + " " + amount();
    }

    public boolean equals(Object x)
    {
        if (this == x) return true;
        if (x == null) return false;
        if (this.getClass() != x.getClass()) return false;
        Transaction that = (Transaction) x;
        if (!this.who.equals(that.who)) return false;
        if (!this.when.equals(that.when)) return false;
        if (this.amount != that.amount) return false;
        return true;
    }

    public static void main(String[] args) {
        String who = args[0];
        Date when = new Date(args[1]);
        double amount = Double.parseDouble(args[2]);
        Transaction t1 = new Transaction(who, when, amount);
        String s = "james 12/31/2009 11.99";
        Transaction t2 = new Transaction(s);
        StdOut.println(t1);
        StdOut.println(t2);
        StdOut.println(t1.equals(t2));
    }
}