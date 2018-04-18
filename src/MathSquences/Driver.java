package MathSquences;

public class Driver
{
    public static void main(String[] args)
    {
        //print(calcValues(5));
       // calcValues(5);
        //recruseiveCalc(1,5);
       // System.out.println("The value: " + recursive8(2));

//        for (int i = 1; i < 6; i++)
//        {
//            System.out.println("The value " + i + ": " + recursive8(i));
//        }

        System.out.println("The total: " + sum(6,15));
    }

    static private double sum(int start, int finish)
    {
        if(start == finish +1)
        {
            return 0;
        }
        else
        {
           // return (6 * start )+ sum(start + 1, finish);
            return Math.pow(start, 3) + sum(start +1, finish);
        }
    }

    static private int recursive8(int n)
    {
        if(n == 1)
        {
            return 2;
        }
        else
        {
            return 4 + recursive8(n-1);
        }
    }

    static private void recruseiveCalc(int start, int n)
    {
        if(start == n+1)
        {
            return;
        }
        else
        {
            double numerator = Math.pow(-1, start);
            int denominator = (start+3) * (start +8);

            System.out.println(numerator);
            System.out.println("--------");
            System.out.println(denominator);
            System.out.println();

            recruseiveCalc(start +1, n);
        }

    }

    private static Double[] calcValues(int n)
    {
        Double[] temp = new Double[n];
        double numerator;
        int denominator;

        for (int i = 1; i < n +1; i++)
        {
           numerator = Math.pow(-1, i);

           denominator = (i+3) * (i +8);

           System.out.println(numerator);
           System.out.println("--------");
           System.out.println(denominator);
            System.out.println();

        }

        return temp;
    }

    private static <T> void print(T[] array)
    {
        for (int i = 0; i < array.length ; i++)
        {
            System.out.println(i+1 + ": " + array[i]);
        }
    }
{

}
}
