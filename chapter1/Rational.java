import edu.princeton.cs.algs4.StdOut;


public class Rational
{
    private long num;
    private long deno;

    public Rational(int numerator, int denominator)
    {
        int c = gcd(numerator, denominator);
        num = numerator / c;
        deno = denominator / c;
    }

    private int gcd(int p, int q) {
        if (q == 0) return p;
        int r = p % q;
        return gcd(q, r);
    }

    public Rational plus(Rational b)
    {
        long num = this.num * b.deno + this.deno * b.num;
        long deno = this.deno * b.deno;
        return new Rational((int) num, (int) deno);
    }

    public Rational minus(Rational b)
    {
        long num = this.num * b.deno - this.deno * b.num;
        long demo = this.deno + b.deno;
        return new Rational((int) num, (int) deno);

    }

    public Rational times(Rational b)
    {
        long num = this.num * b.num;
        long deno = this.deno * b.deno;
        return new Rational((int) num, (int) deno);
    }

    public Rational divides(Rational b)
    {
        long num = this.num * b.deno;
        long deno = this.deno * b.num;
        return new Rational((int) num, (int) deno);
    }

    public boolean equals(Rational that)
    {
        if (this == that) return true;
        if (that == null) return false;
        if (this.getClass() != that.getClass()) return false;
        if (this.num != that.num) return false;
        if (this.deno != that.deno) return false;
        return true;
    }

    public String toString() {
        return num + "/" + deno;
    }

    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int c = Integer.parseInt(args[2]);
        int d = Integer.parseInt(args[3]);
        Rational r1 = new Rational(a, b);
        Rational r2 = new Rational(c, d);
        StdOut.println(r1);
        StdOut.println(r2);
        StdOut.println(r1.plus(r2));
        StdOut.println(r1.minus(r2));
        StdOut.println(r1.divides(r2));
        StdOut.println(r1.times(r2));
        StdOut.println(r1.equals(r2));
    }
}