public class Matrix {

    public static double dot(double[] x, double[] y) {
        int len_x = x.length;
        int len_y = y.length;
        if (len_x != len_y)
            throw new IllegalArgumentException("x and y should be same size!");
        double total = 0.0;
        for (int i = 0; i < len_x; i++)
            total += x[i] * y[i];
        return total;
    }

    public static double[][] mult(double[][] a, double[][] b)
    {
        int len_row_a = a.length;
        int len_col_a = a[0].length;
        int len_row_b = b.length;
        int len_col_b = b[0].length;
        if (len_col_a != len_row_b)
            throw new IllegalArgumentException("size of a column and size of b row should be same!");
        double[][] c = new double[len_row_a][len_col_b];
        for (int i = 0; i < len_row_a; i++)
        {
            for (int j = 0; i < len_col_b; j++)
            {
                c[i][j] = 1;
            }
        }
    }

    public static double[][] transpose(double[][] a)
    {

    }

    public static double[] mult(double[][] a, double[] x)
    {

    }

    public static double[] mult(double[] y, double[][] a)
    {

    }
}